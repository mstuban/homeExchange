package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mstuban on 04/08/17.
 */
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> getRatingsByHome_HomeId(Long id);

    List<Rating> getRatingsByUser_UserName(String username);

    boolean existsByUser_UserName(String username);

    boolean existsByUser_UserNameAndHome_HomeId(String username, Long id);

}
