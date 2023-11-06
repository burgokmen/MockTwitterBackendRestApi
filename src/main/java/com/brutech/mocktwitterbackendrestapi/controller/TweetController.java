package com.brutech.mocktwitterbackendrestapi.controller;

import com.brutech.mocktwitterbackendrestapi.dto.TweetResponse;
import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import com.brutech.mocktwitterbackendrestapi.service.ProfileService;
import com.brutech.mocktwitterbackendrestapi.service.TweetService;
import com.brutech.mocktwitterbackendrestapi.util.Converter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tweet")
public class TweetController {
    private TweetService tweetService;
    private ProfileService profileService;
    @Autowired
    public TweetController(TweetService tweetService, ProfileService profileService) {
        this.tweetService = tweetService;
        this.profileService = profileService;
    }


    @GetMapping("/")
    public List<TweetResponse> getAllTweets(){
        return Converter.tweetResponseListConverter(tweetService.getAllTweets());
    }

    @GetMapping("/profile/{UserId}")
    public List<TweetResponse> getAllTweetsByUserId(@PathVariable Long UserId){
        return Converter.tweetResponseListConverter(tweetService.getAllTweetsByUserId(UserId));
    }

    @GetMapping("/{id}")
    public TweetResponse getTweetById(@PathVariable Long id){
        return Converter.tweetResponseConverter(tweetService.getTweetById(id));
    }

    @PostMapping("/")
    public TweetResponse saveTweet(@RequestBody Tweet tweet){
        Profile profile = profileService.getUserById(tweet.getProfile().getId());
        tweet.setProfile(profile);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @PutMapping("/{id}")
    public TweetResponse updateTweet(@RequestBody Tweet tweet, @PathVariable long id){
        Profile profile = profileService.getUserById(tweet.getProfile().getId());
        tweet.setProfile(profile);
        tweet.setId(id);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @DeleteMapping("/{id}")
    public TweetResponse deleteTweet(@PathVariable Long id){
        return Converter.tweetResponseConverter(tweetService.deleteTweet(id));
    }


}
