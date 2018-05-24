package com.asuis.j2ee.service.impl;

import com.asuis.j2ee.dao.RoleDao;
import com.asuis.j2ee.dao.RouterDao;
import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.model.Routes;
import com.asuis.j2ee.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @author 15988440973
 */
@Service
public class RouterServiceImpl implements RouterService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RouterDao routerDao;
    @Override
    public Result getRoutesByRole(List<GrantedAuthority> auths) {
        Result result = new Result();
        try {
            List<Routes> routes = roleDao.getRoutesByRoleName(auths);
            result.setCode(200);
            result.setData(routes);
        } catch (Exception e) {
            result.setCode(501);
            result.setMessage("error");
        }
        return result;
    }
}
