package com.wevioo.fileback.interfaces;

import org.springframework.mail.SimpleMailMessage;

public interface EmailManager {

    void sendEmail(SimpleMailMessage email);
}
