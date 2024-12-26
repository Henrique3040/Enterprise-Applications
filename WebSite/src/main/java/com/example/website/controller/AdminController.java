package com.example.website.controller;

import com.example.website.model.CategoryModel;
import com.example.website.model.ItemModel;
import com.example.website.model.UserModel;
import com.example.website.repository.CategoryRepository;
import com.example.website.service.CustomUserDetails;
import com.example.website.service.ItemService;
import com.example.website.service.ReservationService;
import com.example.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;


    @GetMapping("/itemsBoard")
    public String adminItems(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:/items";
        }
        model.addAttribute("items", itemService.getAllItems());
        return "adminItems";
    }


    @GetMapping("/users")
    public String adminUsers(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:/items";
        }

        model.addAttribute("users", userService.getAllUsers());
        return "adminUsers";
    }

    @GetMapping("/reservation")
    public String adminReports(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:items";
        }
        model.addAttribute("reservations", reservationService.getAllReservation());
        return "viewReservations";
    }


    /*
    * end point om users te deleten
    *
    * */
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:/items";
        }
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    /*
    * end point om resevaties te verwijderen
    * alleen admin kan dat doen
    *
    * */
    @GetMapping("/reservation/delete/{id}")
    public String deleteReservation(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:/items";
        }
        reservationService.deleteReservation(id);
        return "redirect:/admin/reservation";
    }


    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel userController = userDetails.getUser();
        if (!userController.getAdmin()) {
            return "redirect:/items";
        }
        UserModel user = userService.findById(id);
        model.addAttribute("user", user);
        return "editUsers";
    }


    /*
     *
     * end point om users info aan te passen
     * hier andere admin kan ook een user admin rechten geven
     *
     * */
    @PostMapping("/users/edit/{id}")
    public String updateUser(@PathVariable Long id, UserModel updatedUser, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel userController = userDetails.getUser();
        if (!userController.getAdmin()) {
            return "redirect:/items";
        }

        UserModel existingUser = userService.findById(id);


        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAdmin(updatedUser.getAdmin());

        // Opslaan in de database
        userService.save(existingUser);

        return "redirect:/admin/users";
    }


    /*
    * End point om naar de add Item page te gaan
    *
    * */
    @GetMapping("/add")
    public String addItemForm(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:/items";
        }

        model.addAttribute("categories", itemService.getAllCategories());
        return "addItem";
    }

    /*
    * End point om info van de nieuwe item te saven
    * eens form in page ediItem is ingediend
    *
    * */

    @PostMapping("/add")
    public String addItem(@RequestParam String name,
                          @RequestParam String description,
                          @RequestParam Long category,
                          @RequestParam(required = false) boolean available,
                          @AuthenticationPrincipal CustomUserDetails userDetails) {
        ItemModel item = new ItemModel();
        item.setName(name);
        item.setDescription(description);
        item.setCategory(categoryRepository.findById(category).orElseThrow());
        item.setAvailable(available);
        itemService.addItem(item);
        return "redirect:/items";
    }

    /*
    *
    * End point om items te verwijderen
    *
    * */
    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:/items";
        }
        itemService.deleteItem(id);
        return "redirect:/items";
    }


    /*
    * End point om naar edit item page te gaan
    *
    * */
    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable Long id, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:/items";
        }
        ItemModel item = itemService.getItemById(id);
        model.addAttribute("item", item);
        model.addAttribute("categories", itemService.getAllCategories());
        return "editItem";
    }

    /*
    * End point om naar item edit pagina te gaan
    *
    * */
    @PostMapping("/items/edit")
    public String updateItem(@ModelAttribute ItemModel updatedItem, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:/items";
        }

        if (updatedItem.getAvailable() == null) {
            updatedItem.setAvailable(false);  // If unchecked, set the value to false
        }

        ItemModel item = itemService.getItemById(updatedItem.getId());

        item.setName(updatedItem.getName());
        item.setDescription(updatedItem.getDescription());
        item.setAvailable(updatedItem.getAvailable());
        item.setCategory(updatedItem.getCategory());

        itemService.saveItem(item);
        return "redirect:/admin/itemsBoard";
    }

    /*
    * end point om naar category board te zien
    *
    * */
    @GetMapping("/categories")
    public String adminCategories(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:items";
        }
        model.addAttribute("categories", categoryRepository.findAll());
        return "category";
    }

    /*
     * end point om naar add category te zien
     *
     * */
    @GetMapping("/categories/add")
    public String addCategoryForm(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:items";
        }
        return "addCategory";
    }

    /*
     * end point om nieuwe category te saven
     *
     * */

    @PostMapping("/categories/add")
    public String addCategory(@RequestParam String name, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:items";
        }
        CategoryModel category = new CategoryModel();
        category.setName(name);
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    /*
     * end point om category edit page te zien met de spefieke id
     *
     * */
    @GetMapping("/categories/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:items";
        }
        CategoryModel category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
        model.addAttribute("category", category);
        return "editCategory";
    }

    /*
     * end point om category aanpasingen te saven
     *
     * */
    @PostMapping("/categories/edit")
    public String updateCategory(@RequestParam Long id, @RequestParam String name, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:items";
        }
        CategoryModel category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
        category.setName(name);
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    /*
     * end point om category te deleten
     *
     * */
    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserModel user = userDetails.getUser();
        if (!user.getAdmin()) {
            return "redirect:items";
        }
        categoryRepository.deleteById(id);
        return "redirect:/admin/categories";
    }



}
