package com.storm.score.service;

import com.storm.score.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.storm.score.service
 * fileName       : RoomService
 * author         : ojy
 * date           : 2024/04/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/19        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
}
