package hr.mstuban.homeexchange.domain;

/**
 * Created by mstuban on 14.05.17..
 */

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "HOME")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "HOME_SEQUENCE", sequenceName = "HOME_SEQUENCE")
    @Column(name = "HOME_ID", nullable = false)
    private Long homeId;

    @Column(name = "NAME")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

    @Column(name = "TIME_OF_EXCHANGE_IN_MONTHS")
    private Integer timeOfExchangeInMonths;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "RATING_ID")
    private List<Rating> ratings;

    @Transient
    private Map<String, Double> averageHomeRatings;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeOfExchangeInMonths() {
        return timeOfExchangeInMonths;
    }

    public void setTimeOfExchangeInMonths(Integer timeOfExchangeInMonths) {
        this.timeOfExchangeInMonths = timeOfExchangeInMonths;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Map<String, Double> getAverageHomeRatings() {
        return averageHomeRatings;
    }

    public void setAverageHomeRatings(Map<String, Double> averageHomeRatings) {
        this.averageHomeRatings = averageHomeRatings;
    }
}
