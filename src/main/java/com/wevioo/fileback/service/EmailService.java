package com.wevioo.fileback.service;

import com.wevioo.fileback.interfaces.EmailManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class EmailService implements EmailManager {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private static final String logoPath = "src\\main\\resources\\static\\logo.png".replace('\\', '/');

    @Async
    @Override
    public void inviteUser(String to) throws MessagingException
    {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );

        Context context = new Context();

        String html = templateEngine.process("invitationMailTemplate", context);

        FileSystemResource logo = new FileSystemResource(new File(logoPath));

        helper.setTo(to);
        helper.setText(html,true);
        helper.setSubject("Venez découvrire notre plateforme !");
        helper.addInline("logo.png", logo, "image/png");

        javaMailSender.send(message);
    }

    @Async
    @Override
    public void sendConfirmationMail(String to, String confirmationToken) throws MessagingException
    {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );

        Context context = new Context();

        context.setVariable("confirmationToken", confirmationToken);

        String html = templateEngine.process("confirmationMailTemplate", context);

        FileSystemResource logo = new FileSystemResource(new File(logoPath));

        helper.setTo(to);
        helper.setText(html,true);
        helper.setSubject("Confirmer votre compte !");
        helper.addInline("logo.png", logo, "image/png");

        javaMailSender.send(message);
    }

    @Async
    @Override
    public void sendEMailWithAttach(String to, String attachment) throws MessagingException
    {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );

        Context context = new Context();

        String html = templateEngine.process("contractMailTemplate", context);

        FileSystemResource file = new FileSystemResource(new File(attachment));
        FileSystemResource logo = new FileSystemResource(new File(logoPath));

        helper.setTo(to);
        helper.setText(html,true);
        helper.setSubject("Contrat de devis");
        helper.addInline("logo.png", logo, "image/png");
        helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

        javaMailSender.send(message);
    }

}
