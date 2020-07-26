package trainingProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import trainingProject.service.AWSS3Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AWSS3Service awss3Service;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        //logger.info("test file name: " + file.getOriginalFilename());

        try {
            awss3Service.uploadFile("grace-s3-bucket",file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();

    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String getObject(@RequestParam String s3Key) {
//        return awss3Service.getFileUrl(s3Key);
//    }


}
