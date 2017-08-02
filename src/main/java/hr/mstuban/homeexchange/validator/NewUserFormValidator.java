package hr.mstuban.homeexchange.validator;

import hr.mstuban.homeexchange.domain.form.NewUserForm;
import hr.mstuban.homeexchange.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by mstuban on 31/07/17.
 */

@Component
public class NewUserFormValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewUserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewUserForm newUserForm = (NewUserForm) target;

        validateUsername(newUserForm.getUsername(), errors);
        validatePassword(newUserForm.getPassword(), newUserForm.getMatchingPassword(), errors);
        validateEmail(newUserForm.getEmail(), errors);
        validatePhoneNumber(newUserForm.getPhoneNumber(), errors);

    }

    public void validateUsername(String username, Errors errors) {
        if (userService.existsByUserNameIgnoreCase(username)) {
            errors.rejectValue("username", "error.user.already-exists", "A user with that username already exists in the database.");
        }

    }

    public void validatePassword(String password, String confirmedPassword, Errors errors) {
        if (password.length() < 8) {
            errors.rejectValue("password", "error.password.too-short", "Entered password has to be at least 8 characters long.");
        }

        if (!password.equals(confirmedPassword)) {
            errors.rejectValue("password", "error.password.not-equal", "Entered password is not the same as the confirmed password.");
        }

    }

    public void validateEmail(String email, Errors errors) {
        if (userService.existsByEmailIgnoreCase(email)) {
            errors.rejectValue("email", "error.email.already-exists", "A user with the entered email already exists in the database.");
        }
    }

    public void validatePhoneNumber(String phoneNumber, Errors errors) {
        if (userService.existsByPhoneNumberIgnoreCase(phoneNumber)) {
            errors.rejectValue("phoneNumber", "error.phoneNumber.already-exists", "Entered phone number already exists in the database.");
        }
    }
}
