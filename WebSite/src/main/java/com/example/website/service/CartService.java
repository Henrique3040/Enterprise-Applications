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

    private CartRepository cartRepository;
    private ItemRepository itemRepository;
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ItemRepository itemRepository, ReservationRepository reservationRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
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


}
