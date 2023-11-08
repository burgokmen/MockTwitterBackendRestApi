package com.brutech.mocktwitterbackendrestapi.repository;

import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT p.email FROM twitter.profile AS p WHERE p.email =:email", nativeQuery = true)
    String emailChecker(String email);

    @Query(value = "SELECT p.phone FROM twitter.profile AS p WHERE p.phone =:phone", nativeQuery = true)
    String phoneChecker(String phone);

    @Query(value = "SELECT p.username FROM twitter.profile AS p WHERE p.username =:username", nativeQuery = true)
    String usernameChecker(String username);
}
