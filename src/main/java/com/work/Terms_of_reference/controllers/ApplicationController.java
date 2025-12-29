package com.work.Terms_of_reference.controllers;

import com.work.Terms_of_reference.DTO.ApplicationDTO;
import com.work.Terms_of_reference.entity.Application;
import com.work.Terms_of_reference.entity.User;
import com.work.Terms_of_reference.services.ApplicationService;
import com.work.Terms_of_reference.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @GetMapping("/get_all") // метод выводит все заявки
    @PreAuthorize("isAuthenticated()")
    public List<ApplicationDTO> getAllApplications(Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getName());
        return applicationService.getAllApplications(user);
    }


    @GetMapping("/get/{id}") // метод выводит заявку по id
    @PreAuthorize("isAuthenticated()")
    public ApplicationDTO getApplicationById(@PathVariable long id, Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getName());
        return applicationService.getApplicationById(id, user);
    }

    @PostMapping("/create") // метод создает заявку
    @PreAuthorize("isAuthenticated()")
    public Application createApplication(
            @Valid @RequestBody ApplicationDTO dto,
            Authentication auth
    ) {
        User user = userService.getUserByUsername(auth.getName());
        return applicationService.createApplication(dto, user);
    }

    @PutMapping("/change/{id}") // изменяет заявку
    @PreAuthorize("isAuthenticated()")
    public Application updateApplication(
            @PathVariable long id,
            @Valid @RequestBody ApplicationDTO dto,
            Authentication auth
    ) {
        User user = userService.getUserByUsername(auth.getName());
        return applicationService.changeApplication(id, dto, user);
    }

    @DeleteMapping("/delete/{id}") // удаляет заявку по id
    @PreAuthorize("isAuthenticated()")
    public void deleteApplication(@PathVariable long id, Authentication auth) {
        User user = userService.getUserByUsername(auth.getName());
        applicationService.deleteApplication(id, user);
    }
}
