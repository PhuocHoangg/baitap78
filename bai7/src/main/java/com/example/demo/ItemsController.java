package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemsRepository itemsRepository;

    @GetMapping
    public String showItems(Model model) {
        List<Items> items = itemsRepository.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @PostMapping("/add")
    public String addItem(@RequestParam String name,
                          @RequestParam String quality) {
        Items item = new Items();
        item.setName(name);
        item.setQuality(quality);
        itemsRepository.save(item);
        return "redirect:/items";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String quality) {
        Items item=itemsRepository.findById(id).orElse(null);
        if(item!=null) {
            item.setName(name);
            item.setQuality(quality);
            itemsRepository.save(item);
        }
        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) {
        itemsRepository.deleteById(id);
        return "redirect:/items";
    }
}
