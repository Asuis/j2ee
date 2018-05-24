package com.asuis.j2ee.controller;

import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.form.*;
import com.asuis.j2ee.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 15988440973
 */
@RestController
@RequestMapping("/manager")
@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @RequestMapping("/roles/info")
    public Result getRoles() {
        return managerService.getRoles();
    }

    @RequestMapping("/user/update")
    public Result updateUser (@ModelAttribute("memberUpdateForm")UserUpdateForm userUpdateForm) {

        return managerService.updateUser(userUpdateForm);
    }
    @RequestMapping("/dep/get")
    public Result getDepartments() {
        return managerService.getDepartments();
    }
    @RequestMapping("/dep/details")
    public Result getDepartDetails (@RequestParam(value = "pid",required = false) Integer pid) {
        return managerService.getDepartDetails(pid);
    }
    @RequestMapping("role/add")
    public Result addRole(@RequestAttribute("username") String username, @ModelAttribute("roleForm")RoleForm roleForm) {
        roleForm.setUpdatePerson(username);
        return managerService.addRole(roleForm);
    }
    @RequestMapping("/dep/add")
    public Result addRequest(HttpServletRequest request, @RequestAttribute("username") String username, @ModelAttribute("dep_form")AddDepartmentForm addDepartmentForm) {
        return managerService.submitAddDepRequest(request, username, addDepartmentForm);
    }
    //todo *3
    @RequestMapping("/user/add")
    public Result addUserRequest(@RequestAttribute("username")String username,HttpServletRequest request,@ModelAttribute("user_create_form")RegisterForm registerForm) {
        return managerService.submitAddUserRequest(request,username,registerForm);
    }
    @RequestMapping("/dep/delete")
    public Result deleteDepRequest(@RequestAttribute("username") String username, HttpServletRequest request, RemoveDepartmentForm removeDepartmentForm) {
        return null;
    }
    @RequestMapping("/user/delete")
    public Result deleteUserRequest() {
        return null;
    }
    @RequestMapping("/request/query")
    public Result queryRequest(@RequestAttribute("username") String username) {
        return managerService.queryRequest(username);
    }
}