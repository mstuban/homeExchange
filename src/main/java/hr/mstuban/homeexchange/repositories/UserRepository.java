package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserNameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneNumberIgnoreCase(String phoneNumber);

    User findByUserName(String username);

    @Modifying
    @Query("update User u set u.userName = ?1, u.firstName = ?2, u.lastName = ?3 , u.password = ?4, u.email = ?5, u.phoneNumber = ?6 where u.userId = ?7")
    void editUser(String userName, String firstName, String lastName, String encryptedPassword, String email, String phoneNumber, Long id);

}