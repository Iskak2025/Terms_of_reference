package com.work.Terms_of_reference.services;

import com.work.Terms_of_reference.DTO.ApplicationDTO;
import com.work.Terms_of_reference.entity.Application;
import com.work.Terms_of_reference.entity.User;
import com.work.Terms_of_reference.repositories.ApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepo;

    public List<ApplicationDTO> getAllApplications(User user) {   // логика метода get_all
        List<Application> applications = applicationRepo.findByUser(user);
        List<ApplicationDTO> result = new ArrayList<>();

        for (Application app : applications) { // пробегается по заявкам
            ApplicationDTO dto = new ApplicationDTO();
            dto.setId(app.getId());
            dto.setFullName(app.getFullName()); // копируют значения в DTO
            dto.setPosition(app.getPosition());
            dto.setProducts(app.getProducts());
            dto.setPhone(app.getPhone());
            result.add(dto); // вводит значение DTO
        }

        return result; // возвращает все заявки данного пользователя
    }

    public Application getApplicationByIdEntity(long id) {   // ишет заявку
        return applicationRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка не найдена: " + id));
    }

    public ApplicationDTO getApplicationById(long id, User user) {   // логика метода /get/{id}
        Application app = getApplicationByIdEntity(id);

        if (app.getUser().getId() != user.getId()) { // пользователь не может смотреть чужие заявки
            throw new RuntimeException("Нет доступа");
        }

        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(app.getId());
        dto.setFullName(app.getFullName()); // копируют значение и сохраняют в DTO
        dto.setPosition(app.getPosition());
        dto.setProducts(app.getProducts());
        dto.setPhone(app.getPhone());

        return dto; // возвращают DTO
    }


    public Application createApplication(ApplicationDTO dto, User user) {   // логика метода /create
        Application application = new Application();
        application.setFullName(dto.getFullName());
        application.setPosition(dto.getPosition());
        application.setPhone(dto.getPhone());
        application.setProducts(dto.getProducts());  // сохраняет значение и сохраняют в DTO
        application.setUser(user);
        application.setCreatedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());

        return applicationRepo.save(application); // сохраняет в БД
    }

    public Application changeApplication(long id, ApplicationDTO dto, User user) { // логика метода /change/{id}
        Application existing = getApplicationByIdEntity(id);

        if (existing.getUser().getId() != user.getId()) { // не может изменять чужие заявки
            throw new RuntimeException("Эту заявку нельзя редактировать");
        }

        existing.setFullName(dto.getFullName());
        existing.setPosition(dto.getPosition());
        existing.setPhone(dto.getPhone());
        existing.setProducts(dto.getProducts());  // изменяет занчение и сохраняет в DTO
        existing.setUpdatedAt(LocalDateTime.now());

        return applicationRepo.save(existing); // сохраняет в БД
    }

    public void deleteApplication(long id, User user) { // логика метода /delete/{id}
        Application existing = getApplicationByIdEntity(id);

        if (existing.getUser().getId() != user.getId()) { // не может удалять чужие заявки
            throw new RuntimeException("Эту заявку нельзя удалить");
        }

        applicationRepo.delete(existing); // удаляет из БД
    }
}
