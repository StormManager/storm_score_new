package com.storm.score.utils;

/**
 * packageName    : com.storm.score.utils
 * fileName       : CustomUtils
 * author         : ojy
 * date           : 2024/04/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/23        ojy       최초 생성
 */
public class CustomUtils {
    public static boolean isNotNullAndBlank(String str) {
        return str != null && !str.isBlank();
    }
}
