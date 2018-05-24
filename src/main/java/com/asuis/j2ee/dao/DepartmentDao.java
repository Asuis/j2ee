package com.asuis.j2ee.dao;

import com.asuis.j2ee.model.Department;
import com.asuis.j2ee.model.User;

import java.util.List;

/**
 * @author 15988440973
 */
public interface DepartmentDao {
    public List<User> getUsersByDepartment(String departName);
    public Department getDepartmentDetailByName(String departName);
    public List<Department> getDepartmentByPid(Integer pid);
    /**todo*/
    public List<Department> getDepartmentByName(List<String> keys);
    public Department getDepartmentByUserId(Long userId);
    public List<Department> getDepartments();
    public List<Department> getDepartDetails(Integer pid);
    public int deleteAllDepByUserId(Long userId);
    public int countByPid(Integer pid);
}