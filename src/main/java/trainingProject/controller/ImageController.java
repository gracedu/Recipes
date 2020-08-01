package trainingProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import trainingProject.model.Image;
import trainingProject.service.ImageService;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    public Image uploadImage(MultipartFile image) {
        Image result = imageService.saveImage(image);
        return result;
    }
}
