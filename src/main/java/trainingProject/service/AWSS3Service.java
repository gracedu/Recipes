package trainingProject.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class AWSS3Service {
    private Logger logger = LoggerFactory.getLogger(getClass());

    AmazonS3 amazonS3;
    public AWSS3Service(@Autowired AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }
/*
    public void setAmazonS3() {
        this.amazonS3 = amazonS3;
    }
*/
    //private String  myAWSAccessKeyId=System.getProperty("accessKeyId");
    // private String myAWSSecretKey=System.getProperty("secretKey");
/*
    private AmazonS3 getS3ClientWithSuppliedCredentials() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(myAWSAccessKeyId, myAWSSecretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        return s3Client;
    }
*/
    /*
    private AmazonS3 getS3ClientUsingDefaultChain() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
        return s3Client;
    }*/

    public Bucket createBucket(String bucketName) {
        Bucket bucket = null;
        if(!amazonS3.doesBucketExistV2(bucketName)) {
            bucket = amazonS3.createBucket(bucketName);
        } else {
            logger.info("bucket name: {} is not available."
                    + " Try again with a different Bucket name.", bucketName);
        }
        return bucket;
    }

    public void uploadFile(File f) throws IOException {
        PutObjectRequest request = new PutObjectRequest("grace-s3-bucket", f.getName(), f);
        amazonS3.putObject(request);
    }

}