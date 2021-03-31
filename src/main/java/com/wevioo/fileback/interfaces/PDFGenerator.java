package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.message.ContractIDs;
import org.springframework.http.ResponseEntity;

public interface PDFGenerator {

    ResponseEntity<?> generatePDF(ContractIDs ids);
}
