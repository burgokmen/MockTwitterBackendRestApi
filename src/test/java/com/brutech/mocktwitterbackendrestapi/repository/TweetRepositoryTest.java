package com.brutech.mocktwitterbackendrestapi.repository;


import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TweetRepositoryTest {
    @Autowired
    private TweetRepository tweetRepository;

    @BeforeEach
    void setUp() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        newTweet("Tweet 1", futureDate, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), Long.parseLong("2"));
    }
    @AfterEach
    void tearDown() {
        tweetRepository.deleteAll();
    }

    @Test
    void getTweetByUserId() {
        List<Tweet> tweetList = tweetRepository.getTweetByUserId(2L);
        assertEquals(1, tweetList.size());
        assertEquals("Tweet 1", tweetList.get(0).getTweetBody());
    }


    private void newTweet(String tweetBody, LocalDate futureDate, List<Long> objects, List<Long> objects2, List<Long> objects3, Long i) {
        Profile profile = new Profile();
        profile.setFirstName("TestName");
        profile.setPassword("TestPassword");
        profile.setEmail("test@test.com");

        Tweet tweet = new Tweet();
        tweet.setTweetBody(tweetBody);
        tweet.setCreatedAt(futureDate);
        tweet.setLikedByUserIdList(objects);
        tweet.setRetweetedByUserIdList(objects2);
        tweet.setCommentedByTweetIdList(objects3);
        tweet.setCommentedTweet(i);
        tweet.setProfile(profile);
        tweetRepository.save(tweet);


    }



}
