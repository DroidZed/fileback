package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.exceptions.CategoryNotFoundException;
import com.wevioo.fileback.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryManager {

    List<Category> getAllCategories();
    Category getOneCategory(Long id);
    void createCategory(Category cat);
    void removeCategory(Long id);
    void removeAllCategories();
    ResponseEntity<?> setCategoryPhoto(MultipartFile photo, Long cat_id);
    ResponseEntity<?> updateCategory(Category newCat, Long id) throws CategoryNotFoundException;
    ResponseEntity<?> getCategoryImage(Long id) throws IOException;
}
