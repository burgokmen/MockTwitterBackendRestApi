package com.brutech.mocktwitterbackendrestapi.controller;

import com.brutech.mocktwitterbackendrestapi.dto.LoginProfileDto;
import com.brutech.mocktwitterbackendrestapi.dto.ProfileResponse;
import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.service.AuthenticationService;
import com.brutech.mocktwitterbackendrestapi.service.ProfileService;
import com.brutech.mocktwitterbackendrestapi.util.Converter;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/profile")
@Validated
public class ProfileController {
    private ProfileService profileService;
    private AuthenticationService authenticationService;
    @Autowired
    public ProfileController(ProfileService profileService, AuthenticationService authenticationService) {
        this.profileService = profileService;
        this.authenticationService = authenticationService;
    }



    @PostMapping("/register")
    public ProfileResponse register(@Validated @RequestBody Profile profile){
            return Converter.profileResponseConverter(profileService
                    .registerUser(authenticationService.register(profile)));
    }

    @PostMapping("/login")
    public ProfileResponse login(@RequestBody LoginProfileDto loginProfileDto){
       Profile profile = authenticationService.login(loginProfileDto);
         return Converter.profileResponseConverter(profile);
    }

    @DeleteMapping("/{id}")
    public ProfileResponse deleteUser(@Positive @PathVariable Long id){
        return Converter.profileResponseConverter(profileService.deleteUser(id));
    }

   /* @PostMapping("/follow/{id}")
    public ProfileResponse followUser(@Positive @PathVariable Long id, @RequestBody Profile followedUser){
        Profile followerUser = profileService.getUserById(id);
        Profile followedUser2 = profileService.getUserById(followedUser.getId());
        followerUser.addFollowedUser(followedUser2);
        followedUser.addFollowerUser(followerUser);
        profileService.registerUser(followerUser);
        return Converter.profileResponseConverter(followerUser);
    }*/


}
