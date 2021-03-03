package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.CategoryNotFoundException;
import com.wevioo.fileback.helper.Base64Treatment;
import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private Base64Treatment base64Treatment;

    public List<Category> getAllCategories() {
        List<Category> listOfCategories = categoryRepository.findAll();

        for(Category category : listOfCategories)
            if(category.getImage() != null) {
                Base64Treatment base64Treatment = new Base64Treatment(category.getImage());
                category.setImage(base64Treatment.decompressBytes());
            }

        return listOfCategories;
    }

    public void createCategory(Category cat) {
        Base64Treatment base64Treatment = new Base64Treatment(cat.getImage());
        cat.setImage(base64Treatment.compressBytes());
       this.categoryRepository.save(cat);
    }

    public Category getOneCategory(Long id) {
        return this.categoryRepository.findById(id)
                    .map(data -> {
                        Base64Treatment base64Treatment = new Base64Treatment(data.getImage());
                        data.setImage(base64Treatment.decompressBytes());
                        return data;
                    }).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void removeCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

    public void removeAllCategories() {
        this.categoryRepository.deleteAll();
    }

    public ResponseEntity<?> updateCategory(Category newCat, Long id) {

        if(newCat.getImage() != null && newCat.getImage().length > 0) {
            base64Treatment = new Base64Treatment(newCat.getImage());
        }
        return this.categoryRepository.findById(id)
                .map(category -> {
                    if(newCat.getNom() != null && newCat.getNom() != ""){
                        category.setNom(newCat.getNom());
                    }
                    if(newCat.getDescription() != null && newCat.getDescription() != ""){
                        category.setDescription(newCat.getDescription());
                    }
                    if(base64Treatment != null){
                    category.setImage(base64Treatment.compressBytes());
                    this.categoryRepository.save(category);
                    category.setImage(base64Treatment.decompressBytes());
                    }
                    return ResponseEntity.ok(category);
                }).orElseGet(() -> {
                    newCat.setIdCat(id);
                    newCat.setImage(base64Treatment.compressBytes());
                    this.categoryRepository.save(newCat);
                    newCat.setImage(base64Treatment.decompressBytes());
                    return ResponseEntity.ok(newCat);
                });
    }

}
