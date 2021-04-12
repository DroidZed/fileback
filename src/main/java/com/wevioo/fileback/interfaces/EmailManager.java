package com.wevioo.fileback.interfaces;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;

public interface EmailManager {

    void inviteUser(String to) throws MessagingException;
    void sendConfirmationMail(String to, String confirmationToken) throws MessagingException;
    void sendEMailWithAttach(String to, String attachment) throws MessagingException;
}
