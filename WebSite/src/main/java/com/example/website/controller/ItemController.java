package com.example.website.controller;


import com.example.website.model.ItemModel;
import com.example.website.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /*
    *
    * End point voor alle items te krijgen
    *
    * */
    @GetMapping("/items")
    public String getAllItems(ModelMap model) {

     model.addAttribute("items", itemService.getAllItems());
     List<ItemModel> items = itemService.getAllItems();
     System.out.println("Retrieved items: " + items);
     return "catalogus";
    }


    /*
    *
    * End point om items per specifieke category te aanroepen
    *
    * */
    @GetMapping("/items/category/{category}")
    public List<ItemModel> getItemsByCategory(String category) {
        return itemService.getItemByCategory(category);
    }

}
