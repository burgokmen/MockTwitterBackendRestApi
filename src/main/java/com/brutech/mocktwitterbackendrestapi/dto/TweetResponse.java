package com.brutech.mocktwitterbackendrestapi.dto;

import java.util.Date;

public record TweetResponse(String tweetBody, long commentsNumber, long retweetsNumber,
                            long likesNumber, Date createdAt, ProfileTweetResponse profileTweetResponse){
}
