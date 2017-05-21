package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mstuban on 14.05.17..
 */
@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
}
