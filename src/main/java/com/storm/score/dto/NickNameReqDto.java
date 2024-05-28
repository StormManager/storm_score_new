package com.storm.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.storm.score.dto
 * fileName       : CheckNickNameReqDto
 * author         : ojy
 * date           : 2024/05/28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/28        ojy       최초 생성
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Check NickName Request")
public class NickNameReqDto {
    @Schema(description = "닉네임", example = "test")
    private String nickName;
}
