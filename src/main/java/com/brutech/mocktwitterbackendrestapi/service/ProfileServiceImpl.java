package com.brutech.mocktwitterbackendrestapi.service;

import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{
    private ProfileRepository profileRepository;
    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> getAllUsers() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getUserById(Long id) {
        Optional<Profile> profileOptional = profileRepository.findById(id);
          if (profileOptional.isPresent()){
              return profileOptional.get();
          }
          else {
              throw new RuntimeException("User not found for id :: " + id);
          }
    }

    @Override
    public Profile saveUser(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile deleteUser(Long id) {
       Profile profile = getUserById(id);
       profileRepository.delete(profile);
       return profile;
    }

}
