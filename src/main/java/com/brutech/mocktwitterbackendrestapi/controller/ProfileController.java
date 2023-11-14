package com.brutech.mocktwitterbackendrestapi.controller;

import com.brutech.mocktwitterbackendrestapi.dto.LoginProfileDto;
import com.brutech.mocktwitterbackendrestapi.dto.ProfileResponse;
import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.service.AuthenticationService;
import com.brutech.mocktwitterbackendrestapi.service.ProfileService;
import com.brutech.mocktwitterbackendrestapi.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private ProfileService profileService;
    private AuthenticationService authenticationService;
    @Autowired
    public ProfileController(ProfileService profileService, AuthenticationService authenticationService) {
        this.profileService = profileService;
        this.authenticationService = authenticationService;
    }



    @PostMapping("/register")
    public ProfileResponse register(@RequestBody Profile profile){
            return Converter.profileResponseConverter(profileService
                    .registerUser(authenticationService.register(profile)));
    }

    @PostMapping("/login")
    public ProfileResponse login(@RequestBody LoginProfileDto loginProfileDto){
       Profile profile = authenticationService.login(loginProfileDto);
         return Converter.profileResponseConverter(profile);
    }

    @DeleteMapping("/{id}")
    public ProfileResponse deleteUser(@PathVariable Long id){
        return Converter.profileResponseConverter(profileService.deleteUser(id));
    }
}
