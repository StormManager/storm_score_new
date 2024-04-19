package com.storm.score.repository;

import com.storm.score.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.storm.score.repository
 * fileName       : RoomRepository
 * author         : ojy
 * date           : 2024/04/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/19        ojy       최초 생성
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
}
