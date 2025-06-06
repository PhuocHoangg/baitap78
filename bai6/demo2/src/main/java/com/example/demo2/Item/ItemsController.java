package com.example.demo2.Item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    private static List<Items> itemsList = new ArrayList<>();
    static{Items item=new Items();
    item.setId(1);
    item.setName("Sach");
    item.setQuality("100%");
    itemsList.add(item);
    }

    private int currentId = 1;

    @GetMapping
    public String showItems(Model model) {
        model.addAttribute("items", itemsList);
        return "items"; // JSP file: items.jsp
    }

    @PostMapping("/add")
    public String addItem(@RequestParam String name,
                          @RequestParam String quality) {
        Items item = new Items();
        item.setId(currentId++);
        item.setName(name);
        item.setQuality(quality);
        itemsList.add(item);
        return "redirect:/items";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String quality) {
        for (Items item : itemsList) {
            if (item.getId() == id) {
                item.setName(name);
                item.setQuality(quality);
                break;
            }
        }
        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) {
        itemsList.removeIf(item -> item.getId() == id);
        return "redirect:/items";
    }
}
