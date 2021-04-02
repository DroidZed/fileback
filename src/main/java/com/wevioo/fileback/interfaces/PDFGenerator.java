package com.wevioo.fileback.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;

public interface PDFGenerator {

    ResponseEntity<?> generatePDF(Long devisId) throws IOException, MessagingException;
    ModelAndView generateHTML(ModelAndView modelAndView, Long devisId);
    ResponseEntity<?> sendPDF(Long devisId, String email) throws MessagingException;
}
