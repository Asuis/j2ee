package com.asuis.j2ee.validator;

import com.asuis.j2ee.form.RegisterForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 15988440973
 */
@Component
public class RegisterFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
