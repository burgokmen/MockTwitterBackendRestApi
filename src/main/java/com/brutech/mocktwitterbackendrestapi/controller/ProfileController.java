package com.brutech.mocktwitterbackendrestapi.controller;

import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private ProfileService profileService;
    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/")
    public Profile registerUser(@RequestBody Profile profile){
        return profileService.saveUser(profile);
    }

    @DeleteMapping("/{id}")
    public Profile deleteUser(@PathVariable Long id){
        return profileService.deleteUser(id);
    }
}
