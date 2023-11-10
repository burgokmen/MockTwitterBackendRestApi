package com.brutech.mocktwitterbackendrestapi.repository;

import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {


    @Query("SELECT t FROM Tweet t " +
            "INNER JOIN t.profile p " +
            "WHERE p.id = :id" +
            "ORDER BY t.id DESC")
    public List<Tweet> getTweetByUserId(int id);
}
