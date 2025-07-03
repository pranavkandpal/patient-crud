package com.docsehr.patient.MinIO;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    private final String bucketName = "patient-documents";

    public String uploadFile(MultipartFile file, String fileName) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return "http://localhost:9000/" + bucketName + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to MinIO: " + e.getMessage());
        }
    }
}
