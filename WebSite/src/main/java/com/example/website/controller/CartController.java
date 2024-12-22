package com.example.website.controller;


import com.example.website.model.UserModel;
import com.example.website.service.CartService;
import com.example.website.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    /*
    *
    * Endpoint om naar de cart page te geen
    * */

    @GetMapping
    public String viewCart( UserModel user) {

        if (user == null) {
            return "redirect:/login";
        }

        return "cart";
    }



    /*
    *
    * End point om items in de cart toevoegen
    * */

    @PostMapping("/add/{itemId}")
    public String addItem(@PathVariable Long itemId, @AuthenticationPrincipal CustomUserDetails userDetails ) {

        if (userDetails == null) {
            throw new IllegalArgumentException("User must be logged in to add items to the cart.");
        }

        UserModel user = userDetails.getUser();
        cartService.addItemToCart(user.getId(), itemId);

        return "redirect:/cart";
    }


    /*
    *
    * end pointom items te reserveren
    * */
    @PostMapping("/cart/checkout")
    public String checkoutCart(UserModel user) {

            cartService.checkoutCart(user.getId());
            return "redirect:/cart";
    }



}
