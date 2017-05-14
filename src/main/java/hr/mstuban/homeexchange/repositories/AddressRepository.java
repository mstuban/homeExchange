package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mstuban on 14.05.17..
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
