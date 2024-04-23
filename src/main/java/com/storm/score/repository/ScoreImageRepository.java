package com.storm.score.repository;

import com.storm.score.model.ScoreImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.storm.score.repository
 * fileName       : ScoreImageRepository
 * author         : ojy
 * date           : 2024/04/21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/21        ojy       최초 생성
 */
public interface ScoreImageRepository extends JpaRepository<ScoreImage, Long> {
}
