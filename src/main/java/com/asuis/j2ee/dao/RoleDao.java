package com.asuis.j2ee.dao;

import com.asuis.j2ee.model.RoleRoutes;
import com.asuis.j2ee.model.Routes;
import com.asuis.j2ee.vo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author 15988440973
 */
public interface RoleDao {
    /**
     * used to get auth by userId
     * @param userId userId
     * @return auths for list*/
    List<String> getAuthsByUserId(Long userId);
    List<Routes> getRoutesByRoleName(@Param("roles") List<GrantedAuthority> roles);
    List<Role> getRoleList();
    int deleteRolesByUserId(Long userId);
    List<RoleRoutes> getRoleRoutesByRoleId(Integer roleId);
}
