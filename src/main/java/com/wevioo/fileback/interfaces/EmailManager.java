package com.wevioo.fileback.interfaces;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;

public interface EmailManager {

    void sendEmail(SimpleMailMessage email);
    void sendEMailWithAttach(String to, String subject, String body, String attachment) throws MessagingException;
}
