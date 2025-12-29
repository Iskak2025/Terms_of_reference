package com.work.Terms_of_reference.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName; // имя и фамилия
    private String position; // должность

    @Pattern(regexp = "^\\+996\\d{9}$") // номер телефона должен начинатся на "+996"
    private String phone;

    private LocalDateTime createdAt; // дата создания
    private LocalDateTime updatedAt; // дата изменения

    @ElementCollection // может содержать несколько значений
    @CollectionTable( // таблица для product
            name = "application_products",
            joinColumns = @JoinColumn(name = "application_id")
    )
    @Column(name = "product")
    private List<String> products;


    @ManyToOne(fetch = FetchType.LAZY)  // связь с пользователем(User)
    @JoinColumn(name = "user_id", nullable = false) // внешний ключ
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
