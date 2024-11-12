package com.example.website.repository;

import com.example.website.model.CartModel;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<CartModel, Long> {
}
