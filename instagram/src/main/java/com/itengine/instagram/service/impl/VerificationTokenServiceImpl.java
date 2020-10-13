package com.itengine.instagram.service.impl;

import com.itengine.instagram.model.User;
import com.itengine.instagram.model.VerificationToken;
import com.itengine.instagram.repository.VerificationTokenRepository;
import com.itengine.instagram.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken findToken(String token) {
        return this.verificationTokenRepository.findByToken(token);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }
}
