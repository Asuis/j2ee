package com.asuis.j2ee.auth;

import com.asuis.j2ee.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15988440973
 * 试着尝试用java8流语法
 */
public class JwtUserFactory {
    private JwtUserFactory() {}
    public static JwtUserDetails create(User user, List<String> auths) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (String auth:auths) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(auth);
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        boolean accountNonExpired = false;
        boolean enabled = false;
        if (!"0".equals(user.getIsAccountNonExpired())) {
            accountNonExpired = true;
        }
        if (!"0".equals(user.getIsEnabled())) {
            accountNonExpired = true;
        }
        return new JwtUserDetails(
                user.getUserId(),
                null,
                user.getUsername(),
                grantedAuthorities,
                accountNonExpired,
                false,
                true,
                enabled
        );
    }
}
