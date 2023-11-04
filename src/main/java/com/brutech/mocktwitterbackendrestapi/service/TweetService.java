package com.brutech.mocktwitterbackendrestapi.service;

import com.brutech.mocktwitterbackendrestapi.entity.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> getAllTweets();
    Tweet getTweetById(Long id);
    Tweet saveTweet(Tweet tweet);
    Tweet deleteTweet(Long id);
}
