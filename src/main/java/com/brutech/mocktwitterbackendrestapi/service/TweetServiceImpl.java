package com.brutech.mocktwitterbackendrestapi.service;

import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import com.brutech.mocktwitterbackendrestapi.exceptions.TwitterException;
import com.brutech.mocktwitterbackendrestapi.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TweetServiceImpl implements TweetService{
    private TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet getTweetById(Long id) {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        if (tweetOptional.isPresent()){
            return tweetOptional.get();
        }
        else {
            throw new TwitterException("Tweet not found for id :: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Tweet saveTweet(Tweet tweet) {
        if(tweet.getTweetBody()== null || tweet.getTweetBody().isEmpty()){
            throw new TwitterException("Tweet body cannot be empty", HttpStatus.BAD_REQUEST);
        }
        Profile profile = tweet.getProfile();
        profile.addTweet(tweet);
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet deleteTweet(Long id) {
        Tweet tweet = getTweetById(id);
        tweetRepository.delete(tweet);
        return tweet;
    }

    @Override
    public List<Tweet> getAllTweetsByUserId(Long id) {
        List<Tweet> tweetList = tweetRepository.getTweetByUserId(id);
        if (!tweetList.isEmpty()) {
            return tweetList;
        } else {
            throw new TwitterException("Tweet not found for user id :: " + id, HttpStatus.NOT_FOUND);

        }

    }

    @Override
    public List<Tweet> getAllTweetsByUserHandle(String userHandle) {
        List<Tweet> tweetList = tweetRepository.getAllTweetsByUserHandle(userHandle);
        if (!tweetList.isEmpty()) {
            return tweetList;
        } else {
            throw new TwitterException("Tweet not found for user handle :: " + userHandle, HttpStatus.NOT_FOUND);

        }

    }


}
