package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.Contract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;

public interface PDFGenerator {

    ResponseEntity<?> generatePDF(Long devisId) throws IOException, MessagingException;
    ModelAndView generateHTML(ModelAndView modelAndView, Long devisId);
    ResponseEntity<?> sendPDF(Long devisId, String email) throws MessagingException;
    void savePDF(String contractId, byte[] bytes) throws IOException;
    Contract setContractDetails(Long devisId);
}
