package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.PDFGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping(path = "/devis")
@AllArgsConstructor
public class DevisController {

    private final PDFGenerator pdfGenerator;

    @PostMapping(path = "/contract")
    public ResponseEntity<?> generatePDF(@RequestParam("devisId") Long devisId) throws IOException, MessagingException {
        return this.pdfGenerator.generatePDF(devisId);
    }

    @GetMapping(path = "/html")
    public ModelAndView generateHTML(ModelAndView modelAndView,
                               @RequestParam("devisId") Long devisId) {
        return this.pdfGenerator.generateHTML(modelAndView, devisId);
    }

    @PostMapping(path = "/send")
    public ResponseEntity<?> sendContract(@RequestParam("devisId") Long devisId, @RequestParam("dest") String dest) throws MessagingException {
        return this.pdfGenerator.sendPDF(devisId,dest);
    }
}
