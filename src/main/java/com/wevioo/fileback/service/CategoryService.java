package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.CategoryNotFoundException;
import com.wevioo.fileback.interfaces.CategoryManager;
import com.wevioo.fileback.interfaces.ImageManager;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements CategoryManager {

    private final CategoryRepository categoryRepository;
    private final ImageManager imageManager;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<?> setCategoryPhoto(MultipartFile photo, Long cat_id) {

        Optional<Category> opt = this.categoryRepository.findById(cat_id);

            if(opt.isEmpty())
            {
                return ResponseEntity.badRequest().body("Category not found !");
            }

            Category cat = opt.get();

            if(photo == null) {
                return ResponseEntity.badRequest().body("Photo is null !");
            }

            String imageName =  cat.getNom().replace(' ','_');

            imageName = imageName.concat(".jpg");

            cat.setImageName(imageName);

            this.categoryRepository.save(cat);

           this.imageManager
                    .uploadToLocalFileSystem(photo,
                            "category",
                           imageName
                    );

           return ResponseEntity.ok(new ResponseMessage("Okay !"));
    }

    public void createCategory(Category cat) {

        this.categoryRepository.save(cat);
    }

    @Override
    public List<String> getCategoriesNames() {
        return this.categoryRepository.getCategoriesNames();
    }

    public Category getOneCategory(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void removeCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

    public void removeAllCategories() {
        this.categoryRepository.deleteAll();
    }

    @Transactional
    public ResponseEntity<?> updateCategory(Category newCat, Long id) throws CategoryNotFoundException {

        return this.categoryRepository.findById(id).map(category ->
                {
                    category.setNom(newCat.getNom());

                    category.setDescription(newCat.getDescription());

                    this.categoryRepository.save(category);

                    return ResponseEntity.ok(new ResponseMessage("Category mise à jour avec succées !"));
                }
        ).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public ResponseEntity<?> getCategoryImage(Long id) throws IOException {

        Optional<Category> opt = this.categoryRepository.findById(id);

        if(opt.isEmpty())
            return ResponseEntity.badRequest().body("Category not found !");

        Category cat = opt.get();

        String name = cat.getImageName();

        if(name == null)
            return ResponseEntity.badRequest().body("Image not found !");

        return ResponseEntity.ok(this.imageManager.getImageWithMediaType(name, "category"));
    }
}
