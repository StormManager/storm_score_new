package com.storm.score.service;

import com.storm.score.dto.ScoreCreateReqDto;
import com.storm.score.dto.ScoreGetListReqDto;
import com.storm.score.dto.ScoreGetListResDto;
import com.storm.score.model.Score;
import com.storm.score.repository.ScoreJoinRepository;
import com.storm.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ScoreJoinRepository scoreJoinRepository;

    private final ScoreImageService scoreImageService;

    @Transactional
    public Long createScore(List<MultipartFile> fileList, ScoreCreateReqDto reqDto) {
        fileList = fileList == null ? new ArrayList<>() : fileList;

        Score score = Score.builder()
                .title(reqDto.getTitle())
                .instrument(reqDto.getInstrument())
                .singer(reqDto.getSinger())
                .build();

        scoreImageService.createScoreImages(fileList, score);

        scoreRepository.save(score);

        return score.getId();
    }

    @Transactional(readOnly = true)
    public Page<ScoreGetListResDto> getScoreList(ScoreGetListReqDto reqDto, Pageable pageable) {
        return scoreJoinRepository.getScoreList(reqDto, pageable);
    }

    public void deleteScore(Long scoreId) {
        scoreRepository.deleteById(scoreId);
    }
}
