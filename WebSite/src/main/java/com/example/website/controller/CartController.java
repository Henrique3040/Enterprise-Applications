package com.example.website.controller;

import com.example.website.model.UserModel;
import com.example.website.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class CartController {

    private CartService cartService;

    @PostMapping("/cart/add/{itemId}")
    public String addItem(@PathVariable Long itemId, UserModel user ) {
        cartService.addItemToCart(user.getId(), itemId);
        return String.valueOf(ResponseEntity.ok("Item added to cat"));
    }


}
