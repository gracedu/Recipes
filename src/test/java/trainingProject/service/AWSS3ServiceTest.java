package trainingProject.service;

import com.amazonaws.services.s3.model.Bucket;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import trainingProject.ApplicationBootstrap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AWSS3ServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AWSS3Service awsS3Service;

    @Test
    public void testCreateBucket() {
        String bucketName = "grace-s3-bucket100";
        Bucket bucket = awsS3Service.createBucket(bucketName);
        Assert.assertNotNull(bucket);
    }
}
