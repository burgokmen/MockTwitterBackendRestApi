package com.brutech.mocktwitterbackendrestapi.repository;

import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {


    @Query(value = "SELECT * FROM tweet t " +
            "INNER JOIN profile p ON t.user_id = p.id " +
            "WHERE p.id = :id " +
            "ORDER BY t.id DESC", nativeQuery = true)
    List<Tweet> getTweetByUserId(Long id);

    @Query(value = "SELECT * FROM tweet t " +
            "INNER JOIN profile p ON t.user_id = p.id " +
            "WHERE p.user_handle = :userHandle " +
            "ORDER BY t.id DESC", nativeQuery = true)
    List<Tweet> getAllTweetsByUserHandle(String userHandle);
}
