package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.CategoryNotFoundException;
import com.wevioo.fileback.helper.Base64Treatment;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        List<Category> listOfCategories = categoryRepository.findAll();
        Base64Treatment base64Treatment = new Base64Treatment();

        for (Category category : listOfCategories)
            if (category.getImage() != null) {
                base64Treatment.setBase64String(category.getImage());
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
        return this.categoryRepository.findById(id).map(data -> {
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

    public ResponseEntity<?> updateCategory(Category newCat, Long id) throws CategoryNotFoundException
    {
        Base64Treatment base64Treatment = null;

        if(newCat.getImage() != null && newCat.getImage().length > 0)
        {
            base64Treatment = new Base64Treatment(newCat.getImage());
        }

       Optional<Category> optionalCat = this.categoryRepository.findById(id);

       ResponseMessage resp = new ResponseMessage();
               
       if(optionalCat.isPresent())
       {
            Category category = optionalCat.get();

            if(newCat.getNom() != null && newCat.getNom() != "")
            {
                category.setNom(newCat.getNom());
            }

            if(newCat.getDescription() != null && newCat.getDescription() != "")
            {
                category.setDescription(newCat.getDescription());
            }

            if(base64Treatment != null && base64Treatment.getBase64String().length > 0)
            {
                category.setImage(base64Treatment.compressBytes());
            }

            this.categoryRepository.save(category);

            resp.setMessage("Category mise à jour avec succées !");
       }
       else
        {
            throw new CategoryNotFoundException(id);
        }
        return ResponseEntity.ok(resp);
    }
}
