package com.asuis.j2ee.form;

/**
 * @author 15988440973
 */
public class LoginForm {
    private String username;
    private String password;
    private boolean isRemember;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean remember) {
        isRemember = remember;
    }
}
