package com.storm.score.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * packageName    : com.storm.score.service
 * fileName       : AwsService
 * author         : ojy
 * date           : 2024/05/03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/03        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class AwsService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.object-link}")
    private String objectLink;
    @Value("${cloud.aws.s3.link-start}")
    private String linkStart;

    private static final String DIR = "";

    public List<String> uploadFile(List<MultipartFile> multipartFiles) {
        List<String> fileLinkList = new ArrayList<>();

        // forEach 구문을 통해 multipartFile로 넘어온 파일들 하나씩 fileNameList에 추가
        multipartFiles.forEach(file -> {
            if (file.getContentType() != null) {
                String fileName = putFile(file);

                fileLinkList.add(objectLink + amazonS3Client.getObject(bucket, fileName).getKey());
            } else {
                fileLinkList.add("");
            }
        });

        return fileLinkList;
    }

    private String putFile(MultipartFile multipartFile) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            // 파일 유효성 검사
            Tika tika = new Tika();
            String detectedFile = tika.detect(multipartFile.getBytes());
            if (!(detectedFile.startsWith("image"))) {
                throw new IllegalArgumentException("AwsS3 : 올바른 이미지 파일을 올려주세요.");
            }

            // 업로드
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return fileName;
        } catch (IOException e) {
            throw new ApiException(ResponseCode.S3_UPLOAD_FAILED);
        }
    }


    public void deleteFile(String imgUrl) {
        if (imgUrl.startsWith(this.linkStart)) {
            String fileName = DIR + imgUrl.split("/")[4];
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
        }
    }

    public void deleteAllFile(List<String> imageLinkList) {
        if (!imageLinkList.isEmpty()){
            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucket);
            List<DeleteObjectsRequest.KeyVersion> keyVersionList = new ArrayList<>();
            imageLinkList.forEach(imageLink -> {
                String fileName = DIR + imageLink.split("/")[4];
                keyVersionList.add(new DeleteObjectsRequest.KeyVersion(fileName));
            });
            deleteObjectsRequest.setKeys(keyVersionList);
            amazonS3Client.deleteObjects(deleteObjectsRequest);
        }
    }

    private String createFileName(String fileName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
        return DIR + UUID.randomUUID().toString().concat("_" + getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

    public List<List<String>> getAllObject() {
        ObjectListing objectListing = amazonS3Client.listObjects(bucket, DIR);

        List<List<String>> keyList = new ArrayList<>();

        do {

            List<S3ObjectSummary> summaries = objectListing.getObjectSummaries();

            List<String> collects = summaries.stream().map(s3ObjectSummary -> objectLink + s3ObjectSummary.getKey()).collect(Collectors.toList());
            keyList.add(collects);


            objectListing = amazonS3Client.listNextBatchOfObjects(objectListing);

        } while (objectListing.isTruncated());

        return keyList;
    }


}
