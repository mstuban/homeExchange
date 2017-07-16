package hr.mstuban.homeexchange.domain;

import javax.persistence.*;

/**
 * Created by mstuban on 14.05.17..
 */

@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ADDRESS_SEQUENCE", sequenceName = "ADDRESS_SEQUENCE")
    @Column(name = "ADDRESS_ID", nullable = false)
    private Long addressId;

    @OneToOne
    @JoinColumn(name = "FRN_HOME_ID")
    private Home home;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POSTAL_CODE")
    private Long postalCode;

    public Address() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long id) {
        this.addressId = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
