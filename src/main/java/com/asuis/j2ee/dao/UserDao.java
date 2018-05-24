package com.asuis.j2ee.dao;

import com.asuis.j2ee.domain.DepUser;
import com.asuis.j2ee.domain.User;
import com.asuis.j2ee.vo.RoleUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 15988440973
 */
public interface UserDao {
    /**
     * use to user login
     * @param username username
     * @param password password
     * @return used show user to foreground*/
    User getUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);


    /**
     * get user details
     * @param username username
     * @return table*/
    com.asuis.j2ee.model.User getUserByUsername(@Param("username") String username);

    /**
     * @param username username
     * @return user
     */
    com.asuis.j2ee.model.User getUserInfoByUsername(String username);

    List<RoleUser> getUserListByKey(@Param("keys") List<String> key);

    List<String> getRolesByUser(@Param("userId") Long userId);

}
