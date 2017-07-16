package hr.mstuban.homeexchange.domain;

/**
 * Created by mstuban on 14.05.17..
 */

import javax.persistence.*;

@Entity
@Table(name = "HOME")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "HOME_SEQUENCE", sequenceName = "HOME_SEQUENCE")
    @Column(name = "HOME_ID", nullable = false)
    private Long homeId;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "SIZE_IN_SQUARE_METERS")
    private Integer sizeInSquareMeters;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AVAILABLE")
    private boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

    public Home() {
    }

    public Long getHomeId() {
        return homeId;
    }

    public void setHomeId(Long id) {
        this.homeId = id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
