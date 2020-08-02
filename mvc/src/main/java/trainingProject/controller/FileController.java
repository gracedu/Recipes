package trainingProject.controller;

import com.amazonaws.auth.policy.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trainingProject.service.AWSS3Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        logger.info("test file name: " + file.getOriginalFilename());
        String newFileName = null;
        try {
            newFileName = awss3Service.uploadFile("grace-s3-bucket",file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;

    }

    //same function as the below method, they both return the url of the object
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getObject(@RequestParam("fileName") String s3Key) {
        return awss3Service.getFileUrl(s3Key);
    }

//    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
//    public ResponseEntity<String> getFileUrl(@PathVariable String fileName, HttpServletRequest request) {
//        Resource resource = null;
//        String message = "The file doesn't exist";
//        ResponseEntity responseEntity;
//        try {
//            String url = awss3Service.getFileUrl(fileName);
//            logger.debug(message);
//            responseEntity = ResponseEntity.status(HttpServletResponse.SC_OK).body(url);
//        }
//        catch (Exception e) {
//            responseEntity = ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
//            logger.debug(e.getMessage());
//        }
//        return responseEntity;
//    }

}
