package com.storm.score.controller;

import com.storm.score.dto.ScoreCreateReqDto;
import com.storm.score.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * packageName    : com.storm.score.controller
 * fileName       : ScoreController
 * author         : ojy
 * date           : 2024/04/21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/21        ojy       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/score")
@Tag(name = "Score", description = "악보 창고 API")
public class ScoreController {
    private final ScoreService scoreService;


    @Operation(summary = "악보 업로드", description = """
            악보를 업로드합니다.
            Swagger에서는 파일마다 content-type을 지정 할 수 없으므로 실제 요청시에는 각 파일의 content-type을 지정해야 합니다.
            ex) fileList : multipart/form-data
                reqDto : application/json""")
    @PostMapping(name = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long createScore(
            @RequestPart(name = "fileList", required=false) List<MultipartFile> fileList,
            @RequestPart(name = "reqDto") ScoreCreateReqDto scoreCreateReqDto
    ) {
        return this.scoreService.createScore(fileList, scoreCreateReqDto);
    }
}

