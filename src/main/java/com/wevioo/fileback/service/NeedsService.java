package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.NeedNotFoundException;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.CategoryRepository;
import com.wevioo.fileback.repository.NeedsRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class NeedsService {

    private final NeedsRepository needsRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final ImageService imageService;

    public ResponseEntity<?> injectNewNeed(Needs besoin, Long catid, Long userid) {

        Optional<User> optUsr = userRepository.findById(userid);
        Optional<Category> optCat = categoryRepository.findById(catid);

        if (optUsr.isPresent() && optCat.isPresent()) {
            besoin.setUser(optUsr.get());
            besoin.setCategory(optCat.get());
        }

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
                    oldNeed.setNeedTitle(need.getNeedTitle());
                    oldNeed.setNeedDescription(need.getNeedDescription());
                    oldNeed.setBudget(need.getBudget());
                    oldNeed.setNbJobber(need.getNbJobber());
                    oldNeed.setVehicleInfos(need.getVehicleInfos());
                    return ResponseEntity.ok(this.needsRepository.save(oldNeed));
                })
                .orElseThrow(() -> new NeedNotFoundException(needid));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadNeedPicA(MultipartFile needImageA, Long need_id) {

        Optional<Needs> opt = this.needsRepository.findById(need_id);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        besoin.setImageA(needImageA.getOriginalFilename());

        System.out.println("-------------");
        System.out.println(besoin.getImageA());
        System.out.println("-------------");

        this.needsRepository.save(besoin);

        this.imageService.uploadToLocalFileSystem(needImageA, "needs", "need", needImageA.getOriginalFilename());

        return CompletableFuture.completedFuture(ResponseEntity.ok(new ResponseMessage("Success !!")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadNeedPicB(MultipartFile needImageB, Long need_id) {

        Optional<Needs> opt = this.needsRepository.findById(need_id);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        besoin.setImageB(needImageB.getOriginalFilename());

        System.out.println("-------------");
        System.out.println(besoin.getImageB());
        System.out.println("-------------");

        this.needsRepository.save(besoin);

        this.imageService.uploadToLocalFileSystem(needImageB, "needs", "need", needImageB.getOriginalFilename());

        return CompletableFuture.completedFuture(ResponseEntity.ok(new ResponseMessage("Success !!")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadNeedPicC(MultipartFile needImageC, Long need_id) {

        Optional<Needs> opt = this.needsRepository.findById(need_id);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        besoin.setImageC(needImageC.getOriginalFilename());

        System.out.println("-------------");
        System.out.println(besoin.getImageC());
        System.out.println("-------------");

        this.needsRepository.save(besoin);

        this.imageService.uploadToLocalFileSystem(needImageC, "needs", "need", needImageC.getOriginalFilename());

        return CompletableFuture.completedFuture(ResponseEntity.ok(new ResponseMessage("Success !!")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> uploadNeedPicD(MultipartFile needImageD, Long need_id) {

        Optional<Needs> opt = this.needsRepository.findById(need_id);

        if (opt.isEmpty())
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Besoin introuvable !"));

        Needs besoin = opt.get();

        besoin.setImageD(needImageD.getOriginalFilename());

        System.out.println("-------------");
        System.out.println(besoin.getImageD());
        System.out.println("-------------");

        this.needsRepository.save(besoin);

        this.imageService.uploadToLocalFileSystem(needImageD, "needs", "need", needImageD.getOriginalFilename());

        return CompletableFuture.completedFuture(ResponseEntity.ok(new ResponseMessage("Success !!")));
    }
}
