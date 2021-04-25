package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.Needs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface NeedsManager {

    List<Needs> getAll();
    List<?> countByCategory();
    Long countComments(Long idNeed);
    List<LocalDate> getLimitDates();
    void cloturerBesoin(Long needId);
    Long countAllOfUser(Long idUser);
    ResponseEntity<?> deleteAllTheNeeds();
    ResponseEntity<?> getNeedByID(Long id);
    ResponseEntity<?> deleteOne(Long needid);
    List<Needs> collectNeedsOfUser(Long userid);
    List<Needs> collectNeedsOfCategory(Long catid);
    ResponseEntity<?> modifyNeed(Long needid, Needs need);
    ResponseEntity<?> injectNewNeed(Needs besoin, Long catid, Long userid);
    CompletableFuture<ResponseEntity<?>> getImageA(Long needId) throws IOException;
    CompletableFuture<ResponseEntity<?>> getImageB(Long needId) throws IOException;
    CompletableFuture<ResponseEntity<?>> getImageC(Long needId) throws IOException;
    CompletableFuture<ResponseEntity<?>> getImageD(Long needId) throws IOException;
    CompletableFuture<ResponseEntity<?>> uploadNeedPicA(MultipartFile needImageA, Long need_id);
    CompletableFuture<ResponseEntity<?>> uploadNeedPicB(MultipartFile needImageB, Long need_id);
    CompletableFuture<ResponseEntity<?>> uploadNeedPicC(MultipartFile needImageC, Long need_id);
    CompletableFuture<ResponseEntity<?>> uploadNeedPicD(MultipartFile needImageD, Long need_id);

}
