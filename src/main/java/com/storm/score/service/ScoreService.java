package com.storm.score.service;

import com.storm.score.dto.ScoreCreateReqDto;
import com.storm.score.model.Score;
import com.storm.score.model.ScoreImage;
import com.storm.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.storm.score.service
 * fileName       : ScoreService
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
public class ScoreService {
    private final ScoreRepository scoreRepository;

    private final ScoreImageService scoreImageService;

    @Transactional
    public Long createScore(List<MultipartFile> fileList, ScoreCreateReqDto reqDto) {
        fileList = fileList == null ? new ArrayList<>() : fileList;

        Score score = Score.builder()
                .title(reqDto.getTitle())
                .instrument(reqDto.getInstrument())
                .singer(reqDto.getSinger())
                .build();

        for (int i = 0; i < fileList.size(); i++) {
                    ScoreImage.builder()
                            .score(score)
                            .url("url")
                            .index(i)
                            .build();
        }

        scoreRepository.save(score);

        return score.getId();
    }
}
