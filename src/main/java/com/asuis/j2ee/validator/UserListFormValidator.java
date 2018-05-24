package com.asuis.j2ee.validator;

import com.asuis.j2ee.form.UserListForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 15988440973
 */
public class UserListFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserListForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserListForm userListForm = (UserListForm) o;

    }
}
