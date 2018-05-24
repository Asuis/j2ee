package com.asuis.j2ee.form;

import java.util.List;

/**
 * @author 15988440973
 */
public class UserUpdateForm {
    private Long userId;
    private String username;
    private Integer depName;
    private String phoneNumber;
    private List<Integer> roleName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDepName() {
        return depName;
    }

    public void setDepName(Integer depName) {
        this.depName = depName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getRoleName() {
        return roleName;
    }

    public void setRoleName(List<Integer> roleName) {
        this.roleName = roleName;
    }
}
