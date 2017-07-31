package hr.mstuban.homeexchange.domain;

import javax.persistence.*;

/*
*
 * Created by jt on 12/18/15.
*/


@Entity
@Table(name = "USER_ROLES")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_ROLE_SEQUENCE", sequenceName = "USER_ROLE_SEQUENCE")
    @Column(name = "USER_ROLE_ID", nullable = false)
    private Long userroleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

    @Column(name = "ROLE")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(Long userroleid) {
        this.userroleid = userroleid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
