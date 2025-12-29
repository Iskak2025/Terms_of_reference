package com.work.Terms_of_reference.DTO;

import com.work.Terms_of_reference.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;

public class ApplicationDTO {

    @NotNull // чтобы не был равен null
    private Long id;

    @NotBlank // обязателен для заполнения
    private String fullName;

    @NotBlank
    private String position;

    @Pattern(regexp = "^\\+996\\d{9}$") // номер должен начинатся на "+996"
    private String phone;

    @NotEmpty // не должен быть пустым
    private List<String> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public @NotEmpty List<String> getProducts() {
        return products;
    }

    public void setProducts(@NotEmpty List<String> products) {
        this.products = products;
    }
}
