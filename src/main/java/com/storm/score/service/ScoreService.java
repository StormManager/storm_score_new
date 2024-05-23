package com.storm.score.service;

import com.storm.score.dto.ScoreCreateReqDto;
import com.storm.score.dto.ScoreGetDetailResDto;
import com.storm.score.dto.ScoreGetListReqDto;
import com.storm.score.dto.ScoreGetListResDto;
import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import com.storm.score.model.Score;
import com.storm.score.model.User;
import com.storm.score.repository.ScoreJoinRepository;
import com.storm.score.repository.ScoreRepository;
import com.storm.score.security.UserDetailsImpl;
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
    private final GetUserEntityService getUserEntityService;

    @Transactional
    public Long createScore(List<MultipartFile> fileList, ScoreCreateReqDto reqDto, UserDetailsImpl userDetails) {
        User user = getUserEntityService.getUser(userDetails.getUsername());

        fileList = fileList == null ? new ArrayList<>() : fileList;

        Score score = Score.builder()
                .title(reqDto.getTitle())
                .instrument(reqDto.getInstrument())
                .singer(reqDto.getSinger())
                .user(user)
                .build();

        scoreImageService.createScoreImages(fileList, score);

        scoreRepository.save(score);

        return score.getId();
    }

    @Transactional(readOnly = true)
    public Page<ScoreGetListResDto> getScoreList(ScoreGetListReqDto reqDto, Pageable pageable, UserDetailsImpl userDetails) {
        return scoreJoinRepository.getScoreList(reqDto, pageable,userDetails);
    }

    @Transactional
    public void deleteScore(Long scoreId, UserDetailsImpl userDetails) {
        User user = getUserEntityService.getUser(userDetails.getUsername());
        Score score = this.getScore(scoreId);

        if(!score.getUser().getId().equals(user.getId())){
            throw new ApiException(ResponseCode.UNMODIFIABLE_INFORMATION, "삭제 권한이 없습니다. user nickname: " + user.getNickName() + ", score id: " + scoreId);
        }

        scoreRepository.deleteById(scoreId);
    }

    public Score getScore(Long scoreId) {
        return scoreRepository.findById(scoreId)
                .orElseThrow(() -> new ApiException(ResponseCode.RESOURCE_NOT_FOUND, "악보를 찾을 수 없습니다. id: " + scoreId));
    }

    @Transactional(readOnly = true)
    public ScoreGetDetailResDto getScoreDetail(Long scoreId, UserDetailsImpl userDetails) {
        Score score = this.getScore(scoreId);
        User user = getUserEntityService.getUser(userDetails.getUsername());

        if (!score.getUser().getId().equals(user.getId())) {
            throw new ApiException(ResponseCode.UNMODIFIABLE_INFORMATION, "조회 권한이 없습니다. user nickname: " + user.getNickName() + ", score id: " + scoreId);
        }

        ScoreGetDetailResDto result = ScoreGetDetailResDto.builder()
                .scoreId(score.getId())
                .title(score.getTitle())
                .instrument(score.getInstrument())
                .singer(score.getSinger())
                .build();

        result.regScoreImageList(
                score.getScoreImageList().stream()
                        .map(scoreImage -> ScoreGetDetailResDto.ScoreImageListDto.builder()
                                .scoreImageId(scoreImage.getId())
                                .url(scoreImage.getUrl())
                                .index(scoreImage.getIndex())
                                .build())
                        .toList()
        );
        return result;
    }
}
