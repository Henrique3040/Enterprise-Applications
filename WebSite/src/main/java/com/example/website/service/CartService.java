package com.example.website.service;

import com.example.website.model.CartModel;
import com.example.website.model.ItemModel;
import com.example.website.model.UserModel;
import com.example.website.repository.CartRepository;
import com.example.website.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    private CartRepository cartRepository;
    private ItemRepository itemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }



    @Transactional
    public void addItemToCart(Long userId, Long productId) {

        CartModel cart = cartRepository.findByCustomerId(userId).orElse(null);

        if (cart == null) {
            cart = new CartModel();
            UserModel user = new UserModel();
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


}
