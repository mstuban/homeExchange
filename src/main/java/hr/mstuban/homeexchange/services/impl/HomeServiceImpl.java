package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.repositories.HomeRepository;
import hr.mstuban.homeexchange.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
@Service
public class HomeServiceImpl implements HomeService {

    private final HomeRepository homeRepository;

    @Autowired
    public HomeServiceImpl(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public List<Home> findAll() {
        return homeRepository.findAll();
    }
}
