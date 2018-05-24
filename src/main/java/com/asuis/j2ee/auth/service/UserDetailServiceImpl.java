package com.asuis.j2ee.auth.service;

import com.asuis.j2ee.auth.JwtUserFactory;
import com.asuis.j2ee.dao.RoleDao;
import com.asuis.j2ee.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 15988440973
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.asuis.j2ee.model.User user = userDao.getUserByUsername(username);
        if (user==null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<String> auths = roleDao.getAuthsByUserId(user.getUserId());
            return JwtUserFactory.create(user,auths);
        }
    }
}
