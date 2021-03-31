package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.PDFGenerator;
import com.wevioo.fileback.message.ContractIDs;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/devis")
@AllArgsConstructor
public class DevisController {

    private final PDFGenerator pdfGenerator;

    @PostMapping(path = "/contract")
    public ResponseEntity<?> generatePDF (@RequestBody ContractIDs ids)
    {
        return this.pdfGenerator.generatePDF(ids);
    }
}
