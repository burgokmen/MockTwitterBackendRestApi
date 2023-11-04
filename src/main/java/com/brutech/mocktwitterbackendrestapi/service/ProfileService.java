package com.brutech.mocktwitterbackendrestapi.service;

import com.brutech.mocktwitterbackendrestapi.entity.Profile;

import java.util.List;
import java.util.Optional;


public interface ProfileService {
    List<Profile> getAllUsers();
    Profile getUserById(Long id);
    Profile saveUser(Profile profile);
    Profile deleteUser(Long id);

}
