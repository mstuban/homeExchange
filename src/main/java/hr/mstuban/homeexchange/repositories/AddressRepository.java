package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(" SELECT a from Address a WHERE LOWER(a.country) LIKE LOWER(CONCAT('%',:searchParameter,'%')) and a.home.available = true " +
            " or LOWER(a.city) LIKE LOWER(CONCAT('%',:searchParameter,'%')) and a.home.available = true " +
            " or LOWER(a.street) LIKE LOWER(CONCAT('%',:searchParameter,'%')) and a.home.available = true ")
    List<Address> findAddressesBySearchParameter(@Param("searchParameter") String searchParameter);

}
