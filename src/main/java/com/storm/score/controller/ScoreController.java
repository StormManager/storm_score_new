package com.storm.score.controller;

import com.storm.score.dto.ScoreCreateReqDto;
import com.storm.score.dto.ScoreGetListReqDto;
import com.storm.score.dto.ScoreGetListResDto;
import com.storm.score.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
            악보를 업로드합니다. \\
            Swagger에서는 변수마다 content-type을 지정 할 수 없으므로 실제 요청시에는 각 파일의 content-type을 지정해야 합니다. \\
                        
            ex) \\
            curl -X POST 'http://localhost:8443/score' \\\\ \\
                        -H 'accept: \\*/\\*' \\\\ \\
                        -F 'reqDto={"title": "나비", "instrument": "C", "singer": "조정현"};type=application/json' \\\\ \\
                        -F 'fileList=@/Users/ojy/zeki/resource/오재영.jpg' \\\\ \\
                        -F 'fileList=@/Users/ojy/zeki/resource/오재영 복사본.jpg'""")
    @PostMapping(name = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long createScore(
            @RequestPart(name = "fileList", required=false) List<MultipartFile> fileList,
            @RequestPart(name = "reqDto") ScoreCreateReqDto scoreCreateReqDto
    ) {
        return this.scoreService.createScore(fileList, scoreCreateReqDto);
    }

    @Operation(summary = "악보 전체조회", description = """
            """)
    @GetMapping(name = "")
    public Page<ScoreGetListResDto> getScoreList(
            @Parameter
            ScoreGetListReqDto reqDto,
            @ParameterObject
            @PageableDefault(sort = "") Pageable pageable
    ) {
        return this.scoreService.getScoreList(reqDto, pageable);
    }
}

