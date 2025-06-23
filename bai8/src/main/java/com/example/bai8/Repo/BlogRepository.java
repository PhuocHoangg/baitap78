package com.example.bai8.Repo;

import com.example.bai8.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByCategory_Id(Integer categoryId, Pageable pageable);
    Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Blog> findByTitleContainingIgnoreCaseAndCategory_Id(String title, Integer categoryId, Pageable pageable);

}
