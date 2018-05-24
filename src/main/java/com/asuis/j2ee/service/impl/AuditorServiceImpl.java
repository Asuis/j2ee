package com.asuis.j2ee.service.impl;
import com.asuis.j2ee.dao.*;
import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.form.AddDepartmentForm;
import com.asuis.j2ee.form.RegisterForm;
import com.asuis.j2ee.model.*;
import com.asuis.j2ee.mongodb.domain.ActionRecord;
import com.asuis.j2ee.mongodb.domain.AuditMessage;
import com.asuis.j2ee.mongodb.domain.RequestMessage;
import com.asuis.j2ee.mongodb.repository.ActionRecordRepository;
import com.asuis.j2ee.mongodb.repository.RequestMessageRepository;
import com.asuis.j2ee.service.AuditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
/**
 * @author 15988440973
 */
@Service
public class AuditorServiceImpl implements AuditorService {

    private final String STATUS_NEED_AUDIT = "0";
    private final String STATUS_NON_NEED_AUDIT = "1";

    private final String REQUEST_STATUS_WAIT = "0";
    private final String REQURST_STATUS_REJECT = "2";

    private final String REQUEST_ACTION_ADD_DEP = "0";
    private final String REQUEST_ACTION_DELETE_DEP = "1";
    private final String REQUEST_ACTION_ADD_USER = "2";
    private final String REQUEST_ACTION_DELETE_USER = "3";

    @Autowired
    private ActionRecordRepository actionRecordRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UsersAuthMapper usersAuthMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserDepMapper userDepMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RequestMessageRepository requestMessageRepository;

