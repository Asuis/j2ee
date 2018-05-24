package com.asuis.j2ee.controller;

import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * @author 15988440973
 */
@RestController
@RequestMapping("/routes")
public class RouterController {

    @Autowired
    private RouterService routerService;

    @RequestMapping("/get")
    public Result getRoutes(@RequestAttribute("Authorization")List<GrantedAuthority> roles) {
        Result result = null;
        result = routerService.getRoutesByRole(roles);
        return result;
    }
}
