package com.example.bai10;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.processing.Pattern;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    Integer id;
    @NotBlank(message = "Họ không được để trống")
    @Size(min = 5, max = 45, message = "Họ phải từ 5 đến 45 ký tự")
    private String firstName;
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, max = 45, message = "Tên phải từ 5 đến 45 ký tự")
    private String lastName;
    @Min(value = 18, message = "Tuổi phải >= 18")
    private int age;
    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

}
