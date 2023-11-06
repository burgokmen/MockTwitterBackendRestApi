package com.brutech.mocktwitterbackendrestapi.dto;

import java.util.Date;

public record TweetResponse(long tweetId, String tweetBody, long commentsNumber, long retweetsNumber,
                            long likesNumber, String createdAt, ProfileTweetResponse profileTweetResponse){
}
