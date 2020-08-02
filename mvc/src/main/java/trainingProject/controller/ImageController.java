package trainingProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import trainingProject.model.Image;
import trainingProject.service.ImageService;

import java.awt.*;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Image uploadImage(@RequestParam("image") MultipartFile image) {
        Image result = imageService.saveImage(image);
        return result;
    }
}
