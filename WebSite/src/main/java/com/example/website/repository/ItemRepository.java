package com.example.website.repository;

import com.example.website.model.ItemModel;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<ItemModel, Long> {

    public List<ItemModel> findAll();

    public Optional<ItemModel> findById(Long id);

    public List<ItemModel> findByCategory_Name(String category);
}
