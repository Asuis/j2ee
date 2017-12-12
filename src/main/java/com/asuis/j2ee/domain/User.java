package com.asuis.j2ee.domain;

import java.io.Serializable;

/**
 * @author 15988440973
 */
public class User implements Serializable {
    private String username;
    private String userId;
    private String avatar;

    public User(String username, String userId, String avatar) {
        this.username = username;
        this.userId = userId;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
