package hr.mstuban.homeexchange.services;

import com.querydsl.core.types.Predicate;
import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.User;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
public interface HomeService {
    List<Home> findAll();

    Home save(Home home);

    void deleteById(Long id);

    Home findById(Long id);

    boolean existsByNameIgnoreCase(String name);

    Integer countByUser_UserId(Long id);

    void editHome(String name, Integer sizeInSquareMeters, String description, Integer timeOfExchangeInMonths, String type, boolean available, Long homeId);

    Integer countHomeByUser(User user);

    Home findHomeWithMostTimeExchanged();

    boolean existsByHomeId(Long id);

    Iterable<Home> findAll(Predicate predicate);

}
