package com.work.Terms_of_reference.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "users") // таблица БД для пользователей
public class User {
    @Id // Id назначается автоматически
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // связь с заявками(Application)
    List<Application> applications; // для заявок


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
