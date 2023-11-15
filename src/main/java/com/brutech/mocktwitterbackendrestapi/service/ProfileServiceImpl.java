package com.brutech.mocktwitterbackendrestapi.service;

import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.exceptions.TwitterException;
import com.brutech.mocktwitterbackendrestapi.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
              throw new TwitterException("User not found for id :: " + id, HttpStatus.NOT_FOUND);
          }
    }

    @Override
    public Profile registerUser(Profile profile) {
        String email = profileRepository.emailChecker(profile.getEmail());
        String cellular = profileRepository.phoneChecker(profile.getCellular());
        String userHandle = profileRepository.usernameChecker(profile.getUserHandle());
        if (email != null || cellular != null || userHandle != null){
             throw new TwitterException("Credentials already exists", HttpStatus.BAD_REQUEST);
        }
        return profileRepository.save(profile);
    }

    @Override
    public Profile deleteUser(Long id) {
       Profile profile = getUserById(id);
       profileRepository.delete(profile);
       return profile;
    }

    @Override
    public Optional<Profile> getUserByEmail(String email) {
      Optional<Profile> profileOptional = profileRepository.getUserByEmail(email);
        if (profileOptional.isPresent()){
            return profileOptional;
        }
        else {
            throw new TwitterException("User not found for email :: " + email, HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return profileRepository.getUserByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User credentials are not valid"));
    }





}
