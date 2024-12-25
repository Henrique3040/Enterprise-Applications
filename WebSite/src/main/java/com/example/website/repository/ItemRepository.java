package com.example.website.repository;

import com.example.website.model.ItemModel;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<ItemModel, Long> {

    public List<ItemModel> findAll();

    public List<ItemModel> findByCategory_Name(String category);
}
