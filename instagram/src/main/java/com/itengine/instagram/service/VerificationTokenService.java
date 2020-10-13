package com.itengine.instagram.service;

import com.itengine.instagram.model.User;
import com.itengine.instagram.model.VerificationToken;

public interface VerificationTokenService {

    VerificationToken findToken(String token);
    void createVerificationToken(User user, String token);
}
