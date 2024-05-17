package com.storm.score.service;

import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import com.storm.score.model.EmailVerification;
import com.storm.score.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * packageName    : com.storm.score.service
 * fileName       : EmailService
 * author         : ojy
 * date           : 2024/05/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/17        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final Random random;

    public void emailAuth(String email) {
        String verificationNumber = this.generateVerificationNumber();
        // 이메일 전송 로직
        this.sendEmail(email, verificationNumber);

        EmailVerification emailVerification = EmailVerification.builder()
                .email(email)
                .verificationNumber(verificationNumber)
                .build();
        emailVerificationRepository.save(emailVerification);
    }

    private void sendEmail(String email, String verificationNumber) {
        // TODO: 이메일 전송 로직
    }

    private String generateVerificationNumber() {
        int code = random.nextInt(999999); // 6자리 숫자 생성
        return String.format("%06d", code);
    }

    public void checkEmailAuth(String email, String verificationNumber) {
        EmailVerification emailVerification = emailVerificationRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException(ResponseCode.UNAUTHORIZED, "인증 내역이 없습니다."));

        if (emailVerification.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new ApiException(ResponseCode.EXPIRED_TOKEN, "인증번호가 만료되었습니다. 다시 인증해주세요.");
        }

        if (!emailVerification.getVerificationNumber().equals(verificationNumber)) {
            throw new ApiException(ResponseCode.ACCESS_DENIED, "인증번호가 일치하지 않습니다.");
        }
    }

}
