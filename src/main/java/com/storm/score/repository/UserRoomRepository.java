package com.storm.score.repository;

import com.storm.score.model.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.storm.score.repository
 * fileName       : UserRoomRepository
 * author         : ojy
 * date           : 2024/05/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/23        ojy       최초 생성
 */
public interface UserRoomRepository extends JpaRepository<UserRoom, Long> {
    void deleteByRoom_IdAndUser_Id(Long roomId, Long userId);
}
