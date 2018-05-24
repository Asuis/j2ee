package com.asuis.j2ee.dao;

import com.asuis.j2ee.domain.Routes;

import java.util.List;

/**
 * @author 15988440973
 */
public interface RouterDao {
    public List<Routes> findRouterByPid(Integer pid);
}
