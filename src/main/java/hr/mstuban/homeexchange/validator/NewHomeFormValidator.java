package hr.mstuban.homeexchange.validator;

import hr.mstuban.homeexchange.domain.form.NewHomeForm;
import hr.mstuban.homeexchange.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.codehaus.plexus.util.StringUtils.isBlank;


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
        validateDescriptionString(newHomeForm.getDescription(), errors);
        validateNameString(newHomeForm.getName(), errors);
        validateStreetString(newHomeForm.getStreet(), errors);
        validateCountryString(newHomeForm.getCountry(), errors);
        validateCityString(newHomeForm.getCity(), errors);
        validatePostalCodeLong(newHomeForm.getPostalCode(), errors);
        validateSizeInSquareMetersInteger(newHomeForm.getSizeInSquareMeters(), errors);
        validateTimeOfExchangeInMonthsInteger(newHomeForm.getTimeOfExchangeInMonths(), errors);

    }

    void validateDescriptionString(String description, Errors errors) {
        if (isBlank(description)) {
            errors.rejectValue("description", "error.home.description.empty", "You must enter a description.");
        }

        if (description.length() > 100) {
            errors.rejectValue("description", "error.home.description.too-long", "Description can only be long 100 characters.");
        }
    }

    void validateTimeOfExchangeInMonthsInteger(Integer timeOfExchangeInMonths, Errors errors) {
        if (isBlank(timeOfExchangeInMonths.toString())) {
            errors.rejectValue("timeOfExchangeInMonths", "error.home.timeOfExchangeInMonths.empty", "You must enter size in square meters.");
        }
        if (timeOfExchangeInMonths > 36) {
            errors.rejectValue("timeOfExchangeInMonths", "error.home.timeOfExchangeInMonths.too-long", "You cannot exchange a house for more than 3 years.");
        }

        if (timeOfExchangeInMonths <= 0) {
            errors.rejectValue("timeOfExchangeInMonths", "error.home.timeOfExchangeInMonths.not-valid", "Time of exchange can't be zero or less than zero.");
        }
    }

    void validateSizeInSquareMetersInteger(Integer sizeInSquareMeters, Errors errors) {
        if (isBlank(sizeInSquareMeters.toString())) {
            errors.rejectValue("sizeInSquareMeters", "error.home.sizeInSquareMeters.empty", "You must enter time of exchange.");
        }
        if (sizeInSquareMeters < 0) {
            errors.rejectValue("sizeInSquareMeters", "error.home.sizeInSquareMeters.not-valid", "Size in square meters can't be zero or less than zero.");
        }

    }

    void validateNameString(String name, Errors errors) {
        if (isBlank(name)) {
            errors.rejectValue("name", "error.home.name.empty", "You must enter a home name.");
        }
        if (name.length() > 50) {
            errors.rejectValue("name", "error.home.description.too-long", "Name can only be long 50 characters.");
        }

    }

    void validateCityString(String city, Errors errors) {
        if (isBlank(city)) {
            errors.rejectValue("city", "error.home.city.empty", "You must enter a city.");
        }
        if (city.length() > 50) {
            errors.rejectValue("city", "error.home.city.too-long", "City can only be long 50 characters.");
        }
    }

    void validatePostalCodeLong(Long postalCode, Errors errors) {
        if (isBlank(postalCode.toString())) {
            errors.rejectValue("postalCode", "error.home.postalCode.empty", "You must enter a postal code.");
        }
        if (postalCode > 10000000000L) {
            errors.rejectValue("postalCode", "error.home.postalCode.too-long", "It is impossible to have that postal code. At least not in this universe.");
        }
    }

    void validateCountryString(String country, Errors errors) {
        if (isBlank(country)) {
            errors.rejectValue("country", "error.home.country.empty", "You must enter a country.");
        }
        if (country.length() > 50) {
            errors.rejectValue("country", "error.home.description.too-long", "Country can only be long 50 characters.");
        }
    }

    void validateStreetString(String street, Errors errors) {
        if (isBlank(street)) {
            errors.rejectValue("street", "error.home.street.empty", "You must enter a street.");
        }
        if (street.length() > 60) {
            errors.rejectValue("street", "error.home.description.too-long", "Street can only be long 60 characters.");
        }
    }

    private void validateName(String name, Errors errors) {
        if (homeService.existsByNameIgnoreCase(name)) {
            errors.rejectValue("name", "error.home.name.already-exists", "Home by that name already exists in the database.");
        }

    }

}
