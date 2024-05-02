package com.storm.score.repository;

import com.storm.score.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.storm.score.repository
 * fileName       : MessageRepository
 * author         : ojy
 * date           : 2024/04/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/30        ojy       최초 생성
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
