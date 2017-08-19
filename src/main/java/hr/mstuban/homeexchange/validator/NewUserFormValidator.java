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
        validateFirstNameString(newUserForm.getFirstName(), errors);
        validateLastNameString(newUserForm.getLastName(), errors);

    }

    void validateLastNameString(String lastName, Errors errors) {
        if (isBlank(lastName)) {
            errors.rejectValue("lastName", "error.user.lastName.empty", "You must enter a last name.");
        }

        if (lastName.length() > 30) {
            errors.rejectValue("lastName", "error.user.lastName.too-long", "Last name can only be long 30 characters.");
        }
    }

    void validateFirstNameString(String firstName, Errors errors){
        if (isBlank(firstName)) {
            errors.rejectValue("firstName", "error.user.firstName.empty", "You must enter a first name.");
        }

        if (firstName.length() > 30) {
            errors.rejectValue("firstName", "error.user.firstName.too-long", "First name can only be long 30 characters.");
        }
    }

    void validatePhoneNumberString(String phoneNumber, Errors errors) {

        if (isBlank(phoneNumber)) {
            errors.rejectValue("phoneNumber", "error.user.phoneNumber.empty", "You must enter a phone number.");
        }

        if (phoneNumber.length() > 20) {
            errors.rejectValue("phoneNumber", "error.user.phoneNumber.too-long", "A phone number can only be long 20 characters.");
        }

    }

    void validateEmailString(String email, Errors errors) {

        if (isBlank(email)) {
            errors.rejectValue("email", "error.user.email.empty", "You must enter an e-mail.");
        }

        if (email.length() > 30) {
            errors.rejectValue("email", "error.user.email.too-long", "An e-mail can only be long 30 characters.");
        }

    }

    void validateMatchingPasswordString(String matchingPassword, Errors errors) {

        if (isBlank(matchingPassword)) {
            errors.rejectValue("matchingPassword", "error.user.matchingPassword.empty", "You must enter a matching password.");
        }

    }

    void validatePasswordString(String password, Errors errors) {

        if (isBlank(password)) {
            errors.rejectValue("password", "error.user.password.empty", "You must enter a password.");
        }

    }

    void validateUsernameString(String username, Errors errors) {

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

    private void validateUsername(String username, Errors errors) {
        if (userService.existsByUserNameIgnoreCase(username)) {
            errors.rejectValue("username", "error.user.already-exists", "A user with that username already exists in the database.");
        }

    }

    void validatePassword(String password, String matchingPassword, Errors errors) {
        if (password.length() < 8) {
            errors.rejectValue("password", "error.password.too-short", "Entered password has to be at least 8 characters long.");
        }

        if (matchingPassword.length() < 8) {
            errors.rejectValue("matchingPassword", "error.matchingPassword.too-short", "Confirmed password has to be at least 8 characters long.");
        }

        if (!password.equals(matchingPassword)) {
            errors.rejectValue("password", "error.password.not-equal", "Entered password is not the same as the confirmed password.");
        }

    }

    private void validateEmail(String email, Errors errors) {
        if (userService.existsByEmailIgnoreCase(email)) {
            errors.rejectValue("email", "error.email.already-exists", "A user with the entered email already exists in the database.");
        }
    }

    private void validatePhoneNumber(String phoneNumber, Errors errors) {
        if (userService.existsByPhoneNumberIgnoreCase(phoneNumber)) {
            errors.rejectValue("phoneNumber", "error.phoneNumber.already-exists", "Entered phone number already exists in the database.");
        }
    }
}
