package com.example.website.controller;

import com.example.website.model.UserModel;
import com.example.website.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    private CartService cartService;

    /*
    *
    * End point om items in de cart toevoegen
    * */

    @PostMapping("/cart/add/{itemId}")
    public String addItem(@PathVariable Long itemId, UserModel user ) {
        cartService.addItemToCart(user.getId(), itemId);
        return String.valueOf(ResponseEntity.ok("Item added to cat"));
    }


    /*
    *
    * end pointom items te reserveren
    * */
    @PostMapping("/cart/checkout")
    public String checkoutCart(UserModel user) {

            cartService.checkoutCart(user.getId());
            return String.valueOf(ResponseEntity.ok("Cart checked out"));
    }



}
