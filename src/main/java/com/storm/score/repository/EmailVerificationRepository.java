package com.storm.score.repository;

import com.storm.score.model.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName    : com.storm.score.repository
 * fileName       : EmailVerificationRepository
 * author         : ojy
 * date           : 2024/05/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/17        ojy       최초 생성
 */
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    List<EmailVerification> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteAllByEmail(String email);
}
