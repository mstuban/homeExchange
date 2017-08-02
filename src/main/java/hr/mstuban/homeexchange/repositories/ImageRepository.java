package hr.mstuban.homeexchange.repositories;

import hr.mstuban.homeexchange.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mstuban on 31/07/17.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findImageByHome_HomeId(Long id);

}