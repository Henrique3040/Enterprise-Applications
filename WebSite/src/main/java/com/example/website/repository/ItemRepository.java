package com.example.website.repository;

import com.example.website.model.ItemModel;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemModel, Long> {
}
