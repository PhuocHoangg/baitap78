package com.example.bai11;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class SongDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank(message="Not Empty")
    @Size(min=5, max=800)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Không được chứa ký tự đặc biệt")
    String title;
    @NotBlank
    @Size(min=5, max=1000)
    @Pattern(regexp = "^[a-zA-Z0-9,\\s]+$", message = "Chỉ được chứa chữ cái, số, khoảng trắng và dấu phẩy"
    )
    String type;
    @NotBlank
    @Size(min=5, max=300)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Họ không được chứa ký tự đặc biệt")
    String artist;
}
