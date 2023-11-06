package com.brutech.mocktwitterbackendrestapi.controller;

import com.brutech.mocktwitterbackendrestapi.dto.ProfileResponse;
import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.service.ProfileService;
import com.brutech.mocktwitterbackendrestapi.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private ProfileService profileService;
    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/")
    public ProfileResponse registerUser(@RequestBody Profile profile){
            return Converter.profileResponseConverter(profileService.registerUser(profile));
    }

    @DeleteMapping("/{id}")
    public ProfileResponse deleteUser(@PathVariable Long id){
        return Converter.profileResponseConverter(profileService.deleteUser(id));
    }
}
