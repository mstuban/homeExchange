package hr.mstuban.homeexchange.services;

import hr.mstuban.homeexchange.domain.Home;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
public interface HomeService {
    List<Home> findAll();
    Home save(Home home);
    void deleteById(Long id);
    Home findById(Long id);
}
