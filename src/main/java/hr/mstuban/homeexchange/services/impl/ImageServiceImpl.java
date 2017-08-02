package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.Image;
import hr.mstuban.homeexchange.repositories.ImageRepository;
import hr.mstuban.homeexchange.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Image findImageByHome(Long id) {
        return imageRepository.findImageByHome_HomeId(id);
    }

    @Override
    public void delete(Long id){
        imageRepository.delete(id);
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

}
