package hr.mstuban.homeexchange.validator;

import hr.mstuban.homeexchange.domain.form.NewUserForm;
import hr.mstuban.homeexchange.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.codehaus.plexus.util.StringUtils.isBlank;

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

        validateUsernameString(newUserForm.getUsername(), errors);
        validatePasswordString(newUserForm.getPassword(), errors);
        validateMatchingPasswordString(newUserForm.getMatchingPassword(), errors);
        validateEmailString(newUserForm.getEmail(), errors);
        validatePhoneNumberString(newUserForm.getPhoneNumber(), errors);


    }

    public void validatePhoneNumberString(String phoneNumber, Errors errors) {

        if (isBlank(phoneNumber)) {
            errors.rejectValue("phoneNumber", "error.user.phoneNumber.empty", "You must enter a phone number.");
        }

        if (phoneNumber.length() > 20) {
            errors.rejectValue("phoneNumber", "error.user.phoneNumber.too-long", "A phone number cannot be that long.");
        }

    }

    public void validateEmailString(String email, Errors errors) {

        if (isBlank(email)) {
            errors.rejectValue("email", "error.user.email.empty", "You must enter an e-mail.");
        }

        if (email.length() > 30) {
            errors.rejectValue("email", "error.user.email.too-long", "An e-mail cannot be that long.");
        }

    }

    public void validateMatchingPasswordString(String matchingPassword, Errors errors) {

        if (isBlank(matchingPassword)) {
            errors.rejectValue("matchingPassword", "error.user.matchingPassword.empty", "You must enter a matching password.");
        }

    }

    public void validatePasswordString(String password, Errors errors) {

        if (isBlank(password)) {
            errors.rejectValue("password", "error.user.password.empty", "You must enter a password.");
        }

    }

    public void validateUsernameString(String username, Errors errors) {

        if (isBlank(username)) {
            errors.rejectValue("username", "error.user.username.empty", "You must enter a username.");
        }

        if (username.length() > 15) {
            errors.rejectValue("username", "error.user.username.too-long", "A username can only be long 15 characters.");
        }

        if (username.length() < 5) {
            errors.rejectValue("username", "error.user.username.too-short", "A username has to be at least 5 characters long.");
        }

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

        if (confirmedPassword.length() < 8) {
            errors.rejectValue("confirmedPassword", "error.confirmedPassword.too-short", "Confirmed password has to be at least 8 characters long.");
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
