package com.brutech.mocktwitterbackendrestapi.service;

import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;


public interface ProfileService extends UserDetailsService {
    List<Profile> getAllUsers();
    Profile getUserById(Long id);
    Profile registerUser(Profile profile);
    Profile deleteUser(Long id);
    Optional<Profile> getUserByEmail(String email);

}
