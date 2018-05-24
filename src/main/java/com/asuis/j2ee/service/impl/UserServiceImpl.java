package com.asuis.j2ee.service.impl;

import com.asuis.j2ee.auth.JwtUtils;
import com.asuis.j2ee.dao.*;
import com.asuis.j2ee.domain.DepUser;
import com.asuis.j2ee.domain.MACAddress;
import com.asuis.j2ee.domain.User;
import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.form.LoginForm;
import com.asuis.j2ee.form.RegisterForm;
import com.asuis.j2ee.model.Department;
import com.asuis.j2ee.mongodb.domain.ActionRecord;
import com.asuis.j2ee.mongodb.repository.ActionRecordRepository;
import com.asuis.j2ee.service.UserService;
import com.asuis.j2ee.utils.IpMacUtils;
import com.asuis.j2ee.vo.JwtUser;
import com.asuis.j2ee.vo.RoleUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 15988440973
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ActionRecordRepository actionRecordRepository;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UsersAuthMapper usersAuthMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public Result loginByUsernameAndPassword(LoginForm loginForm, HttpServletRequest request) {
        Result result = new Result();
        User user = userDao.getUserByUsernameAndPassword(loginForm.getUsername(),loginForm.getPassword());
        if (user!=null) {
            JwtUser jwtUser = new JwtUser();
            jwtUser.setAvatar(user.getAvatar());
            jwtUser.setJsonWebToken(JwtUtils.addAuthentication(user.getUsername()));
            jwtUser.setRefreshToken("");
            jwtUser.setUsername(user.getUsername());
            result.setCode(200);
            result.setMessage("login successful");
            result.setData(jwtUser);
            Department department = departmentDao.getDepartmentByUserId(user.getUserId());
            try {
                String ip = IpMacUtils.getIpAddr(request);
                MACAddress macAddress = new MACAddress(ip);
                String mac = macAddress.getMac();

                ActionRecord actionRecord = new ActionRecord();
                actionRecord.setOperatorId(user.getUserId().toString());
                actionRecord.setOperator(user.getUsername());
                actionRecord.setTime(new Date(System.currentTimeMillis()));
                actionRecord.setDesc("登录签到");
                actionRecord.setOperatorAvatar(user.getAvatar());
                if (department!=null) {
                    actionRecord.setDepartName(department.getName());
                }
                actionRecord.setIp(ip);
                actionRecord.setMac(mac);
                actionRecord.setAction("LOGIN");
                actionRecordRepository.insert(actionRecord);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            result.setCode(404);
            result.setMessage("用户名或密码不正确");
        }
        return result;
    }

    @Override
    public Result register(RegisterForm registerForm) {

        return null;
    }

    @Override
    public Result updateUserDetail() {
        return null;
    }

    @Override
    public Result unRegister() {
        return null;
    }

    @Override
    public Result getUserInfo(String username) {
        Result result = new Result();
        if (username==null) {
            result.setCode(501);
            result.setMessage("username error");
            return result;
        }
        if (!Pattern.matches("^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$",username)) {
            result.setCode(501);
            result.setMessage("username error");
            return result;
        }
        com.asuis.j2ee.model.User user = userDao.getUserByUsername(username);
        result.setCode(200);
        result.setData(user);
        return result;
    }

    @Override
    public Result getUserListByKey(List<String> key, Integer pageNum, Integer pagesize) {
        Result result = new Result();
        if (pageNum==null) {
            pageNum = 1;
        } else if (pageNum<1) {
            pageNum = 1;
        }
        if (pagesize==null) {
            pagesize = 10;
        } else if (pagesize<1) {
            pagesize = 10;
        }
        if (key==null) {
            key = new ArrayList<>();
        }
        PageHelper.startPage(pageNum,pagesize);
        List<RoleUser> users = userDao.getUserListByKey(key);
        PageInfo<RoleUser> pageInfo = new PageInfo<>(users);
        if (users.size()==0) {
            result.setCode(404);
            result.setMessage("404040404");
        } else {
            result.setCode(200);
            result.setData(pageInfo);
        }
        return result;
    }
}
