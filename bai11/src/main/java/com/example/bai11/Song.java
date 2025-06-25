package com.example.bai11;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   Integer id;
    @NotBlank(message="Not Empty")
    @Size(min=3, max=800)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Không được chứa ký tự đặc biệt")
   String title;
    @NotBlank
    @Size(min=2, max=1000)
    @Pattern(regexp = "^[a-zA-Z0-9,\\s]+$", message = "Chỉ được chứa chữ cái, số, khoảng trắng và dấu phẩy"
    )
   String type;
   @ManyToOne
   Artist artist;
}
