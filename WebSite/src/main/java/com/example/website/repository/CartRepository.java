package com.example.website.repository;

import com.example.website.model.CartModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<CartModel, Long> {

    Optional<CartModel> findByCustomerId(Long customerId);

}