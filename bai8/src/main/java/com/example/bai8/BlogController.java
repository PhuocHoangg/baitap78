package com.example.bai8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
     @Autowired
    BlogRepository blogRepository;
     @GetMapping
public String listTitleBlog(Model model) {
         List<Blog> blogs = blogRepository.findAll();
         model.addAttribute("blogs", blogs);
         return "blog_titles";
     }
    @GetMapping("/blog/{id}")
    public String viewBlogDetail(@PathVariable Integer id, Model model) {
        Blog blog = blogRepository.findById(id).orElse(null);
        model.addAttribute("blog", blog);
        return "blog_detail";
    }
}
