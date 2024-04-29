package com.storm.score.dto;

import com.storm.score.model.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.storm.score.dto
 * fileName       : CreateResDto
 * author         : ojy
 * date           : 2024/04/29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/29        ojy       최초 생성
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateResDto extends ApiResponse {
    private Long id;
}
