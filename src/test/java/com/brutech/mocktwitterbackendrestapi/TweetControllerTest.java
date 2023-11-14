package com.brutech.mocktwitterbackendrestapi;

import com.brutech.mocktwitterbackendrestapi.controller.TweetController;
import com.brutech.mocktwitterbackendrestapi.dto.TweetResponse;
import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.entity.Tweet;
import com.brutech.mocktwitterbackendrestapi.service.ProfileService;
import com.brutech.mocktwitterbackendrestapi.service.TweetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TweetControllerTest {

    @InjectMocks
    private TweetController tweetController;

    @Mock
    private TweetService tweetService;

    @Mock
    private ProfileService profileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTweetsReturnsExpectedTweets() {
        Tweet tweet1 = new Tweet();
        Tweet tweet2 = new Tweet();
        when(tweetService.getAllTweets()).thenReturn(Arrays.asList(tweet1, tweet2));

        List<TweetResponse> tweets = tweetController.getAllTweets();

        assertEquals(2, tweets.size());
        verify(tweetService, times(1)).getAllTweets();
    }

    @Test
    public void getTweetByIdReturnsExpectedTweet() {
        Tweet tweet = new Tweet();
        when(tweetService.getTweetById(1L)).thenReturn(tweet);

        TweetResponse tweetResponse = tweetController.getTweetById(1L);

        assertEquals(tweet, tweetResponse);
        verify(tweetService, times(1)).getTweetById(1L);
    }

    @Test
    public void saveTweetSavesAndReturnsExpectedTweet() {
        Tweet tweet = new Tweet();
        Profile profile = new Profile();
        tweet.setProfile(profile);
        when(profileService.getUserById(anyLong())).thenReturn(profile);
        when(tweetService.saveTweet(any(Tweet.class))).thenReturn(tweet);

        TweetResponse tweetResponse = tweetController.saveTweet(tweet);

        assertEquals(tweet, tweetResponse);
        verify(tweetService, times(1)).saveTweet(tweet);
    }

    @Test
    public void updateTweetUpdatesAndReturnsExpectedTweet() {
        Tweet tweet = new Tweet();
        Profile profile = new Profile();
        tweet.setProfile(profile);
        when(profileService.getUserById(anyLong())).thenReturn(profile);
        when(tweetService.saveTweet(any(Tweet.class))).thenReturn(tweet);

        TweetResponse tweetResponse = tweetController.updateTweet(tweet, 1L);

        assertEquals(tweet, tweetResponse);
        verify(tweetService, times(1)).saveTweet(tweet);
    }

    @Test
    public void deleteTweetDeletesAndReturnsExpectedTweet() {
        Tweet tweet = new Tweet();
        when(tweetService.getTweetById(1L)).thenReturn(tweet);
        when(tweetService.deleteTweet(1L)).thenReturn(tweet);

        TweetResponse tweetResponse = tweetController.deleteTweet(1L);

        assertEquals(tweet, tweetResponse);
        verify(tweetService, times(1)).deleteTweet(1L);
    }

    @Test
    public void likeTweetLikesAndReturnsExpectedTweet() {
        Tweet tweet = new Tweet();
        Profile profile = new Profile();
        when(tweetService.getTweetById(1L)).thenReturn(tweet);
        when(profileService.getUserById(anyLong())).thenReturn(profile);
        when(tweetService.saveTweet(any(Tweet.class))).thenReturn(tweet);

        TweetResponse tweetResponse = tweetController.likeTweet(1L, profile);

        assertEquals(tweet, tweetResponse);
        verify(tweetService, times(1)).saveTweet(tweet);
    }

    @Test
    public void unlikeTweetUnlikesAndReturnsExpectedTweet() {
        Tweet tweet = new Tweet();
        Profile profile = new Profile();
        when(tweetService.getTweetById(1L)).thenReturn(tweet);
        when(profileService.getUserById(anyLong())).thenReturn(profile);
        when(tweetService.saveTweet(any(Tweet.class))).thenReturn(tweet);

        TweetResponse tweetResponse = tweetController.unlikeTweet(1L, profile);

        assertEquals(tweet, tweetResponse);
        verify(tweetService, times(1)).saveTweet(tweet);
    }
}
