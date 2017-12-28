package com.asuis.j2ee.controller;

import com.asuis.j2ee.dao.UserDao;
import com.asuis.j2ee.dto.Result;
import com.asuis.j2ee.form.LoginForm;
import com.asuis.j2ee.form.RegisterForm;
import com.asuis.j2ee.service.UserService;
import com.asuis.j2ee.validator.LoginFormValidator;
import com.asuis.j2ee.validator.RegisterFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author 15988440973
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginFormValidator loginFormValidator;
    @Autowired
    private RegisterFormValidator registerFormValidator;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@ModelAttribute("user_form")LoginForm loginForm, BindingResult loginFormValidateResult) {

        Result result;

        loginFormValidator.validate(loginForm,loginFormValidateResult);
        if (loginFormValidateResult.hasErrors()) {
            result = new Result();
            result.setCode(504);
            result.setData(loginFormValidateResult.getAllErrors());
        } else {
            result = userService.loginByUsernameAndPassword(loginForm);
        }


        return result;
    }
    @PostMapping(value = "/register")
    public Result register(@ModelAttribute("register_form")RegisterForm registerForm,BindingResult registerFormValidateResult) {
        Result result;
        registerFormValidator.validate(registerForm,registerFormValidateResult);
        if (registerFormValidateResult.hasErrors()) {
            result = new Result();
            result.setCode(504);
            result.setData(registerFormValidateResult.getAllErrors());
        } else {
            result = userService.register(registerForm);
        }
        return result;
    }
}
