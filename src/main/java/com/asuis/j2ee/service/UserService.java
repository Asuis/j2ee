package com.asuis.j2ee.service;

import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.form.LoginForm;
import com.asuis.j2ee.form.RegisterForm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 用户服务负责用户的登录、注册、用户信息的完善
 * @author 15988440973
 */
public interface UserService {
    /**登录服务返回token、refresh token*/
    public Result loginByUsernameAndPassword(LoginForm loginForm, HttpServletRequest request);
    /**注册服务返回result200成功，？？？失败*/
    public Result register(RegisterForm registerForm);
    /**完善用户信息*/
    public Result updateUserDetail();
    /**注销用户*/
    public Result unRegister();
    /**获取用户详情*/
    public Result getUserInfo(String username);

    /**
     * @param key jhjh
     * @param offset khj
     * @param pagesize kjkj
     * @return kjkj
     */
    public Result getUserListByKey(List<String> key, Integer offset, Integer pagesize);
}
