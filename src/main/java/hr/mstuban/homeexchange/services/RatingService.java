package hr.mstuban.homeexchange.services;

import hr.mstuban.homeexchange.domain.Rating;

import java.util.List;

/**
 * Created by mstuban on 04/08/17.
 */

public interface RatingService {

    public List<Rating> getRatingsByHomeId(Long id);

    public Rating save(Rating rating);

    public List<Rating> getRatingsByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndHomeId(String username, Long id);
}
