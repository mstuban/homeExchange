package hr.mstuban.homeexchange.validator;


import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.domain.form.EditUserForm;
import hr.mstuban.homeexchange.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by mstuban on 01/08/17.
 */
@Component
public class EditUserFormValidator extends NewUserFormValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EditUserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EditUserForm editUserForm = (EditUserForm) target;

        validateEditedUsername(editUserForm.getUsername(), errors, editUserForm.getId());
        validatePassword(editUserForm.getPassword(), editUserForm.getMatchingPassword(), errors);
        validateEditedEmail(editUserForm.getEmail(), errors, editUserForm.getId());
        validateEditedPhoneNumber(editUserForm.getPhoneNumber(), errors, editUserForm.getId());

    }

    private void validateEditedUsername(String username, Errors errors, Long id) {

        User user = userService.getById(id);
        String oldUsername = user.getUserName();

        if (userService.existsByUserNameIgnoreCase(username) && !username.equals(oldUsername)) {
            errors.rejectValue("username", "error.user.already-exists", "A user with that username already exists in the database.");
        }
    }

    private void validateEditedEmail(String email, Errors errors, Long id) {

        User user = userService.getById(id);
        String oldEmail = user.getEmail();

        if (userService.existsByEmailIgnoreCase(email) && !email.equals(oldEmail)) {
            errors.rejectValue("email", "error.email.already-exists", "A user with that email already exists in the database.");
        }
    }

    private void validateEditedPhoneNumber(String phoneNumber, Errors errors, Long id) {

        User user = userService.getById(id);
        String oldPhoneNumber = user.getPhoneNumber();

        if (userService.existsByPhoneNumberIgnoreCase(phoneNumber) && !phoneNumber.equals(oldPhoneNumber)) {
            errors.rejectValue("phoneNumber", "error.phoneNumber.already-exists", "A user with that phone number already exists in the database.");
        }
    }
}
