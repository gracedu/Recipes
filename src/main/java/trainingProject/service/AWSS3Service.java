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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AWSS3Service {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String bucket = System.getProperty("aws.s3.bucket");
    AmazonS3 amazonS3;
    public AWSS3Service(@Autowired AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }


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

    public String uploadFile(MultipartFile file) throws IOException {
        return uploadFile(bucket, file);
    }

    public String uploadFile(String bucketName, MultipartFile file) throws IOException {
        try {
            String uuid = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String newFileName = uuid + "." + Files.getFileExtension(originalFilename);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, newFileName, file.getInputStream(),objectMetadata);
            logger.info(String.format("The file name=%s was uploaded to bucket %s", newFileName, bucketName));
            return newFileName;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }



   //TODO TEST
    public String getFileUrl(String fileName) {
        return getFileUrl(bucket, fileName);
    }

    public String getFileUrl(String bucketName, String fileName) {
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    public void putObject(String S3Key, File file) {
        putObject(bucket, S3Key, file);
    }

    public void putObject(String bucket, String s3Key, File file) {
        amazonS3.putObject(bucket, s3Key, file);
    }

    public S3Object getObject(String s3Key) {
        if (s3Key == null) return null;
        return getObject(bucket, s3Key);
    }

    public S3Object getObject(String bucket, String s3Key) {
        return amazonS3.getObject(bucket, s3Key);
    }




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

    public void deleteObject(String bucketName, String objectKey) {
        amazonS3.deleteObject(bucketName, objectKey);
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