package com.brutech.mocktwitterbackendrestapi.entity;

import com.brutech.mocktwitterbackendrestapi.exceptions.TwitterException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@NoArgsConstructor
@Data
@Table(name= "tweet", schema = "twitter")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tweet_body")
        private String tweetBody;


    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Profile profile;


    @Column(name= "liked_by_user_id")
    private List<Long> likedByUserIdList;


    @Column(name= "retweeted_by_user_id")
    private List<Long> retweetedByUserIdList;


    @Column(name= "commented_by_tweet_id")
    private List<Long> commentedByTweetIdList;

    @Column(name = "commented_tweet")
    private Long commentedTweet;

    public void addLikedByUserId(Long userId){
        if(likedByUserIdList == null){
            likedByUserIdList = new ArrayList<>();
        }
       if (!likedByUserIdList.contains(userId)){
           likedByUserIdList.add(userId);
       }
       throw new TwitterException("User already liked this tweet", HttpStatus.BAD_REQUEST);
    }

    public void removeLikedByUserId(Long userId){
        if(likedByUserIdList == null){
            throw new TwitterException("Cannot unlike this tweet", HttpStatus.BAD_REQUEST);
        }
        if (likedByUserIdList.contains(userId)){
            likedByUserIdList.stream()
                    .filter(id -> !id.equals(userId)).collect(Collectors.toList());
        }
        throw new TwitterException("Cannot unlike this tweet", HttpStatus.BAD_REQUEST);
    }

    public void addRetweetedByUserIdList(Long userId){
        if(retweetedByUserIdList == null){
            retweetedByUserIdList = new ArrayList<>();
        }
        if (!retweetedByUserIdList.contains(userId)){
            retweetedByUserIdList.add(userId);
        }
        throw new TwitterException("User already retweeted this tweet", HttpStatus.BAD_REQUEST);
    }

    public void removeRetweetedByUserIdList(Long userId){
        if(retweetedByUserIdList == null){
            throw new TwitterException("Cannot unretweet this tweet", HttpStatus.BAD_REQUEST);
        }
        if (retweetedByUserIdList.contains(userId)){
            retweetedByUserIdList.stream()
                    .filter(id -> !id.equals(userId)).collect(Collectors.toList());
        }
        throw new TwitterException("Cannot unretweet this tweet", HttpStatus.BAD_REQUEST);
    }

    public void addCommentedByTweetIdList(Long tweetId){
        if(commentedByTweetIdList == null){
            commentedByTweetIdList = new ArrayList<>();
        }
        if (!commentedByTweetIdList.contains(tweetId)){
            commentedByTweetIdList.add(tweetId);
        }
        throw new TwitterException("User already commented this tweet", HttpStatus.BAD_REQUEST);
    }

    public void removeCommentedByTweetIdList(Long tweetId){
        if(commentedByTweetIdList == null){
            throw new TwitterException("Cannot uncomment this tweet", HttpStatus.BAD_REQUEST);
        }
        if (commentedByTweetIdList.contains(tweetId)){
            commentedByTweetIdList.stream()
                    .filter(id -> !id.equals(tweetId)).collect(Collectors.toList());
        }
        throw new TwitterException("Cannot uncomment this tweet", HttpStatus.BAD_REQUEST);
    }





}
