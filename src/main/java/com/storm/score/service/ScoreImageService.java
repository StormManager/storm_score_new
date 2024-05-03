package com.storm.score.service;

import com.storm.score.model.Score;
import com.storm.score.model.ScoreImage;
import com.storm.score.repository.ScoreImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.storm.score.service
 * fileName       : ScoreImageService
 * author         : ojy
 * date           : 2024/04/21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/21        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ScoreImageService {
    private final ScoreImageRepository scoreImageRepository;
    private final AwsService awsService;
    public List<ScoreImage> createScoreImages(List<MultipartFile> fileList, Score score) {
        List<ScoreImage> scoreImageList = new ArrayList<>();

        List<String> urlList = awsService.uploadFile(fileList);

        for (int i = 0; i < urlList.size(); i++) {
            scoreImageList.add(
                ScoreImage.builder()
                    .score(score)
                    .url("url")
                    .index(i)
                    .build()
            );
        }

        return scoreImageList;
    }
}
