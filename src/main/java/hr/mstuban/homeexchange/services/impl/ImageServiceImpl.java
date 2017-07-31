package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.Image;
import hr.mstuban.homeexchange.repositories.ImageRepository;
import hr.mstuban.homeexchange.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mstuban on 31/07/17.
 */
@Component
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    @Override
    public void store(Image image) {
        imageRepository.save(image);
    }
}
