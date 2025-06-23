package com.example.bai8.Repo;

import com.example.bai8.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
