package com.example.website.repository;

import com.example.website.model.CategoryModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryModel, Long> {
    List<CategoryModel> findAll();
}
