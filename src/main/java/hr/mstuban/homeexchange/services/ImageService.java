package hr.mstuban.homeexchange.services;

import hr.mstuban.homeexchange.domain.Image;

import java.util.List;

/**
 * Created by mstuban on 31/07/17.
 */
public interface ImageService {

    void store(Image image);

    Image findImageByHome(Long id);

    void delete(Long id);

    List<Image> findAll();

}
