package com.asuis.j2ee.form;

import java.util.List;

/**
 * @author 15988440973
 */
public class UserListForm {
    private Integer pageNum;
    private Integer pageSize;
    private List<String> key;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }
}
