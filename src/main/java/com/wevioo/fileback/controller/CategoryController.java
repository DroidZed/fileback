package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@ResponseBody
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Category> getCategories() {
        return this.categoryService.getAllCategories();
    }

    @GetMapping(path = "/one/{id}")
    public @ResponseBody
    Category getOneCategory(@PathVariable Long id) {
        return this.categoryService.getOneCategory(id);
    }

    @GetMapping(path = "/image/get/{id}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    ResponseEntity<?> getCategoryImage(@PathVariable Long id) throws IOException {
        return this.categoryService.getCategoryImage(id);
    }

    @PostMapping(path = "/add")
    public void addCategory(@RequestBody Category cat) {
        this.categoryService.createCategory(cat);
    }

    @DeleteMapping(path = "/del/{id}")
    public void deleteOneCategory(@PathVariable Long id) {
        this.categoryService.removeCategory(id);
    }

    @DeleteMapping(path = "/del/all")
    public void deleteAllCategories() {
        this.categoryService.removeAllCategories();
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category newCat) {
        return this.categoryService.updateCategory(newCat, id);
    }

    @PostMapping(path = "/update/{catId}/pic")
    public ResponseEntity<?> updateCategoryPicture(@PathVariable Long catId, @RequestParam("file") MultipartFile image)
    {
       return this.categoryService.setCategoryPhoto(image,catId);
    }
}
