package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.repositories.HomeRepository;
import hr.mstuban.homeexchange.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Home save(Home home) {
        return homeRepository.save(home);
    }

    @Override
    public void deleteById(Long id) {
        homeRepository.delete(id);
    }

    @Override
    public Home findById(Long id) {
        return homeRepository.findByHomeId(id);
    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return homeRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public Integer countByUser_UserId(Long id) {
        return homeRepository.countByUser_UserId(id);
    }

    @Transactional
    @Override
    public void editHome(String name, Integer sizeInSquareMeters, String description, Integer timeOfExchangeInMonths, String type, boolean available, Long homeId) {
        homeRepository.editHome(name, sizeInSquareMeters, description, timeOfExchangeInMonths, type, available, homeId);
    }

}
