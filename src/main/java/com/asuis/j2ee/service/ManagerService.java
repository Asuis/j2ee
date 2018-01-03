package com.asuis.j2ee.service;


import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.form.AddDepartmentForm;
import com.asuis.j2ee.form.RegisterForm;
import com.asuis.j2ee.form.RoleForm;
import com.asuis.j2ee.form.UserUpdateForm;
import com.asuis.j2ee.mongodb.domain.RequestMessage;

import javax.management.relation.RelationSupport;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 管理者服务接口
 * 包括
 * 1.提交删除、添加人员请求
 * 2.查询指定部门信息、人员信息、人员业绩
 * 3.提交删除、添加部门请求
 * 4.角色添加、删除（业务员）
 * @author 15988440973
 */
public interface ManagerService {
    /**提交请求*/
    public Result submitRequest(RequestMessage requestMessage);
    /**查询指定部门信息*/
    public Result getDepartmentDetail(String departName);
    /**查询指定人员信息*/
    public Result getEmpolyeeDetail(String username);
    /**查询人员信息列表,条件部门或其他*/
    public Result getEmpolyeeList(String departName,Integer pageNum,Integer pageSize);
    /**添加角色*/
    public Result addRole(RoleForm roleForm);
    /**删除角色*/
    public Result deleteRole(String role);

    public Result getDepartments();

    public Result getRoles();
    public Result updateUser(UserUpdateForm userUpdateForm);
    public Result getDepartDetails(Integer pid);
    public Result submitAddDepRequest(HttpServletRequest request, String username, AddDepartmentForm addDepartmentForm);
    public Result queryRequest(String username);
    public Result submitAddUserRequest(HttpServletRequest request, String username, RegisterForm registerForm);
}
