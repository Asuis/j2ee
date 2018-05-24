package com.asuis.j2ee;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asuis.j2ee.domain.Routes;
import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    AuditorService auditorService;
    @Autowired
    ManagerService managerService;
    @Autowired
    ObserverService observerService;
    @Autowired
    RouterService routerService;
    @Autowired
    UserService userService;

    @Test
    public void auditor() {

    }

    @Test
    public void manager() {
    }

    @Test
    public void observer() {
    }

    @Test
    public void router() {
        List<GrantedAuthority> roles = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        roles.add(authority);
        Result routes = routerService.getRoutesByRole(roles);
        String json = JSON.toJSONString(routes);
        System.out.println(json);
    }


    @Test
    public void user() {
    }
}
