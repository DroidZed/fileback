package com.wevioo.fileback.service;

import com.wevioo.fileback.enums.EtatBesoin;
import com.wevioo.fileback.enums.EtatDevis;
import com.wevioo.fileback.exceptions.NeedNotFoundException;
import com.wevioo.fileback.geolocationClasses.DisplayLatLng;
import com.wevioo.fileback.interfaces.GeoCoder;
import com.wevioo.fileback.interfaces.ImageManager;
import com.wevioo.fileback.interfaces.LocationManager;
import com.wevioo.fileback.interfaces.NeedsManager;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.*;
import com.wevioo.fileback.repository.CategoryRepository;
import com.wevioo.fileback.repository.NeedsRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class NeedsService implements NeedsManager {

    private final NeedsRepository needsRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final ImageManager imageManager;

    private final LocationManager locationManager;

    private final GeoCoder geoCoder;

    @Override
    public void cloturerBesoin(Long needId) {
        this.needsRepository.findById(needId)
                .map(d -> {
                    d.setEtatBesoin(EtatBesoin.CLOTURE);
                    return this.needsRepository.save(d);
                }).orElseThrow(() -> new NeedNotFoundException(needId));
    }

    @Override
    public ResponseEntity<?> getNeedByID(Long id) {
        Optional<Needs> opt = this.needsRepository.findById(id);

        if (opt.isEmpty())
            return ResponseEntity.status(404).body("Not Found !");

        Needs n = opt.get();

        var offres = n.getOffres();

        if (!offres.isEmpty()) {
            List<Devis> temp = new ArrayList<>();

            for (Devis d : offres) {
                var etat = d.getEtat();
                if (etat == EtatDevis.PUBLISHED) {
                    temp.add(d);
                }
            }
            n.setOffres(temp);
        }

        return ResponseEntity.ok(n);
    }

    public ResponseEntity<?> injectNewNeed(Needs besoin, Long catid, Long userid) {

        Optional<User> optUsr = userRepository.findById(userid);
        Optional<Category> optCat = categoryRepository.findById(catid);

        if (optUsr.isEmpty() || optCat.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: user or category not found !");
        }

        this.setNeedLocation(besoin);

        besoin.setUser(optUsr.get());
        besoin.setCategory(optCat.get());

        return ResponseEntity.ok(this.needsRepository.save(besoin));
    }

    public List<Needs> collectNeedsOfUser(Long userid) {

        return this.needsRepository.findNeedsOfUser(userid);
    }

    public List<Needs> collectNeedsOfCategory(Long catid) {

        return this.needsRepository.findNeedsByCategory(catid);
    }

    public ResponseEntity<?> deleteAllTheNeeds() {
        this.needsRepository.deleteAll();
        return ResponseEntity.ok(new ResponseMessage("All needs deleted successfully !"));
    }

    public ResponseEntity<?> deleteOne(Long needid) {
        this.needsRepository.deleteById(needid);
        return ResponseEntity.ok(new ResponseMessage(format("Need {0} deleted successfully !", needid)));
    }

    public ResponseEntity<?> modifyNeed(Long needid, Needs need) {
        return this.needsRepository.findById(needid)
                .map(oldNeed -> {
                    oldNeed.setDateLimite(need.getDateLimite());
                    oldNeed.setBudget(need.getBudget());
                    oldNeed.setNbJobber(need.getNbJobber());
                    oldNeed.setVehicleInfos(need.getVehicleInfos());
                    oldNeed.setAddress(need.getAddress());
                    this.setNeedLocation(need);
                    return ResponseEntity.ok(this.needsRepository.save(oldNeed));
                })
                .orElseThrow(() -> new NeedNotFoundException(needid));
    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> getImageA(Long needId) throws IOException {

        Optional<Needs> opt = this.needsRepository.findById(needId);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        if (besoin.getImageA() == null)
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Image introuvable !"));

        return CompletableFuture.completedFuture(ResponseEntity.ok(this.imageManager.getImageWithMediaType(besoin.getImageA(), "needs\\need" + needId)));

    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> getImageB(Long needId) throws IOException {
        Optional<Needs> opt = this.needsRepository.findById(needId);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        if (besoin.getImageB() == null)
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Image introuvable !"));

        return CompletableFuture.completedFuture(ResponseEntity.ok(this.imageManager.getImageWithMediaType(besoin.getImageB(), "needs\\need" + needId)));

    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> getImageC(Long needId) throws IOException {
        Optional<Needs> opt = this.needsRepository.findById(needId);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        if (besoin.getImageC() == null)
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Image introuvable !"));

        return CompletableFuture.completedFuture(ResponseEntity.ok(this.imageManager.getImageWithMediaType(besoin.getImageC(), "needs\\need" + needId)));

    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> getImageD(Long needId) throws IOException {
        Optional<Needs> opt = this.needsRepository.findById(needId);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        if (besoin.getImageD() == null)
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Image introuvable !"));

        return CompletableFuture.completedFuture(ResponseEntity.ok(this.imageManager.getImageWithMediaType(besoin.getImageD(), "needs\\need" + needId)));

    }

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadNeedPicA(MultipartFile needImageA, Long need_id) {

        Optional<Needs> opt = this.needsRepository.findById(need_id);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        besoin.setImageA(needImageA.getOriginalFilename());

        this.needsRepository.save(besoin);

        this.imageManager.uploadToLocalFileSystem(needImageA, "needs\\need" + besoin.getIdNeed(), needImageA.getOriginalFilename());

        return CompletableFuture.completedFuture(ResponseEntity.ok(new ResponseMessage("Success !!")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadNeedPicB(MultipartFile needImageB, Long need_id) {

        Optional<Needs> opt = this.needsRepository.findById(need_id);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        besoin.setImageB(needImageB.getOriginalFilename());

        this.needsRepository.save(besoin);

        this.imageManager.uploadToLocalFileSystem(needImageB, "needs\\need" + besoin.getIdNeed(), needImageB.getOriginalFilename());

        return CompletableFuture.completedFuture(ResponseEntity.ok(new ResponseMessage("Success !!")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadNeedPicC(MultipartFile needImageC, Long need_id) {

        Optional<Needs> opt = this.needsRepository.findById(need_id);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        besoin.setImageC(needImageC.getOriginalFilename());

        this.needsRepository.save(besoin);

        this.imageManager.uploadToLocalFileSystem(needImageC, "needs\\need" + besoin.getIdNeed(), needImageC.getOriginalFilename());

        return CompletableFuture.completedFuture(ResponseEntity.ok(new ResponseMessage("Success !!")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadNeedPicD(MultipartFile needImageD, Long need_id) {

        Optional<Needs> opt = this.needsRepository.findById(need_id);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        besoin.setImageD(needImageD.getOriginalFilename());

        this.needsRepository.save(besoin);

        this.imageManager.uploadToLocalFileSystem(needImageD, "needs\\need" + besoin.getIdNeed(), needImageD.getOriginalFilename());

        return CompletableFuture.completedFuture(ResponseEntity.ok(new ResponseMessage("Success !!")));
    }

    private void setNeedLocation(@NotNull Needs besoin) {
        try {
            DisplayLatLng latLng = this.geoCoder.getAddressCoded(besoin.getAddress()).get();

            NeedLocation needLoc = new NeedLocation(latLng.lng, latLng.lat);

            if (besoin.getNeedLocation() != null) {
                this.locationManager.updateNeedLocation(besoin.getNeedLocation().getIdLoc(), needLoc);
            } else {
                besoin.setNeedLocation(needLoc);
            }
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
    }


}
