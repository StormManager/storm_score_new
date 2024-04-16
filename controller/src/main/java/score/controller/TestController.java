package score.controller;
/**
 *
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description    :
 * packageName    : com.storm.score.controller
 * fileName       : TestController
 * author         : wammelier
 * date           : 2024/03/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/18        wammelier       최초 생성
 */
@RestController
@Api("테스트용")
public class TestController {

  @GetMapping("/test")
  @ApiOperation(value = "HTTP/GET", notes = "TEST")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "userName", value = "회원의 이름", required = true, dataType = "String")
  })
  public String Test(@RequestParam String userName) {
    return "HIHIHIHI";
  }



}
