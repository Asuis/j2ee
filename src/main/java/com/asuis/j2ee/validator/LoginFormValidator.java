package com.asuis.j2ee.validator;

import com.asuis.j2ee.form.LoginForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * @author 15988440973
 */
@Component
public class LoginFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginForm loginForm = (LoginForm) o;
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if (username!=null&&password!=null) {
            if (!Pattern.matches("^\\w{6,25}+$",password))
            {
                errors.reject("密码只能输入6-25个字母、数字、下划线");
            }
            if (!Pattern.matches("^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$",username))
            {
                errors.reject("用户名不能含有汉字、数字、字母、下划线以外的字符");
            }
        } else {
            errors.reject("用户名或密码不能为空");
        }
    }
}
