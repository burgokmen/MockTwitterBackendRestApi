package com.brutech.mocktwitterbackendrestapi.controller;

import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import com.brutech.mocktwitterbackendrestapi.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {
    private TweetService tweetService;
    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/")
    public List<Tweet> getAllTweets(){
        return tweetService.getAllTweets();
    }

    @GetMapping("/{id}")
    public Tweet getTweetById(@PathVariable Long id){
        return tweetService.getTweetById(id);
    }

    @PostMapping("/")
    public Tweet saveTweet(@RequestBody Tweet tweet){
        return tweetService.saveTweet(tweet);
    }

    @PutMapping("/{id}")
    public Tweet updateTweet(@PathVariable Long id,@RequestBody Tweet tweet){
        return tweetService.saveTweet(tweet);
    }

    @DeleteMapping("/{id}")
    public Tweet deleteTweet(@PathVariable Long id){
        return tweetService.deleteTweet(id);
    }



}
