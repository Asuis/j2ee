package com.asuis.j2ee.service;

import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.model.Routes;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author 15988440973
 */
public interface RouterService {
    Result getRoutesByRole(List<GrantedAuthority> auths);
}
