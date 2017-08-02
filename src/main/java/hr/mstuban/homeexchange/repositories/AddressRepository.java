package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(" SELECT a from Address a WHERE LOWER(a.country) LIKE LOWER(CONCAT('%',:searchParameter,'%'))" +
            " or LOWER(a.city) LIKE LOWER(CONCAT('%',:searchParameter,'%'))" +
            " or LOWER(a.street) LIKE LOWER(CONCAT('%',:searchParameter,'%'))")
    List<Address> findAddressesBySearchParameter(@Param("searchParameter") String searchParameter);

    Address findByAddressId(Long addressId);

    List<Address> getAddressesByHome_User_UserName(String username);

    void deleteByHome_HomeId(Long id);

    @Modifying
    @Query("update Address a set a.city = ?1, a.country = ?2, a.postalCode = ?3, a.street = ?4 where a.addressId = ?5")
    void editAddress(String city, String country, Long postalCode, String street, Long addressId);

    Address findByHome_HomeId(Long id);

}
