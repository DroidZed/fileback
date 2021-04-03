package com.wevioo.fileback.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.wevioo.fileback.interfaces.EmailManager;
import com.wevioo.fileback.interfaces.PDFGenerator;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Contract;
import com.wevioo.fileback.model.Devis;
import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PDFService implements PDFGenerator {

    @Value(value = "${contracts.path}")
    private String pathToPDF;

    private final TemplateEngine templateEngine;

    private final DevisRepository devisRepository;

    private final EmailManager emailManager;

    @Autowired
    public PDFService(TemplateEngine templateEngine, DevisRepository devisRepository, EmailManager emailManager) {
        this.templateEngine = templateEngine;
        this.devisRepository = devisRepository;
        this.emailManager = emailManager;
    }

    @Override
    public ModelAndView generateHTML(ModelAndView modelAndView, Long devisId) {
        Contract contract = this.setContractDetails(devisId);

        if (contract == null)
            return null;

        modelAndView.addObject("contract", contract);
        modelAndView.setViewName("contrat");
        return modelAndView;
    }

    @Override
    public ResponseEntity<?> generatePDF(Long devisId) throws IOException, MessagingException {

        Contract contract = this.setContractDetails(devisId);

        if (contract == null)
            return ResponseEntity
                    .badRequest()
                    .body("Requested data not found !");

        /* Create HTML using Thymeleaf template Engine */

        Context context = new Context();
        context.setVariable("contract", contract);
        String contractHTML = templateEngine.process("contrat", context);

        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /* Call convert method */

        HtmlConverter.convertToPdf(contractHTML, target, new ConverterProperties());

        /* extract output as bytes */

        byte[] bytes = target.toByteArray();

        Path storageDirectory = Paths.get(pathToPDF);

        if (!Files.exists(storageDirectory)) {

            Files.createDirectories(storageDirectory);

        }

        /* Convert the bytes to physical file */
        this.savePDF(contract.getContractId(), bytes);

        this.sendPDF(devisId, contract.getUserDetails().getEmail());
        this.sendPDF(devisId, contract.getJobberDetails().getEmail());

        return ResponseEntity.ok(new ResponseMessage("Contract created, saved and sent to destination !"));
    }

    public ResponseEntity<?> sendPDF(Long devisId, String email) throws MessagingException {

        Contract contract = this.setContractDetails(devisId);

        if (contract == null) {
            return ResponseEntity.badRequest().body("No contract available !");
        }

        String fname = pathToPDF + "\\" + "contrat_" + contract.getContractId() + ".pdf";

        String subject = "Contrat de devis";

        String body = "Veuillez consulter le contrat en accroche ! Tout les details concernant le travail sonts mentionnées dedans.";

        emailManager.sendEMailWithAttach(email, subject, body, fname);

        return ResponseEntity.ok(new ResponseMessage("Email sent !"));
    }

    private void savePDF(String contractId, byte[] bytes) throws IOException {

        String fname = pathToPDF + "\\" + "contrat_" + contractId + ".pdf";

        File outputFile = new File(fname);

        FileOutputStream outputStream = new FileOutputStream(outputFile);

        outputStream.write(bytes);

        outputStream.flush();

        outputStream.close();
    }

    private Contract setContractDetails(Long devisId) {

        /* Do Business Logic*/

        Contract contract = new Contract();

        Optional<Devis> optDevis = devisRepository.findById(devisId);

        if (optDevis.isEmpty()) {
            return null;
        }

        Devis devis = optDevis.get();

        User jobber = devis.getJobber(), user = devis.getNeed().getUser();

        Needs need = devis.getNeed();

        contract.setUserDetails(user);

        contract.setJobberDetails(jobber);

        contract.setNeed(need);

        contract.setCategory(jobber.getActivity().getCategory());

        contract.setContractId("CTR_USR_" + user.getIdUser() + "_JBR_" + jobber.getIdUser());

        contract.setDevis(devis);

        contract.setCreatedAt(LocalDateTime.now());

        return contract;
    }
}
