package com.wevioo.fileback.service;

import com.wevioo.fileback.interfaces.EmailManager;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements EmailManager {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

}
