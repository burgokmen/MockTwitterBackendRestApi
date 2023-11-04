package com.brutech.mocktwitterbackendrestapi.repository;

import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