    @Override
    public Result getRequestNeedAudit(Integer pageNum, Integer pageSize) {

        Result result = new Result();

        Sort sort = new Sort(Sort.Direction.DESC,"requestTime");
        org.springframework.data.domain.Pageable pageable = new com.asuis.j2ee.dto.PageRequest(pageNum-1,pageSize,sort);
        try {
            Page<RequestMessage> data = requestMessageRepository.findByStatusEqualsOrderByRequestTime(STATUS_NEED_AUDIT,pageable);
            result.setCode(200);
            result.setData(data);
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("error");
        }
        return result;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result auditRequest(User user,Department dep,String ip,AuditMessage auditMessage,String requestId) {

        Result result = new Result();

        RequestMessage requestMessage = requestMessageRepository.findBy_id(requestId);

        if (requestMessage!=null&& requestMessage.getStatus().equals(STATUS_NEED_AUDIT)) {
            requestMessage.setAuditMessage(auditMessage);

            ActionRecord actionRecord = new ActionRecord();
            actionRecord.setAction("AUDIT");
            actionRecord.setDepartName(dep.getName());
            actionRecord.setIp(ip);
            actionRecord.setTime(new Date(System.currentTimeMillis()));
            actionRecord.setDesc("驳回请求");
            actionRecord.setOperatorAvatar(user.getAvatar());
            actionRecord.setMac("");
            actionRecord.setOperatorId(user.getUserId().toString());
            actionRecord.setOperator(user.getUsername());


            if (auditMessage.isResult()) {
                requestMessage.setAuditMessage(auditMessage);
                requestMessage.setStatus(STATUS_NON_NEED_AUDIT);
                try {
                    requestMessageRepository.save(requestMessage);
                    actionRecord.setAction("AUDIT_ALLOW");
                    switch (requestMessage.getAction()){
                        case REQUEST_ACTION_ADD_DEP:
                            AddDepartmentForm addDepartmentForm = (AddDepartmentForm) requestMessage.getData();
                            Department department = new Department();
                            department.setCreateDate(new Date(System.currentTimeMillis()));
                            department.setUpdateDate(new Date(System.currentTimeMillis()));
                            department.setCreatePersion(addDepartmentForm.getCreatePerson());
                            department.setParentId(addDepartmentForm.getPid());
                            department.setRemark(addDepartmentForm.getRemark());
                            department.setCreatePersion(requestMessage.getApplicant());
                            department.setName(addDepartmentForm.getDepartName());

                            actionRecord.setDesc("允许添加"+addDepartmentForm.getDepartName());

                            if (addDepartmentForm.getPid()!=0) {
                                Department departmentP = departmentMapper.selectByPrimaryKey(addDepartmentForm.getPid());
                                String struct = departmentP.getStructure();
                                Integer count = departmentDao.countByPid(addDepartmentForm.getPid())+1;
                                struct += "-"+count;
                                department.setStructure(struct);
                                department.setSortNo(departmentP.getSortNo()+1);
                            } else {
                                Integer count = departmentDao.countByPid(0)+1;
                                department.setStructure(count.toString());
                                department.setSortNo(1);
                            }
                            departmentMapper.insert(department);
                            break;
                        case REQUEST_ACTION_ADD_USER:
                            RegisterForm registerForm = (RegisterForm) requestMessage.getData();
                            //插入用户
                            User user1 = new User();
                            user1.setPhone(registerForm.getPhone());
                            user1.setUsername(registerForm.getUsername());
                            user1.setCreateDate(new Date(System.currentTimeMillis()));
                            user1.setIsEnabled("1");
                            user1.setIsAccountNonExpired("1");

                            actionRecord.setDesc("允许添加成员"+registerForm.getUsername());

                            userMapper.insertSelective(user1);
                            //授予账号密码登录权限
                            UsersAuth usersAuth = new UsersAuth();
                            usersAuth.setUserId(user1.getUserId());
                            usersAuth.setCredential("123456");
                            usersAuth.setIdentifier(user1.getUsername());
                            usersAuth.setIndentity("LOGIN");

                            usersAuthMapper.insertSelective(usersAuth);
                            //授予操作权限
                            for (Integer roleId:registerForm.getRoleIds()) {
                                UserRole role = new UserRole();
                                role.setUserId(user1.getUserId());
                                role.setRoleId(roleId);
                                userRoleMapper.insert(role);
                            }
                            //添加至部门
                            UserDep userDep = new UserDep();
                            userDep.setDepId(registerForm.getDepId());
                            userDep.setUserId(user1.getUserId());
                            userDepMapper.insert(userDep);
                            break;
                        case REQUEST_ACTION_DELETE_DEP:
                            actionRecord.setDesc("允许删除部门");
                            break;
                        case REQUEST_ACTION_DELETE_USER:
                            actionRecord.setDesc("允许删除用户");
                            break;
                        default:
                            break;
                    }
                    result.setCode(200);
                    result.setMessage("audit successful");
                } catch (Exception e) {
                    result.setCode(501);
                    result.setMessage("audit error");
                    throw e;
                }
            } else {
                requestMessage.setStatus(REQURST_STATUS_REJECT);
                actionRecord.setAction("AUDIT_REJECT");
                actionRecord.setDesc("驳回"+requestMessage.getApplicant()+"的请求(ID:"+requestMessage.get_id()+")");
                try {
                    requestMessage.setAuditMessage(auditMessage);
                    requestMessageRepository.save(requestMessage);
                    result.setCode(200);
                    result.setMessage("未通过审核");

                } catch (Exception e) {
                    result.setCode(501);
                    result.setMessage("失败aaaa");
                }
            }
            try {
                actionRecordRepository.insert(actionRecord);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            result.setCode(502);
            result.setMessage("该请求已删除或已被受理");
        }
        return result;
    }

    @Override
    public Result getRequestByStatusAndAuditor(String userId, String status, Integer pageNum, Integer pageSize) {

        Result result = new Result();

        org.springframework.data.domain.Pageable pageable = new com.asuis.j2ee.dto.PageRequest(pageNum,pageSize,null);


        try {
            List<RequestMessage> data = requestMessageRepository.findByQuery(userId);
            result.setCode(200);
            result.setData(data);
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage(e.getMessage());
        }

        //todo
        return result;
    }

    @Override
    public Result getRequestByStatus(String status, Integer pageNum, Integer pageSize) {

        Result result = new Result();

        org.springframework.data.domain.Pageable pageable = new com.asuis.j2ee.dto.PageRequest(pageNum,pageSize,null);
        try {
            Page<RequestMessage> page = requestMessageRepository.findByStatus(status,pageable);
            result.setCode(200);
            result.setData(page);
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}