package trainingProject.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import trainingProject.ApplicationBootstrap;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AWSS3ServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AWSS3Service awsS3Service;

    @Autowired
    private AmazonS3 client;

    @Test
    public void testCreateBucket() {
        String bucketName = "grace-s3-bucket1190";
        awsS3Service.createBucket(bucketName);
        verify(client, times(1)).createBucket(bucketName);
    }

//    @Test
//    public void uploadFileTest() throws IOException {
//        awsS3Service.uploadFile(new File("/Users/jinxiadu/Desktop/File1.txt"));
//        verify(client, times(1)).putObject(any(PutObjectRequest.class));
//    }
}
