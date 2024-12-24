package com.example.website.controller;


import com.example.website.model.ItemModel;
import com.example.website.model.ReservationModel;
import com.example.website.model.UserModel;
import com.example.website.service.CartService;
import com.example.website.service.CustomUserDetails;
import com.example.website.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ReservationService reservationService;


    /*
    *
    * Endpoint om naar de cart page te geen
    * */

    @GetMapping
    public String viewCart( @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        // Fetch the cart items for the logged-in user
        UserModel user = userDetails.getUser();
        List<ItemModel> cartItems = cartService.getCartItemsByUserId(user.getId());
        model.addAttribute("cartItems", cartItems);
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
    @PostMapping("/checkout")
    public String checkoutCart(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {

        if (userDetails == null) {
            return "redirect:/login";
        }


            UserModel user = userDetails.getUser();
            cartService.checkoutCart(user.getId());

            List<ReservationModel> reservations = reservationService.getReservation(user.getId());

            model.addAttribute("reservations", reservations);

            return "cofirmationPage";
    }



}
