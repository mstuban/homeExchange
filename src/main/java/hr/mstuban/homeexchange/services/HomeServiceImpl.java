package hr.mstuban.homeexchange.services;

import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.repositories.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeRepository homeRepository;

    @Override
    public List<Home> findAll() {
        return (List<Home>) homeRepository.findAll();
    }
}
