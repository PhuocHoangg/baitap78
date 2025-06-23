package com.example.bai8.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
