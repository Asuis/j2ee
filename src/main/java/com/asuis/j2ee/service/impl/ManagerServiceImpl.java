package com.asuis.j2ee.service.impl;

import com.asuis.j2ee.dao.*;
import com.asuis.j2ee.dto.PageRequest;
import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.form.AddDepartmentForm;
import com.asuis.j2ee.form.RegisterForm;
import com.asuis.j2ee.form.RoleForm;
import com.asuis.j2ee.form.UserUpdateForm;
import com.asuis.j2ee.model.*;
import com.asuis.j2ee.mongodb.domain.ActionRecord;
import com.asuis.j2ee.mongodb.domain.RequestMessage;
import com.asuis.j2ee.mongodb.repository.ActionRecordRepository;
import com.asuis.j2ee.mongodb.repository.RequestMessageRepository;
import com.asuis.j2ee.service.ManagerService;
import com.asuis.j2ee.utils.IpMacUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 15988440973
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private final String REQUEST_STATUS_WAIT = "0";
    private final String REQUEST_ACTION_ADD_DEP = "0";
    private final String REQUEST_ACTION_DELETE_DEP = "1";
    private final String REQUEST_ACTION_ADD_USER = "2";
    private final String REQUEST_ACTION_DELETE_USER = "3";
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RequestMessageRepository requestMessageRepository;

    @Autowired
    private ActionRecordRepository actionRecordRepository;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserDepMapper userDepMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleRoutesMapper roleRoutesMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result submitRequest(RequestMessage requestMessage) {

        Result result = new Result();

        try {
            requestMessageRepository.insert(requestMessage);
            result.setCode(200);
            result.setMessage("insert requset message successful");
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("insert fail");
        }
        return result;
    }

    @Override
    public Result getDepartmentDetail(String departName) {

        Result result = new Result();

        Department department = departmentDao.getDepartmentDetailByName(departName);
        if (department!=null) {
            result.setCode(200);
            result.setMessage("find successful");
            result.setData(department);
        } else {
            result.setCode(404);
            result.setMessage("not find data");
        }
        return result;
    }

    @Override
    public Result getEmpolyeeDetail(String username) {
        User user = userDao.getUserByUsername(username);
        Result result = new Result();
        if (user!=null) {
            result.setCode(200);
            result.setMessage("find successful");
            result.setData(user);
        } else {
            result.setCode(404);
            result.setMessage("not find data");
        }
        return result;
    }

    @Override
    public Result getEmpolyeeList(String departName,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> usersByDepartment = departmentDao.getUsersByDepartment(departName);
        PageInfo<User> info = new PageInfo<>(usersByDepartment);
        Result result = new Result();
        if (usersByDepartment.size()==0) {
            result.setCode(404);
            result.setMessage("not find data");
        } else {
            result.setCode(200);
            result.setMessage("");
            result.setData(info);
        }
        return result;
    }
    //todo
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addRole(RoleForm roleForm) {

        Result result = new Result();

        Role role1 = new Role();

        role1.setRoleName(roleForm.getRoleName());
        role1.setRemark(roleForm.getRoleName());
        role1.setUpdatePerson(roleForm.getUpdatePerson());
        role1.setUpdateDate(new Date(System.currentTimeMillis()));
        role1.setCreateDate(new Date(System.currentTimeMillis()));
        role1.setCreatePerson("SYSTEM");
        try {
            roleMapper.insert(role1);
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("error");
        }

        try {
            for (Integer id:roleForm.getRoles()) {
                List<RoleRoutes> roleRoutes = roleDao.getRoleRoutesByRoleId(id);
                for (RoleRoutes r:roleRoutes) {
                    Integer routesId = r.getRoutesId();
                    RoleRoutes roleRoutes1 = new RoleRoutes();
                    roleRoutes1.setRoutesId(routesId);
                    roleRoutes1.setRoleId(role1.getId());
                    roleRoutesMapper.insertSelective(roleRoutes1);
                }
            }
            result.setCode(200);
            result.setMessage("ok");
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("roleRoutes error");
        }

        return result;
    }
    //todo
    @Override
    public Result deleteRole(String role) {
        return null;
    }

    @Override
    public Result getDepartments() {
        Result result = new Result();
        try {
            List<Department> departments = departmentDao.getDepartments();
            result.setCode(200);
            result.setData(departments);
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("error");
        }
        return result;
    }

    @Override
    public Result getRoles() {
        Result result = new Result();
        try {
            List<com.asuis.j2ee.vo.Role> roles = roleDao.getRoleList();
            result.setCode(200);
            result.setData(roles);
        } catch (Exception e) {
            result.setCode(404);
            result.setMessage("???吃惊");
        }
        return result;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateUser(UserUpdateForm userUpdateForm) {
        Result result = new Result();
        try {
            User user = new User();
            user.setUserId(userUpdateForm.getUserId());
            user.setPhone(userUpdateForm.getPhoneNumber());
            userMapper.updateByPrimaryKeySelective(user);


            departmentDao.deleteAllDepByUserId(userUpdateForm.getUserId());
            UserDep userDep = new UserDep();
            userDep.setUserId(userUpdateForm.getUserId());
            userDep.setDepId(userUpdateForm.getDepName());
            userDepMapper.insert(userDep);


            roleDao.deleteRolesByUserId(userUpdateForm.getUserId());
            for (Integer role:userUpdateForm.getRoleName()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userUpdateForm.getUserId());
                userRole.setRoleId(role);
                userRoleMapper.insert(userRole);
            }
            result.setCode(200);
            result.setMessage("ok");
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @Override
    public Result getDepartDetails(Integer pid) {

        Result result = new Result();

        if (pid == null) {
            pid = 0;
        }
        try {
            List<Department> departments = departmentDao.getDepartDetails(pid);
            result.setCode(200);
            result.setData(departments);
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("alalala");
        }

        return result;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result submitAddDepRequest(HttpServletRequest request, String username, AddDepartmentForm addDepartmentForm) {



        Result result = new Result();
        User user = userDao.getUserByUsername(username);

        Department department = departmentDao.getDepartmentByUserId(user.getUserId());

        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setApplicantId(user.getUserId().toString());
        requestMessage.setApplicant(username);
        requestMessage.setAction(REQUEST_ACTION_ADD_DEP);
        requestMessage.setReason(addDepartmentForm.getReason());
        requestMessage.setStatus(REQUEST_STATUS_WAIT);
        requestMessage.setData(addDepartmentForm);
        requestMessage.setRequestTime(new Date(System.currentTimeMillis()));

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setAction(REQUEST_ACTION_ADD_DEP);
        actionRecord.setDesc("添加"+addDepartmentForm.getDepartName());
        actionRecord.setOperator(user.getUsername());
        actionRecord.setOperatorId(user.getUserId().toString());
        actionRecord.setMac("");
        String ip = null;
        try {
            ip = IpMacUtils.getIpAddr(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        actionRecord.setIp(ip);
        actionRecord.setDepartName(department.getName());
        actionRecord.setOperatorAvatar(user.getAvatar());
        actionRecord.setTime(new Date(System.currentTimeMillis()));
        try {
            requestMessageRepository.insert(requestMessage);
            result.setCode(200);
            actionRecordRepository.insert(actionRecord);
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("aaaaaaaaaaaaaaaa");
        }
        return result;
    }

    @Override
    public Result queryRequest(String username) {
        Result result = new Result();
        Sort sort = new Sort(Sort.Direction.DESC,"_id");
        Pageable pageable = new PageRequest(0,100,sort);
        try {
            Page<RequestMessage> requestMessages = requestMessageRepository.findByApplicant(username,pageable);
            result.setCode(200);
            result.setData(requestMessages);
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("fial");
        }
        return result;
    }

    @Override
    public Result submitAddUserRequest(HttpServletRequest request, String username, RegisterForm registerForm) {

        Result result = new Result();

        User user = userDao.getUserByUsername(username);

        RequestMessage requestMessage = new RequestMessage();

        Department department = departmentDao.getDepartmentByUserId(user.getUserId());

        requestMessage.setStatus(REQUEST_STATUS_WAIT);
        requestMessage.setAction(REQUEST_ACTION_ADD_USER);
        requestMessage.setData(registerForm);
        requestMessage.setReason(registerForm.getReason());
        requestMessage.setApplicant(user.getUsername());
        requestMessage.setApplicantId(user.getUserId().toString());
        requestMessage.setRequestTime(new Date(System.currentTimeMillis()));
        requestMessage.setApplicantAvatar(user.getAvatar());

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setTime(new Date(System.currentTimeMillis()));
        actionRecord.setAction(REQUEST_ACTION_ADD_USER);
        actionRecord.setOperatorAvatar(user.getAvatar());
        actionRecord.setOperator(user.getUsername());

        String ip = null;
        try {
            ip = IpMacUtils.getIpAddr(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        actionRecord.setIp(ip);
        actionRecord.setMac("");
        actionRecord.setDesc("添加"+registerForm.getUsername());
        actionRecord.setDepartName(department.getName());
        actionRecord.setTime(new Date(System.currentTimeMillis()));
        actionRecord.setOperator(user.getUsername());
        actionRecord.setOperatorAvatar(user.getAvatar());
        actionRecord.setOperatorId(user.getUserId().toString());
        try {
            requestMessageRepository.insert(requestMessage);
            actionRecordRepository.insert(actionRecord);
            result.setCode(200);
        } catch (Exception e) {
            result.setCode(501);
            result.setData(e.getMessage());
        }
        return result;
    }
}