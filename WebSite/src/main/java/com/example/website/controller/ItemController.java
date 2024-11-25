package com.example.website.controller;


import com.example.website.model.ItemModel;
import com.example.website.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ItemController {

    private ItemService itemService;

    /*
    *
    * End point voor alle items te krijgen
    *
    * */
    @GetMapping("/items")
    public String getAllItems(Model model) {

     model.addAttribute("items", itemService.getAllItems());
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
