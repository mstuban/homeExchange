package hr.mstuban.homeexchange.controller;

import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.Image;
import hr.mstuban.homeexchange.services.HomeService;
import hr.mstuban.homeexchange.services.ImageService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;

/**
 * Created by mstuban on 3.7.2017..
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {


    @Autowired
    private ImageService imageService;

    @Autowired
    private HomeService homeService;

    @PostMapping("/images/{homeId}")
    @Transactional
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @PathVariable("homeId") Long homeId,
                                   RedirectAttributes redirectAttributes) {

        try {
            Home home = homeService.findById(homeId);

            if (file.getBytes().length > 2097152) {
                throw new FileUploadException("upload.images.error.size");
            }
            if (!file.getContentType().contains("image")) {
                throw new FileUploadException("upload.images.error.type");
            }

            Image image = new Image(file.getOriginalFilename(), file.getBytes(), home, file.getContentType());
            imageService.store(image);
            home.setImage(image);

        } catch (FileUploadException ex) {
            redirectAttributes.addFlashAttribute("alert", ex.getMessage());
            return "redirect:/home/new";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("alert", ex.getMessage());
            return "redirect:/home/new";
        }

        redirectAttributes.addFlashAttribute("imageAddedSuccessfully", "You have successfully added a photo for your home!");

        return "redirect:/homes";
    }

}