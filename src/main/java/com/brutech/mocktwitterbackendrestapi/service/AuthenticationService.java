package com.brutech.mocktwitterbackendrestapi.service;

import com.brutech.mocktwitterbackendrestapi.dto.LoginProfileDto;
import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.exceptions.TwitterException;
import com.brutech.mocktwitterbackendrestapi.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private ProfileRepository profileRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthenticationService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Profile register(Profile profile){
    String encodedPassword = passwordEncoder.encode(profile.getPassword());
    Profile profile1 = new Profile();
    profile1.setEmail(profile.getEmail());
    profile1.setCellular(profile.getCellular());
    profile1.setUserHandle(profile.getUserHandle());
    profile1.setBirthday(profile.getBirthday());
    profile1.setFirstName(profile.getFirstName());
    profile1.setLastName(profile.getLastName());
    profile1.setProfilePicture(profile.getProfilePicture());
    profile1.setProfileWalpaper(profile.getProfileWalpaper());
    profile1.setCreatedAt(profile.getCreatedAt());
    profile1.setLocation(profile.getLocation());
    profile1.setPassword(encodedPassword);
    return profile1;
    }

    public Profile login(LoginProfileDto    loginProfileDto){
        Optional<Profile> profileOptional = profileRepository.getUserByEmail(loginProfileDto.email());
        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            boolean isPasswordMatched = passwordEncoder
                    .matches(loginProfileDto.password(), profile.getPassword());
            if (isPasswordMatched) {
                return profile;
            }
        }
        throw new TwitterException("Invalid credentials", HttpStatus.BAD_REQUEST);
    }

}
