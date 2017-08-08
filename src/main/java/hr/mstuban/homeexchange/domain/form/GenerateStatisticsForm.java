package hr.mstuban.homeexchange.domain.form;

import static org.codehaus.plexus.util.StringUtils.isBlank;

/**
 * Created by mstuban on 05/08/17.
 */
public class GenerateStatisticsForm {

    private String country;

    private Integer timeOfExchangeInMonthsMin;

    private Integer timeOfExchangeInMonthsMax;

    private Integer sizeInSquareMetersMin;

    private Integer sizeInSquareMetersMax;

    private boolean available;

    private String city;

    private String type;

    private Double hospitalityMin;

    private Double hospitalityMax;

    private Double cleanlinessMin;

    private Double cleanlinessMax;

    private Double comfortMin;

    private Double comfortMax;

    private Double facilitiesMin;

    private Double facilitiesMax;

    private Double valueForMoneyMin;

    private Double valueForMoneyMax;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getHospitalityMin() {
        return hospitalityMin;
    }

    public void setHospitalityMin(Double hospitalityMin) {
        this.hospitalityMin = hospitalityMin;
    }

    public Double getHospitalityMax() {
        return hospitalityMax;
    }

    public void setHospitalityMax(Double hospitalityMax) {
        this.hospitalityMax = hospitalityMax;
    }

    public Double getCleanlinessMin() {
        return cleanlinessMin;
    }

    public void setCleanlinessMin(Double cleanlinessMin) {
        this.cleanlinessMin = cleanlinessMin;
    }

    public Double getCleanlinessMax() {
        return cleanlinessMax;
    }

    public void setCleanlinessMax(Double cleanlinessMax) {
        this.cleanlinessMax = cleanlinessMax;
    }

    public Double getComfortMin() {
        return comfortMin;
    }

    public void setComfortMin(Double comfortMin) {
        this.comfortMin = comfortMin;
    }

    public Double getComfortMax() {
        return comfortMax;
    }

    public void setComfortMax(Double comfortMax) {
        this.comfortMax = comfortMax;
    }

    public Double getFacilitiesMin() {
        return facilitiesMin;
    }

    public void setFacilitiesMin(Double facilitiesMin) {
        this.facilitiesMin = facilitiesMin;
    }

    public Double getFacilitiesMax() {
        return facilitiesMax;
    }

    public void setFacilitiesMax(Double facilitiesMax) {
        this.facilitiesMax = facilitiesMax;
    }

    public Double getValueForMoneyMin() {
        return valueForMoneyMin;
    }

    public void setValueForMoneyMin(Double valueForMoneyMin) {
        this.valueForMoneyMin = valueForMoneyMin;
    }

    public Double getValueForMoneyMax() {
        return valueForMoneyMax;
    }

    public void setValueForMoneyMax(Double valueForMoneyMax) {
        this.valueForMoneyMax = valueForMoneyMax;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTimeOfExchangeInMonthsMin() {
        return timeOfExchangeInMonthsMin;
    }

    public void setTimeOfExchangeInMonthsMin(Integer timeOfExchangeInMonthsMin) {
        this.timeOfExchangeInMonthsMin = timeOfExchangeInMonthsMin;
    }

    public Integer getTimeOfExchangeInMonthsMax() {
        return timeOfExchangeInMonthsMax;
    }

    public void setTimeOfExchangeInMonthsMax(Integer timeOfExchangeInMonthsMax) {
        this.timeOfExchangeInMonthsMax = timeOfExchangeInMonthsMax;
    }

    public Integer getSizeInSquareMetersMin() {
        return sizeInSquareMetersMin;
    }

    public void setSizeInSquareMetersMin(Integer sizeInSquareMetersMin) {
        this.sizeInSquareMetersMin = sizeInSquareMetersMin;
    }

    public Integer getSizeInSquareMetersMax() {
        return sizeInSquareMetersMax;
    }

    public void setSizeInSquareMetersMax(Integer sizeInSquareMetersMax) {
        this.sizeInSquareMetersMax = sizeInSquareMetersMax;
    }

    public boolean isFormBlank() {
        if (!isBlank(country)) {
            return false;
        }

        if (!isBlank(city)) {
            return false;
        }

        if (!isBlank(type)) {
            return false;
        }

        if (timeOfExchangeInMonthsMin != null || timeOfExchangeInMonthsMax != null || sizeInSquareMetersMin != null || sizeInSquareMetersMax != null || hospitalityMin != null || hospitalityMax != null || facilitiesMin != null || facilitiesMax != null || cleanlinessMin != null || cleanlinessMax != null || comfortMin != null || comfortMax != null || valueForMoneyMin != null || valueForMoneyMax != null) {
            return false;
        }

        return true;

    }

}
