package com.asuis.j2ee.service;


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
    public HashMap<String,String> submitRequest();
    /**查询指定部门信息*/
    public HashMap<String,String> getDepartmentDetail();
    /**查询指定人员信息*/
    public HashMap<String,String> getEmpolyeeDetail();
    /**查询人员信息列表,条件部门或其他*/
    public List getEmpolyeeList();
    /**添加角色*/
    public HashMap<String,String> addRole();
    /**删除角色*/
    public HashMap<String,String> deleteRole();
}
