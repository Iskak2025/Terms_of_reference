package com.work.Terms_of_reference.services;

import com.work.Terms_of_reference.DTO.UserDTO;
import com.work.Terms_of_reference.entity.User;
import com.work.Terms_of_reference.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void login(UserDTO userDTO) { // логика метода /login

        User user = userRepo.findByUsername(userDTO.getUsername());

        if (user == null) { // проверка пользователя
            throw new RuntimeException("Пользователь не найден");
        }

        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) { // проверка пароля
            throw new RuntimeException("Не верный пороль");
        }
    }

    public void register(UserDTO userDTO) { // логика метода /register

        if (userRepo.findByUsername(userDTO.getUsername()) != null) { // проверка занято ли имя
            throw new RuntimeException("имя занято");
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword())); // хеширует пароль

        userRepo.save(newUser); // сохраняет в БД
    }

    public User getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) { // проверка пользователя
            throw new RuntimeException("Пользователь не найден: " + username);
        }
        return user;
    }
}
