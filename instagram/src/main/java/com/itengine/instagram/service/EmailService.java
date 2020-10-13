package com.itengine.instagram.service;

import com.itengine.instagram.model.User;

import javax.mail.MessagingException;

public interface EmailService {

    void sendConfirmRegMail(User user) throws InterruptedException, MessagingException;
}
