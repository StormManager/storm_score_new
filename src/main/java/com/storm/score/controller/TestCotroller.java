package com.storm.score.controller;
/**
*/

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description    :
 * packageName    : com.storm.score.controller
 * fileName       : TestCotroller
 * author         : wammelier
 * date           : 2024/03/28
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/28        wammelier       최초 생성
 */


@Slf4j
@RestController
@RequestMapping("/test")
public class TestCotroller {

  @GetMapping("/")
  public String getTest() {
    log.info("START : {}", "asdfl;kajsdf;lasikj" );
    return "Hello";

  }

}