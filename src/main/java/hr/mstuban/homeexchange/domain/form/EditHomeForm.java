package hr.mstuban.homeexchange.domain.form;

/**
 * Created by mstuban on 01/08/17.
 */

public class EditHomeForm {

    private Long id;

    private String name;

    private String street;

    private String city;

    private Long postalCode;

    private String country;

    private Integer sizeInSquareMeters;

    private String type;

    private String description;

    private boolean available;

    private Integer timeOfExchangeInMonths;
/*
    @NotNull
    @NotEmpty
    private MultipartFile imageFile;*/

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

    public Integer getSizeInSquareMeters() {
        return sizeInSquareMeters;
    }

    public void setSizeInSquareMeters(Integer sizeInSquareMeters) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

/*    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }*/
}

