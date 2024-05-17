package com.storm.score.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "EMAIL_VERIFICATION", schema = "STORM_SCORE")
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMAIL_VERIFICATION_ID", nullable = false)
    private Long id;

    @Column(name = "EMAIL", nullable = false, length = 45)
    private String email;

    @Column(name = "VERIFICATION_NUMBER", nullable = false, length = 6)
    private String verificationNumber;

    @Column(name = "EXPIRY_DATE", nullable = false)
    private LocalDateTime expiryDate;

    @Builder
    public EmailVerification(String email, String verificationNumber, LocalDateTime expiryDate) {
        this.email = email;
        this.verificationNumber = verificationNumber;
        this.expiryDate = expiryDate;
    }
}