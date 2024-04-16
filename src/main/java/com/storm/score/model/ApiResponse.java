package com.storm.score.model;
/**
 *
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description    :
 * packageName    : com.storm.score.model
 * fileName       : ApiResponse
 * author         : wammelier
 * date           : 2024/04/16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/16        wammelier       최초 생성
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
  private String rspCode = "0000";
  private String resStatus = "Success";

}
