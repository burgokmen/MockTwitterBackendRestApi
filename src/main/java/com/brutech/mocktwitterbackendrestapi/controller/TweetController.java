package com.brutech.mocktwitterbackendrestapi.controller;

import com.brutech.mocktwitterbackendrestapi.dto.TweetResponse;
import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import com.brutech.mocktwitterbackendrestapi.service.ProfileService;
import com.brutech.mocktwitterbackendrestapi.service.TweetService;
import com.brutech.mocktwitterbackendrestapi.util.Converter;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/tweet")
@Validated
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
    public List<TweetResponse> getAllTweetsByUserId(@Positive @PathVariable Long UserId){
        return Converter.tweetResponseListConverter(tweetService.getAllTweetsByUserId(UserId));
    }

    @GetMapping("/{id}")
    public TweetResponse getTweetById(@Positive @PathVariable Long id){
        return Converter.tweetResponseConverter(tweetService.getTweetById(id));
    }

    @PostMapping("/")
    public TweetResponse saveTweet(@RequestBody Tweet tweet){
        Profile profile = profileService.getUserById(tweet.getProfile().getId());
        tweet.setCommentedTweet(Long.parseLong("0"));
        tweet.setProfile(profile);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @PutMapping("/{id}")
    public TweetResponse updateTweet(@RequestBody Tweet tweet,@Positive @PathVariable long id){
        Profile profile = profileService.getUserById(tweet.getProfile().getId());
        tweet.setProfile(profile);
        tweet.setId(id);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @DeleteMapping("/{id}")
    public TweetResponse deleteTweet(@Positive @PathVariable Long id){
        Tweet tweet = tweetService.getTweetById(id);
        tweet.setCommentedTweet(Long.parseLong("0"));
        if (!(tweet.getCommentedByTweetIdList() == null)){
            for(Long tweetId : tweet.getCommentedByTweetIdList()) {
                Tweet tweet1 = tweetService.getTweetById(tweetId);
                tweet1.setCommentedTweet(0L);
                tweetService.saveTweet(tweet1);
            };
            tweet.setCommentedByTweetIdList(new ArrayList<>());
            tweet.setCommentedTweet(Long.parseLong("0"));
            tweetService.saveTweet(tweet);
        }
        if(tweet.getCommentedTweet() != 0){
            Tweet tweet1 = tweetService.getTweetById(tweet.getCommentedTweet());
            tweet1.removeCommentedByTweetIdList(id);
        }
        return Converter.tweetResponseConverter(tweetService.deleteTweet(id));
    }

    @PostMapping("/like/{id}")
    public TweetResponse likeTweet(@Positive @PathVariable Long id, @RequestBody Profile profile){
        return processTweet(id, profile, true);
    }

    @PostMapping("/unlike/{id}")
    public TweetResponse unlikeTweet(@Positive @PathVariable Long id, @RequestBody Profile profile){
        return processTweet(id, profile, false);
    }

    private TweetResponse processTweet(Long id, Profile profile, boolean isLike){
        Tweet tweet = tweetService.getTweetById(id);
        Profile profile1 = profileService.getUserById(profile.getId());
        if(isLike){
            profile1.addLikedTweetIds(tweet.getId());
            tweet.addLikedByUserId(profile1.getId());
        }else{
            profile1.removeLikedTweetIds(tweet.getId());
            tweet.removeLikedByUserId(profile1.getId());
        }
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    /*    @PostMapping("/retweet/{id}")
        public TweetResponse retweetTweet(@PathVariable Long id, @RequestBody Profile profile){
            Tweet tweet = tweetService.getTweetById(id);
            Profile profile1 = profileService.getUserById(profile.getId());
            profile1.addRetweetsTweetsIdList(tweet.getId());
            tweet.addRetweetedByUserIdList(profile1.getId());
            return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
        }

        @PostMapping("/unretweet/{id}")
        public TweetResponse unretweetTweet(@PathVariable Long id, @RequestBody Profile profile){
            Tweet tweet = tweetService.getTweetById(id);
            Profile profile1 = profileService.getUserById(profile.getId());
            profile1.removeRetweetsTweetsIdList(tweet.getId());
            tweet.removeRetweetedByUserIdList(profile1.getId());
            return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
        }*/

    @PostMapping("/retweet/{id}")
    public TweetResponse processRetweet(@Positive @PathVariable Long id, @RequestBody Profile profile, @RequestParam boolean isRetweet){
        Tweet tweet = tweetService.getTweetById(id);
        Profile profile1 = profileService.getUserById(profile.getId());
        if(isRetweet){
            profile1.addRetweetedTweetsIdList(tweet.getId());
            tweet.addRetweetedByUserIdList(profile1.getId());
        }else{
            profile1.removeRetweetedTweetsIdList(tweet.getId());
            tweet.removeRetweetedByUserIdList(profile1.getId());
        }
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }



        @PostMapping("/comment/{id}")
        public TweetResponse commentTweet(@Positive @PathVariable Long id, @RequestBody Tweet tweet){
            Tweet commentedTweet = tweetService.getTweetById(id);
            Profile profile = profileService.getUserById(tweet.getProfile().getId());
            tweet.setProfile(profile);
            tweet.setCommentedTweet(id);
            Tweet tweet1 = tweetService.saveTweet(tweet);
            commentedTweet.addCommentedByTweetIdList(tweet1.getId());
            return Converter.tweetResponseConverter(tweetService.saveTweet(tweet1));
        }

        @DeleteMapping("/comment/{id}")
        public TweetResponse deleteCommentTweet(@Positive @PathVariable Long id){
            Tweet tweet = tweetService.getTweetById(id);
            Tweet commentedTweet = tweetService.getTweetById(tweet.getCommentedTweet());
            commentedTweet.removeCommentedByTweetIdList(id);
            return Converter.tweetResponseConverter(tweetService.deleteTweet(id));
        }


}
