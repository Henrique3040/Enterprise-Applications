package com.example.website.service;

import com.example.website.model.CartModel;
import com.example.website.model.ItemModel;
import com.example.website.model.ReservationModel;
import com.example.website.model.UserModel;
import com.example.website.repository.CartRepository;
import com.example.website.repository.ItemRepository;
import com.example.website.repository.ReservationRepository;
import com.example.website.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ItemRepository itemRepository, ReservationRepository reservationRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }


    public List<ItemModel> getCartItemsByUserId(Long userId) {
        // Find the cart or create one if it doesn't exist
        CartModel cart = cartRepository.findByUser_Id(userId).orElseGet(() -> {
            CartModel newCart = new CartModel();
            UserModel user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + userId));
            newCart.setUser(user);
            newCart.setItems(new ArrayList<>());
            return cartRepository.save(newCart);
        });

        return cart.getItems();
    }





    @Transactional
    public void addItemToCart(Long userId, Long productId) {

        CartModel cart = cartRepository.findByUser_Id(userId).orElse(null);

        if (cart == null) {
            cart = new CartModel();
            UserModel user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found" + userId));
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
            cartRepository.save(cart);

        }

        ItemModel item = itemRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found" + productId));

        if (!cart.getItems().contains(item)) {
            cart.getItems().add(item);
        }

        cartRepository.save(cart);

    }

    /*
    *
    * Checkout functie
    * plaats alle items van kaar in een reservetion model
    * verwijdeert dan de itams van de kart
    *
    * */

    public void checkoutCart(Long userId) {

        /*
        *
        * eerst de items de cart omzeten naar reservation
        *
        * */
        CartModel cart = cartRepository.findByUser_Id(userId).orElse(null);
        if (cart == null || cart.getItems().isEmpty()) {

            throw new IllegalArgumentException("Cart is empty");

        }

        List<ReservationModel> reservations = cart.getItems().stream()
                .map(item -> {
                    ReservationModel reservation = new ReservationModel();
                    reservation.setUser(cart.getUser());
                    reservation.setItem(item);
                    reservation.setStartDate(LocalDate.now());
                    reservation.setEndDate(LocalDate.now().plusDays(7));/* 7 dage reservatie */
                    return reservation;
                })
                .collect(Collectors.toList());

        reservationRepository.saveAll(reservations);

        cart.getItems().clear();
        cartRepository.save(cart);

    }


    public void eraseItem(Long productId, Long userId) {
        CartModel cart = cartRepository.findByUser_Id(userId).orElse(null);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        System.out.println("Items in cart: " + cart.getItems());
        System.out.println("Attempting to remove item with ID: " + productId);


        // Controleer of het item aanwezig is
        boolean itemExists = cart.getItems().stream()
                .anyMatch(item -> item.getId().equals(productId));
        if (!itemExists) {
            throw new IllegalArgumentException("Item not found in cart");
        }

        // Verwijder het item en sla de wijzigingen op
        cart.getItems().removeIf(item -> item.getId().equals(productId));
        cartRepository.save(cart);

    }


}
