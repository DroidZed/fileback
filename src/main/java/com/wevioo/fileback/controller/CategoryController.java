package com.wevioo.fileback.controller;

import com.wevioo.fileback.model.Category;
import com.wevioo.fileback.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@ResponseBody
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path="/all")
    public @ResponseBody List<Category> getCategories () {
        return this.categoryService.getAllCategories();
    }
}
