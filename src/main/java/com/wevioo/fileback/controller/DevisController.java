package com.wevioo.fileback.controller;

import com.wevioo.fileback.enums.EtatDevis;
import com.wevioo.fileback.interfaces.DevisManager;
import com.wevioo.fileback.interfaces.PDFGenerator;
import com.wevioo.fileback.model.Devis;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/devis")
@AllArgsConstructor
public class DevisController {

    private final PDFGenerator pdfGenerator;
    private final DevisManager devisManager;


    @PostMapping("/save")
    public ResponseEntity<?> saveDevis(@RequestBody Devis d, @RequestParam("jobberId") Long jobberId, @RequestParam("needId") Long needId)
    {
        return this.devisManager.saveDevis(d, jobberId, needId);
    }

    @PostMapping(path = "/contract")
    public ResponseEntity<?> generatePDF(@RequestParam("devisId") Long devisId)
            throws IOException, MessagingException
    {
        return this.pdfGenerator.generatePDF(devisId);
    }

    @PostMapping(path = "/send")
    public ResponseEntity<?> sendContract(@RequestParam("devisId") Long devisId,
                                          @RequestParam("dest") String dest)
            throws MessagingException
    {
        return this.pdfGenerator.sendPDF(devisId,dest);
    }

    @GetMapping(path = "/html")
    public ModelAndView generateHTML(ModelAndView modelAndView,
                                     @RequestParam("devisId") Long devisId)
    {
        return this.pdfGenerator.generateHTML(modelAndView, devisId);
    }

    @GetMapping(path = "/get/one/{id}")
    public Devis getOne(@PathVariable Long id)
    {
        return this.devisManager.getDevisById(id);
    }
    
    @PutMapping(path = "/ignore/{id}")
    public CompletableFuture<ResponseEntity<?>> ignorerDevis(@PathVariable Long id)
    {
        return this.devisManager.changeEtatDevis(id, EtatDevis.IGNORED);
    }

    @PutMapping(path = "/confirm/{id}")
    public CompletableFuture<ResponseEntity<?>> confirmDevis(@PathVariable Long id)
    {
        return this.devisManager.changeEtatDevis(id, EtatDevis.ACCEPTED);
    }
}
