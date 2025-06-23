package com.example.bai8;

import com.example.bai8.Repo.CategoryRepository;
import com.example.bai8.model.Category;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping
    public String listCategory(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "listCategory";
    }
    @PostMapping("/add")
    public String addCategory(@RequestParam String name, Model model) {
        if(!name.trim().isEmpty()){
           Category category = new Category();
           category.setName(name.trim());
           categoryRepository.save(category);
        }
        return "redirect:/category";
    }
    @GetMapping("/update/{id}")
    public String updateCategory(@PathVariable Integer id, Model model) {
        Category category=categoryRepository.findById(id).orElse(null);
        model.addAttribute("category", category);
        return "CategoryEdit";
    }
    @PostMapping("/update")
    public String updateCategory( @ModelAttribute Category category, @RequestParam String name, Model model) {
        category.setName(name);
        categoryRepository.save(category);
        return "redirect:/category";

    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, Model model) {
        categoryRepository.deleteById(id);
        return "redirect:/category";
    }
}
