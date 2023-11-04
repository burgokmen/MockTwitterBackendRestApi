package com.brutech.mocktwitterbackendrestapi.service;

import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import com.brutech.mocktwitterbackendrestapi.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new RuntimeException("Tweet not found for id :: " + id);
        }
    }

    @Override
    public Tweet saveTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet deleteTweet(Long id) {
        Tweet tweet = getTweetById(id);
        tweetRepository.delete(tweet);
        return tweet;
    }
}
