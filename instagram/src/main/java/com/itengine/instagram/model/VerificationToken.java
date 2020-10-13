package com.itengine.instagram.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    private LocalDateTime expiryDate;

    public VerificationToken(){

    }

    public VerificationToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(final String token, final User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }


    private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDateTime = now.plusMinutes(EXPIRATION);
        return expiryDateTime;
    }
}
