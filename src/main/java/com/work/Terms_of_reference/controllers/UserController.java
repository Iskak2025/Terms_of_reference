package com.work.Terms_of_reference.controllers;

import com.work.Terms_of_reference.DTO.UserDTO;
import com.work.Terms_of_reference.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login") // метод для аутификации
    public ResponseEntity<String> login(@Valid @RequestBody UserDTO userDTO) {
        try {
            userService.login(userDTO);
            return ResponseEntity.ok("Вход прошел успешно"); // если пользователь авторизован, если нет то возвращает ошибку 401
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(401)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/register") // метод для авторизации
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO) {
        try {
            userService.register(userDTO);
            return ResponseEntity.ok("Регистрация прошла успешно"); // если пользователь авторизован
        } catch (RuntimeException e) {
            return ResponseEntity        // если не правельный запрос то возвращает ошибку
                    .badRequest()
                    .body(e.getMessage());
        }
    }


}
