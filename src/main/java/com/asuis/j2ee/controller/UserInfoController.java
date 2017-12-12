package com.asuis.j2ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 15988440973
 */
@Controller("UserInfoController")
public class UserInfoController {
    @RequestMapping("/hello")
    public String home() {
        return "index";
    }
}
