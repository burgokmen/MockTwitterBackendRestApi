package com.brutech.mocktwitterbackendrestapi.repository;

import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
