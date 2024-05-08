package com.storm.score.service;

import com.storm.score.repository.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.storm.score.service
 * fileName       : UserRoomService
 * author         : ojy
 * date           : 2024/05/05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/05        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class UserRoomService {
    private final UserRoomRepository userRoomRepository;

}
