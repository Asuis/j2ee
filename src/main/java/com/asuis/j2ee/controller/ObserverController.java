package com.asuis.j2ee.controller;

import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.service.ObserverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 15988440973
 */
@RestController
@RequestMapping("/observer")
@PreAuthorize("hasRole('ROLE_CONTROL') or hasRole('ROLE_ADMIN')")
public class ObserverController {
    @Autowired
    private ObserverService observerService;

    @RequestMapping("/sign_data")
    public Result getSignData(@RequestParam(value = "pageNum",required = false) Integer pageNum,@RequestParam(value = "pageSize",required = false) Integer pagesize) {
        if (pageNum==null) {
            pageNum = 1;
        } else if (pageNum<1) {
            pageNum = 1;
        }
        if (pagesize==null) {
            pagesize = 10;
        } else if (pagesize<1) {
            pagesize = 10;
        }
        return observerService.getLoginRecode(pageNum,pagesize);
    }
    @RequestMapping("/action_data")
    public Result getActionData(@RequestParam(value = "pageNum",required = false) Integer pageNum,@RequestParam(value = "pageSize",required = false) Integer pagesize) {
        if (pageNum==null) {
            pageNum = 1;
        } else if (pageNum<1) {
            pageNum = 1;
        }
        if (pagesize==null) {
            pagesize = 10;
        } else if (pagesize<1) {
            pagesize = 10;
        }
        return observerService.getActionRecode(pageNum,pagesize);
    }
    @RequestMapping("/sale_data")
    public Result getSaleData () {
        return null;
    }
    @RequestMapping("/get_total_sale")
    public Result getTotalSale() {
        return null;
    }
    @RequestMapping("/get_sale_rank")
    public Result getSaleRank () {
        return null;
    }
}
