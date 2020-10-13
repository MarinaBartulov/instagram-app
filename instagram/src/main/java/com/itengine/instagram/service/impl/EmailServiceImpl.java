package com.itengine.instagram.service.impl;

import com.itengine.instagram.model.User;
import com.itengine.instagram.service.EmailService;
import com.itengine.instagram.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Async
    public void sendConfirmRegMail(User user) throws MailException, InterruptedException, MessagingException {
        String token = UUID.randomUUID().toString();
        this.verificationTokenService.createVerificationToken(user, token);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String confirmationUrl
                =  "http://localhost:4200/confirmationRegistration/" + token;
        String link = "<a href='" + confirmationUrl + "'>" + confirmationUrl + "</a>";
        String htmlMsg = "Hello, \n " + user.getName() + ",\n\n Please confirm your email by clicking on the link below: " +
                " \n " + link;
        helper.setText(htmlMsg, true);
        helper.setTo(user.getEmail());
        helper.setSubject("Instagram account activation.");
        helper.setFrom(env.getProperty("spring.mail.username"));
        javaMailSender.send(mimeMessage);
    }
}
