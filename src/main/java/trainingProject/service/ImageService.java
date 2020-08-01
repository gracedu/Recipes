package trainingProject.service;

import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import trainingProject.model.Image;
import trainingProject.repository.ImageDao;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    @Autowired
    private AWSS3Service fileService;
    @Autowired
    private ImageDao imageDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public Image saveImage(MultipartFile file) {
        if (file == null || file.isEmpty()) throw new ServiceException("Image is empty");
        String originalFileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFileName);
        String homeDir = "/Users/jinxiadu/Desktop/Luffy.jpg"; //catalin??
        Image image = new Image();
        String s3Key = FilenameUtils.getBaseName(originalFileName) + "_" + image.getUuid() + extension;
        File localFile = new File(homeDir + s3Key);
        try {
            file.transferTo(localFile);
            fileService.putObject(s3Key, localFile);
            S3Object s3Object = fileService.getObject(s3Key);
            image.setUrl(fileService.getFileUrl(s3Object.getKey()));
            image.setExtension(extension);
            return imageDao.save(image);
        }
        catch (IOException e) {
            logger.error("can't find image", e);
            return null;
        }
    }
}
