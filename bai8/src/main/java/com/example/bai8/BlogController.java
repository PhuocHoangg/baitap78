package com.example.bai8;

import com.example.bai8.Repo.AuthorRepository;
import com.example.bai8.Repo.BlogRepository;
import com.example.bai8.Repo.CategoryRepository;
import com.example.bai8.model.Author;
import com.example.bai8.model.Blog;
import com.example.bai8.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class BlogController {
     @Autowired
     BlogRepository blogRepository;
     @Autowired
     CategoryRepository categoryRepository;
     @Autowired
     AuthorRepository authorRepository;
     @GetMapping("/blogs")
    public String categories(Model model) {
         model.addAttribute("categories", categoryRepository.findAll());
         return "categories";
     }
     @GetMapping("/blogs/category")
  public String blogCategory(@RequestParam("categoryId") Integer categoryId,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size,
                             @RequestParam(required = false) String keyword,
                             Model model) {
         Pageable pageable = PageRequest.of(page, 5);
         Page<Blog> blogPage;

         if (keyword != null && !keyword.isEmpty()) {
             if (categoryId != null) {
                 blogPage = blogRepository.findByTitleContainingIgnoreCaseAndCategory_Id(keyword, categoryId, pageable);
             } else {
                 blogPage = blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
             }
         } else {
             if (categoryId != null) {
                 blogPage = blogRepository.findByCategory_Id(categoryId, pageable);
             } else {
                 blogPage = blogRepository.findAll(pageable);
             }
         }
         model.addAttribute("blogs", blogPage.getContent());
         model.addAttribute("totalPages", blogPage.getTotalPages());
         model.addAttribute("currentPage", page);
         model.addAttribute("categories", categoryRepository.findAll());
         model.addAttribute("selectedCategoryId", categoryId);
         model.addAttribute("keyword", keyword);
         return "blogs_by_category";
     }
     @GetMapping("add")
    public String add(Model model) {
         Blog blog = new Blog();
         model.addAttribute("blog", blog);
         model.addAttribute("categories", categoryRepository.findAll());
         model.addAttribute("author",authorRepository.findAll());
         return "add";
     }
    @PostMapping("/add")
    public String save(@ModelAttri  bute Blog blog,
                       @RequestParam String authorName) {
        Author author = authorRepository.findByName(authorName);
        if (author == null) {
            author = new Author();
            author.setName(authorName);
            authorRepository.save(author);
        }
        blog.setAuthor(author);
        blogRepository.save(blog);

        return "redirect:/blogs";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
         Blog blog = blogRepository.findById(id).orElse(null);
         model.addAttribute("blog", blog);
         model.addAttribute("categories", categoryRepository.findAll());
         return "edit";
    }
    @PostMapping("/update")
    public String updateBlog(@ModelAttribute Blog blog,
                             @RequestParam String authorName
                           ) {

        Author author = authorRepository.findByName(authorName);
        if (author == null) {
            author = new Author();
            author.setName(authorName);
            authorRepository.save(author);
        }
        blog.setAuthor(author);
        blogRepository.save(blog);
            return "redirect:/blogs";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,
    Model model) {
         Blog blog=blogRepository.findById(id).orElse(null);
         Integer catId=blog.getCategory().getId();
        blogRepository.deleteById(id);
        return "redirect:/blogs/category?categoryId=" + catId;


    }



}
