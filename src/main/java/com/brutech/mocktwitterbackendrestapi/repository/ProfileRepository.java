package com.brutech.mocktwitterbackendrestapi.repository;

import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT p.email FROM twitter.profile " +
            "AS p WHERE p.email =:email", nativeQuery = true)
    String emailChecker(String email);

    @Query(value = "SELECT p.cellular FROM twitter.profile " +
            "AS p WHERE p.cellular =:cellular", nativeQuery = true)
    String phoneChecker(String cellular);

    @Query(value = "SELECT p.user_handle FROM twitter.profile " +
            "AS p WHERE p.user_handle =:userHandle", nativeQuery = true)
    String usernameChecker(String userHandle);



    @Query("SELECT p FROM Profile p WHERE p.email =:email")
    Optional<Profile> getUserByEmail(@Param("email") String email);
}
