package com.storm.score.repository;

import com.storm.score.model.UserRoom;
import com.storm.score.model.UserRoomId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.storm.score.repository
 * fileName       : UserRoomRepository
 * author         : ojy
 * date           : 2024/05/05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/05        ojy       최초 생성
 */
public interface UserRoomRepository extends JpaRepository<UserRoom, UserRoomId> {
}
