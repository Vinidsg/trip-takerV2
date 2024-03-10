package com.senac.tripTaker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class AmazonS3Service {

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    @Value("${amazon.access-key}")
    private String accessKey;

    @Value("${amazon.secret-key}")
    private String secretKey;

    @Value("${amazon.region}")
    private String region;

    public String uploadImage(MultipartFile imageFile) throws IOException, S3Exception {
        Region regionObj = Region.of(region);
        S3Client s3Client = S3Client.builder()
                .region(regionObj)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();

        String fileName = System.currentTimeMillis() + "-" + imageFile.getOriginalFilename();
        File tempFile = convertMultiPartFileToFile(imageFile);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(request, RequestBody.fromFile(tempFile));

        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        URL objectUrl = s3Client.utilities().getUrl(getUrlRequest);

        // Após o upload, deletar o arquivo temporário criado
        tempFile.delete();

        return objectUrl.toString();
    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        Path tempDir = Files.createTempDirectory("");
        File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
        file.transferTo(tempFile);
        return tempFile;
    }
}
