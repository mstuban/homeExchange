package hr.mstuban.homeexchange.domain;

import javax.persistence.*;

/**
 * Created by mstuban on 04/08/17.
 */

@Entity
@Table(name = "RATING")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "RATING_SEQUENCE", sequenceName = "RATING_SEQUENCE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "COMFORT")
    private Integer comfort;

    @Column(name = "HOSPITALITY")
    private Integer hospitality;

    @Column(name = "CLEANLINESS")
    private Integer cleanliness;

    @Column(name = "FACILITIES")
    private Integer facilities;

    @Column(name = "VALUE_FOR_MONEY")
    private Integer valueForMoney;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HOME_ID")
    private Home home;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

    @Column(name = "COMMENT")
    private String comment;

    public Rating() {

    }

    public Rating(Integer comfort, Integer hospitality, Integer cleanliness, Integer facilities, Integer valueForMoney, Home home, User user, String comment) {
        this.comfort = comfort;
        this.hospitality = hospitality;
        this.cleanliness = cleanliness;
        this.facilities = facilities;
        this.valueForMoney = valueForMoney;
        this.home = home;
        this.user = user;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getComfort() {
        return comfort;
    }

    public void setComfort(Integer comfort) {
        this.comfort = comfort;
    }

    public Integer getHospitality() {
        return hospitality;
    }

    public void setHospitality(Integer hospitality) {
        this.hospitality = hospitality;
    }

    public Integer getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(Integer cleanliness) {
        this.cleanliness = cleanliness;
    }

    public Integer getFacilities() {
        return facilities;
    }

    public void setFacilities(Integer facilities) {
        this.facilities = facilities;
    }

    public Integer getValueForMoney() {
        return valueForMoney;
    }

    public void setValueForMoney(Integer valueForMoney) {
        this.valueForMoney = valueForMoney;
    }
}
