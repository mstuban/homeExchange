package hr.mstuban.homeexchange.domain.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by mstuban on 31/07/17.
 */
public class NewHomeForm {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String street;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private Long postalCode;

    @NotNull
    @NotEmpty
    private String country;

    @NotNull
    @NotEmpty
    private String sizeInSquareMeters;

    @NotNull
    @NotEmpty
    private String type;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private boolean available;

    @NotNull
    @NotEmpty
    private Integer timeOfExchangeInMonths;

    @NotNull
    @NotEmpty
    private MultipartFile imageFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSizeInSquareMeters() {
        return sizeInSquareMeters;
    }

    public void setSizeInSquareMeters(String sizeInSquareMeters) {
        this.sizeInSquareMeters = sizeInSquareMeters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Integer getTimeOfExchangeInMonths() {
        return timeOfExchangeInMonths;
    }

    public void setTimeOfExchangeInMonths(Integer timeOfExchangeInMonths) {
        this.timeOfExchangeInMonths = timeOfExchangeInMonths;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
