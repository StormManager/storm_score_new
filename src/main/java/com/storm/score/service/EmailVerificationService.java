package com.storm.score.service;

import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import com.storm.score.model.EmailVerification;
import com.storm.score.repository.EmailVerificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

    private final JavaMailSender javaMailSender;

    @Transactional
    public void emailAuth(String email) {
        String verificationNumber = this.generateVerificationNumber();
        // 이메일 전송 로직
        this.sendEmail(email, verificationNumber);

        if(emailVerificationRepository.existsByEmail(email)){
            emailVerificationRepository.deleteAllByEmail(email);
        }

        EmailVerification emailVerification = EmailVerification.builder()
                .email(email)
                .verificationNumber(verificationNumber)
                .expiryDate(LocalDateTime.now().plusMinutes(10))
                .build();
        emailVerificationRepository.save(emailVerification);
    }

    public void sendEmail(String email, String verificationNumber) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String htmlMsg = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>인증번호 안내</title>\n" +
                "    <style>\n" +
                "        .email-container {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f7f7f7;\n" +
                "            padding: 20px;\n" +
                "            border: 1px solid #ddd;\n" +
                "            border-radius: 5px;\n" +
                "            max-width: 600px;\n" +
                "            margin: auto;\n" +
                "        }\n" +
                "        .email-header {\n" +
                "            background-color: #4CAF50;\n" +
                "            color: white;\n" +
                "            padding: 10px;\n" +
                "            border-radius: 5px 5px 0 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .email-body {\n" +
                "            padding: 20px;\n" +
                "            background-color: white;\n" +
                "            border-radius: 0 0 5px 5px;\n" +
                "        }\n" +
                "        .verification-code {\n" +
                "            font-size: 20px;\n" +
                "            font-weight: bold;\n" +
                "            color: #4CAF50;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            margin-top: 20px;\n" +
                "            color: #555;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"email-container\">\n" +
                "        <div class=\"email-header\">\n" +
                "            <h2>스톰 악보 인증번호 안내</h2>\n" +
                "        </div>\n" +
                "        <div class=\"email-body\">\n" +
                "            <p>안녕하세요,</p>\n" +
                "            <p>요청하신 인증번호는 아래와 같습니다:</p>\n" +
                "            <p class=\"verification-code\">" + verificationNumber + "</p>\n" +
                "            <p>인증번호는 10분간 유효합니다.</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 스톰 악보. All rights reserved.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";


        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("[스톰 악보] 인증번호 안내 메일");
            helper.setFrom("stormscore94@gmail.com");
            helper.setText(htmlMsg, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new ApiException(ResponseCode.EMAIL_SEND_ERROR, "이메일 전송 중 오류가 발생했습니다.");
        }
    }


    private String generateVerificationNumber() {
        int code = random.nextInt(999999); // 6자리 숫자 생성
        return String.format("%06d", code);
    }

    @Transactional
    public void checkEmailAuth(String email, String verificationNumber) {
        List<EmailVerification> emailVerificationList = emailVerificationRepository.findByEmail(email);

        if (emailVerificationList.isEmpty()) {
            throw new ApiException(ResponseCode.UNAUTHORIZED, "인증 내역이 없습니다.");
        }

        if (emailVerificationList.size() > 1) {
            emailVerificationRepository.deleteAll(emailVerificationList);
            throw new ApiException(ResponseCode.INTERNAL_SERVER_LOGIC_ERROR, "인증 내역이 1건 이상 발생했습니다. [전체 삭제 조치]");
        }

        EmailVerification emailVerification = emailVerificationList.get(0);
        if (emailVerification.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new ApiException(ResponseCode.EXPIRED_TOKEN, "인증번호가 만료되었습니다. 다시 인증해주세요.");
        }

        if (!emailVerification.getVerificationNumber().equals(verificationNumber)) {
            throw new ApiException(ResponseCode.ACCESS_DENIED, "인증번호가 일치하지 않습니다.");
        }
    }

}
