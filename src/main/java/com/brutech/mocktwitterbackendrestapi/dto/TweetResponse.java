package com.brutech.mocktwitterbackendrestapi.dto;


import java.util.List;

public record TweetResponse(long tweetId, String tweetBody,
                            String createdAt, ProfileTweetResponse profileTweetResponse,
                            List<Long> likedByUserIdList, List<Long> commentedByTweetIdList,
                            List<Long> retweetedByUserIdList, Long commentedTweet){
}
