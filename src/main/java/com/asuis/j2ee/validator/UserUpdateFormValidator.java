package com.asuis.j2ee.validator;

import com.asuis.j2ee.form.UserUpdateForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 15988440973
 */
public class UserUpdateFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserUpdateForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //todo
    }
}
