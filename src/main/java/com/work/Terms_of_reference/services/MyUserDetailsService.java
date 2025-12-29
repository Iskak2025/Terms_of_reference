package com.work.Terms_of_reference.services;

import com.work.Terms_of_reference.entity.User;
import com.work.Terms_of_reference.entity.UserPrincipal;
import com.work.Terms_of_reference.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {   // проверка пользователя
            throw new UsernameNotFoundException(username);
        }
        return new  UserPrincipal(user);
    }

}
