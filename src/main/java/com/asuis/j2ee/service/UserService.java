package com.asuis.j2ee.service;

import java.util.HashMap;

/**
 * 用户服务负责用户的登录、注册、用户信息的完善
 * @author 15988440973
 */
public interface UserService {
    /**登录服务返回token、refresh token*/
    public HashMap<String,String> login();
    /**注册服务返回result200成功，？？？失败*/
    public HashMap<String,String> register();
    /**完善用户信息*/
    public HashMap<String,String> updateUserDetail();
    /**注销用户*/
    public HashMap<String,String> unRegister();
}
