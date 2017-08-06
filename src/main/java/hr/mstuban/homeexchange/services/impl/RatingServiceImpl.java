package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.Rating;
import hr.mstuban.homeexchange.repositories.RatingRepository;
import hr.mstuban.homeexchange.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mstuban on 04/08/17.
 */
@Component
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getRatingsByHomeId(Long id) {
        return ratingRepository.getRatingsByHome_HomeId(id);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatingsByUsername(String username) {
        return ratingRepository.getRatingsByUser_UserName(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return ratingRepository.existsByUser_UserName(username);
    }

    @Override
    public boolean existsByUsernameAndHomeId(String username, Long id) {
        return ratingRepository.existsByUser_UserNameAndHome_HomeId(username, id);
    }

    @Override
    public Rating findByHomeIdAndUsername(Long id, String username) {
        return ratingRepository.findByHome_HomeIdAndUserUserName(id, username);
    }

    @Override
    public List<Object[]> getCommentsByHome(Home home) {
        return ratingRepository.getCommentsByHome(home);
    }

    @Override
    public Rating findById(Long id) {
        return ratingRepository.findOne(id);
    }

    @Override
    public void deleteById(Long id) {
        ratingRepository.delete(id);
    }

}

