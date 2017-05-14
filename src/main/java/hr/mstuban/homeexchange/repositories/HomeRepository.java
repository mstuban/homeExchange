package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Home;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mstuban on 14.05.17..
 */
public interface HomeRepository extends CrudRepository<Home, Long> {
}
