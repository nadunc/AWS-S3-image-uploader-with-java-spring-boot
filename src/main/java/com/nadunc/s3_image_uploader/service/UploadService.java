package com.nadunc.s3_image_uploader.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nadunc.s3_image_uploader.exeption.InvalidFileFormatException;
import com.nadunc.s3_image_uploader.exeption.InvalidFileSizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class UploadService {

    @Value("${app.aws.iam.accesskey}")
    private String acessKey;

    @Value("${app.aws.iam.secretkey}")
    private String secretKey;

    @Value("${app.aws.s3.clientregion}")
    private String clientRegion;

    @Value("${app.aws.s3.bucketname}")
    private String bucketName;

    @Value("${spring.servlet.multipart.max-file-size}")
    private Integer maxFileSize;


    public String uploadImage(MultipartFile file) throws IOException, InvalidFileFormatException, InvalidFileSizeException, AmazonServiceException, SdkClientException{
        byte[] bytes = file.getBytes();
        String fileObjKeyName = file.getOriginalFilename();

        if (!file.getContentType().contains("image/")) {
            throw new InvalidFileFormatException();
        }else if(file.getSize() > maxFileSize){
            throw new InvalidFileSizeException();
        } else {

            BasicAWSCredentials awsCreds = new BasicAWSCredentials(acessKey, secretKey);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();


            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
//            metadata.addUserMetadata("x-amz-meta-title", "someTitle");
            metadata.setContentLength(file.getSize());


            InputStream inputStream = new ByteArrayInputStream(bytes);
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, inputStream, metadata);

            s3Client.putObject(request);


            return "https://s3."+clientRegion+".amazonaws.com/"+bucketName+"/"+fileObjKeyName;
        }
    }

}
