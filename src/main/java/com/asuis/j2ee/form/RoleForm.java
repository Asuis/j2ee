package com.asuis.j2ee.form;

import java.util.List;

/**
 * @author 15988440973
 */
public class RoleForm {
    private String roleName;
    private String roleDetails;
    private List<Integer> roles;
    private String updatePerson;

    public String getRoleDetails() {
        return roleDetails;
    }

    public void setRoleDetails(String roleDetails) {
        this.roleDetails = roleDetails;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
