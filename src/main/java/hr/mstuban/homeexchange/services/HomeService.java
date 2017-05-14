package hr.mstuban.homeexchange.services;

import hr.mstuban.homeexchange.domain.Home;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
public interface HomeService {
    List<Home> findAll();
}
