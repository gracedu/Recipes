package trainingProject.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import trainingProject.ApplicationBootstrap;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AWSS3ServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AWSS3Service awsS3Service;

    @Autowired
    private AmazonS3 client;

    //  @Before

    //@After


    @Test
    public void testCreateBucket() {
        String testBucket = "testBucket";
        awsS3Service.createBucket(testBucket);
        verify(client, times(1)).createBucket(testBucket);
    }

    @Test
    public void uploadFileTest() throws IOException {
//        File file = new File("/Users/jinxiadu/Desktop/File1.txt");
//        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getInputStream()).thenReturn(mock(InputStream.class));
        when(multipartFile.getOriginalFilename()).thenReturn("testFileName");
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        awsS3Service.uploadFile(multipartFile);

        verify(client, times(1)).putObject(anyString(), anyString(),
                                          any(InputStream.class), any(ObjectMetadata.class));
    }

    @Test
    public void getFileUrlTest() throws MalformedURLException {
        String s3Key = "16fbb89d-6f98-4986-b362-afa4fa650983.txt";
        when(client.getUrl(anyString(),anyString())).thenReturn(new URL("http","xxx",123,"xxx"));
        awsS3Service.getFileUrl(s3Key);
        verify(client, times(1)).getUrl(anyString(), anyString());
    }

    @Test
    public void getObjectTest() {
        String s3Key = "16fbb89d-6f98-4986-b362-afa4fa650983.txt";
        awsS3Service.getObject(s3Key);
        verify(client, times(1)).getObject(anyString(), anyString());
    }
}
