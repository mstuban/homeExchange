package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select a.role from UserRole a, User b where b.userName=?1 and a.user.userId=b.userId")
    List<String> findRolesByUsername(String username);
}