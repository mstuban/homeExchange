package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Query("SELECT a from Address a WHERE LOWER(a.city) = LOWER(:parameter) or LOWER(a.street) = LOWER(:parameter) or LOWER(a.country) = LOWER(:parameter)")
    List<Address> findByStreetOrCityOrCountry(@Param("parameter") String parameter);

}
