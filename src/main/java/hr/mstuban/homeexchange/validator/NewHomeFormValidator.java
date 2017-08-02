package hr.mstuban.homeexchange.validator;

import hr.mstuban.homeexchange.domain.form.NewHomeForm;
import hr.mstuban.homeexchange.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Created by mstuban on 01/08/17.
 */

@Component
public class NewHomeFormValidator implements Validator {

    @Autowired
    private HomeService homeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewHomeForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewHomeForm newHomeForm = (NewHomeForm) target;

        validateName(newHomeForm.getName(), errors);
        validateDescription(newHomeForm.getDescription(), errors);

    }

    private void validateName(String name, Errors errors) {
        if (homeService.existsByNameIgnoreCase(name)) {
            errors.rejectValue("name", "error.home.name.already-exists", "Home by that name already exists in the database.");
        }

    }

    void validateDescription(String description, Errors errors) {
        if (description.length() > 100) {
            errors.rejectValue("description", "error.description.too-long", "Description can only be long 100 characters.");
        }
    }
}
