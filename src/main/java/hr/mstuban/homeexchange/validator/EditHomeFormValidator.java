package hr.mstuban.homeexchange.validator;


import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.form.EditHomeForm;
import hr.mstuban.homeexchange.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by mstuban on 01/08/17.
 */
@Component
public class EditHomeFormValidator extends NewHomeFormValidator implements Validator {

    @Autowired
    private HomeService homeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EditHomeForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EditHomeForm editHomeForm = (EditHomeForm) target;

        validateEditedName(editHomeForm.getName(), errors, editHomeForm.getId());
        validateDescription(editHomeForm.getDescription(), errors);

    }

    private void validateEditedName(String name, Errors errors, Long id) {

        Home home = homeService.findById(id);
        String oldName = home.getName();

        if (homeService.existsByNameIgnoreCase(name) && !name.equals(oldName)) {
            errors.rejectValue("name", "error.name.already-exists", "A home with that name already exists in the database.");
        }
    }


}
