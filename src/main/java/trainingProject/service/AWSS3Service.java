package trainingProject.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public String uploadFile(String bucketName, MultipartFile file) throws IOException {
//        PutObjectRequest request = new PutObjectRequest("grace-s3-bucket", f.getName(), f);
//        amazonS3.putObject(request);
        try {
            String uuid = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String newFileName = uuid + "." + Files.getFileExtension(originalFilename);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, newFileName, file.getInputStream(),objectMetadata);
            return newFileName;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


//    public String getFileUrl(String s3Key) {
//        return amazonS3.get
//    }

    //TODO TEST
    public File downloadObject(String bucketName,
                               String objectKey, String destinationFullPath) throws IOException {
        S3Object s3Object = amazonS3.getObject(bucketName, objectKey);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        File destFile = new File(destinationFullPath);
        FileUtils.copyInputStreamToFile(inputStream, destFile);
        return destFile;
    }

    //TODO TEST
    public boolean isBucketExist(String bucketName) {
        boolean isExist = amazonS3.doesBucketExistV2(bucketName);
        return isExist;
    }

    //TODO TEST
    public boolean isObjectExist(String bucketName, String objectKey) {
        boolean isExist = amazonS3.doesObjectExist(bucketName, objectKey);
        return isExist;
    }

    //TODO TEST
    public List<Bucket> getBucketList() {
        List<Bucket> buckets = amazonS3.listBuckets();
        return buckets;
    }

    //TODO TEST
    public void deleteBucket(String bucketName) {
        try {
            amazonS3.deleteBucket(bucketName);
        }
        catch (AmazonServiceException e) {
            logger.error(e.getErrorMessage());
        }
    }

    //TODO TEST
    public void uploadObject(String bucketName, String key, String fullFilePath) {
        amazonS3.putObject(bucketName, key, new File(fullFilePath));
    }

    // TODO TEST
    public List<String> findObjectKeyList(String bucketName) {
        ObjectListing objectListing = amazonS3.listObjects(bucketName);
        List<String> objectKeyList = new ArrayList<>();
        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            objectKeyList.add(os.getKey());
        }
        return objectKeyList;
    }

    public void copyObject(String oriBucketName,
                           String oriObjectKey, String destBucketName,
                           String destObjectKey) {
        amazonS3.copyObject(oriBucketName, oriObjectKey,
                destBucketName, destObjectKey);
    }

    public void copyObjectUsingCopyObjectRequest(
            String oriBucketName,
            String oriObjectKey,
            String destBucketName,
            String destObjectKey) {
        CopyObjectRequest copyObjectRequest = new CopyObjectRequest();
        copyObjectRequest.setSourceBucketName(oriBucketName);
        copyObjectRequest.setSourceKey(oriObjectKey);
        copyObjectRequest.setDestinationBucketName(destBucketName);
        copyObjectRequest.setDestinationKey(destObjectKey);
        amazonS3.copyObject(copyObjectRequest);
    }
}