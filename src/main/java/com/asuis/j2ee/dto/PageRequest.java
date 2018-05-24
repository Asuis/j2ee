package com.asuis.j2ee.dto;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author 15988440973
 */
public class PageRequest extends AbstractPageRequest {

    private Sort sort;


    public PageRequest(int page, int size,Sort sort) {
        super(page, size);
        this.sort = sort;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Pageable next() {
        return new PageRequest(this.getPageNumber()+1,this.getPageSize(),this.sort);
    }

    @Override
    public Pageable previous() {
        if (this.getPageNumber()<=1) {
            return this.first();
        }
        return new PageRequest(this.getPageNumber()-1,this.getPageSize(),this.sort);
    }

    @Override
    public Pageable first() {
        return  new PageRequest(1,this.getPageSize(),this.sort);
    }
}
