package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT nom from Category order by nom")
    List<String> getCategoriesNames();
}
