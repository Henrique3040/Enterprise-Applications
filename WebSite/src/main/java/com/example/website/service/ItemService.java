package com.example.website.service;

import com.example.website.model.CategoryModel;
import com.example.website.model.ItemModel;
import com.example.website.repository.CategoryRepository;
import com.example.website.repository.ItemRepository;
import com.example.website.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    public ItemModel getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + id));
    }


    /*
    * Haal alle items van uit de database
    *
    * */

    public List<ItemModel> getAllItems() {
        return itemRepository.findAll();
    }

    /*
    *
    * Haal producten op basis van specifiek categorie
    *
    * */
    public List<ItemModel> getItemByCategory(String category) {
        return itemRepository.findByCategory_Name(category);
    }

    /*
    *
    * Hal al producten met een bapaalde category
    * */
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addItem(ItemModel itemModel) {
        itemRepository.save(itemModel);
    }

    public void saveItem(ItemModel itemModel) {
        itemRepository.save(itemModel);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public boolean isItemUsedInReservation(Long id) {
        return reservationRepository.existsByItemId(id);
    }

}
