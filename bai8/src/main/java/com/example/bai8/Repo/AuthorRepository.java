package com.example.bai8.Repo;

import com.example.bai8.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByName(String name);
}
