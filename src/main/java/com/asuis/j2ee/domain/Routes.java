package com.asuis.j2ee.domain;

import java.util.List;

/**
 * @author 15988440973
 */
public class Routes {
    private List<Routes> childRoutes;

    private Integer id;

    private Integer pid;

    private String path;

    private String webPata;

    private String name;

    private String desc;

    private String sort;

    private String extra;

    public List<Routes> getChildRoutes() {
        return childRoutes;
    }

    public void setChildRoutes(List<Routes> childRoutes) {
        this.childRoutes = childRoutes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWebPata() {
        return webPata;
    }

    public void setWebPata(String webPata) {
        this.webPata = webPata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
