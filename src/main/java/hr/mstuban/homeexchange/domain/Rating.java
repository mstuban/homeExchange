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

    @Column(name = "NUMBER_OF_STARS")
    private Integer numberOfStars;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HOME_ID")
    private Home home;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
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
}
