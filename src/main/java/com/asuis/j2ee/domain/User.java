package com.asuis.j2ee.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 15988440973
 */
public class User implements Serializable {
    private String username;
    private Long userId;
    private String avatar;

    public User(Long userId,String username, String avatar) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(avatar, user.avatar);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, userId, avatar);
    }
}
