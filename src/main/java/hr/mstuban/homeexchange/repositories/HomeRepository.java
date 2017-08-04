package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by mstuban on 14.05.17..
 */
@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {

    Home findByName(String name);

    boolean existsByNameIgnoreCase(String name);

    Integer countByUser_UserId(Long id);

    Home findByHomeId(Long id);

    @Modifying
    @Query("update Home h set h.name = ?1, h.sizeInSquareMeters = ?2, h.description = ?3, h.timeOfExchangeInMonths = ?4, h.type = ?5, h.available = ?6 where h.homeId = ?7")
    void editHome(String name, Integer sizeInSquareMeters, String description, Integer timeOfExchangeInMonths, String type, boolean available, Long homeId);

    Integer countHomeByUser(User user);

    Home findFirstByTimeOfExchangeInMonthsIsNotNullOrderByTimeOfExchangeInMonthsDesc();

    boolean existsByHomeId(Long id);

}
