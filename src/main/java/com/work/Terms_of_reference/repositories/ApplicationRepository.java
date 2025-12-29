package com.work.Terms_of_reference.repositories;

import com.work.Terms_of_reference.entity.Application;
import com.work.Terms_of_reference.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUser(User user);
}
