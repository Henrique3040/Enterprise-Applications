package com.example.website.service;

import com.example.website.model.ItemModel;
import com.example.website.repository.CategoryRepository;
import com.example.website.repository.ItemRepository;
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
    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
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

}
