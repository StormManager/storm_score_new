package com.storm.score.repository;

import com.storm.score.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.storm.score.repository
 * fileName       : ScoreRepository
 * author         : ojy
 * date           : 2024/04/21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/21        ojy       최초 생성
 */
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
