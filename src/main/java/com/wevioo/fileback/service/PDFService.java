package com.wevioo.fileback.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.wevioo.fileback.interfaces.PDFGenerator;
import com.wevioo.fileback.message.ContractIDs;
import com.wevioo.fileback.model.Contract;
import com.wevioo.fileback.model.Devis;
import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.DevisRepository;
import com.wevioo.fileback.repository.NeedsRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PDFService implements PDFGenerator {

    private final TemplateEngine templateEngine;
    private final UserRepository userRepository;
    private final NeedsRepository needsRepository;
    private final DevisRepository devisRepository;

    @Override
    public ResponseEntity<?> generatePDF(ContractIDs ids){

        /* Do Business Logic*/

        Contract contract = new Contract();

        Optional<User> optUser = userRepository.findById(ids.getUserId());
        Optional<User> optJobber = userRepository.findById(ids.getJobberId());
        Optional<Needs> optNeed = needsRepository.findById(ids.getNeedId());
        Optional<Devis> optDevis = devisRepository.findById(ids.getDevisId());

        if (optUser.isEmpty() || optJobber.isEmpty() || optNeed.isEmpty() || optDevis.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Requested data not found !");
        }

        User jobber = optJobber.get();
        User user = optUser.get();
        Needs need = optNeed.get();

        contract.setUserdetails(user);

        contract.setJobberDetails(jobber);

        contract.setNeed(need);

        contract.setCategory(jobber.getActivity().getCategory());

        contract.setContractId("CTR_USR_"+user.getIdUser()+"_JBR_"+jobber.getIdUser());

        contract.setDevis(optDevis.get());

        contract.setCreatedAd(LocalDateTime.now());

        /* Create HTML using Thymeleaf template Engine */

        Context context = new Context();
        context.setVariable("contract", contract);
        String orderHtml = templateEngine.process("contrat", context);

        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();


        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, new ConverterProperties());

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();

        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
