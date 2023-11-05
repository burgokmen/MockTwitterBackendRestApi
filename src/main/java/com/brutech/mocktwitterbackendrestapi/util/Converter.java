package com.brutech.mocktwitterbackendrestapi.util;

import com.brutech.mocktwitterbackendrestapi.dto.ProfileResponse;
import com.brutech.mocktwitterbackendrestapi.dto.ProfileTweetResponse;
import com.brutech.mocktwitterbackendrestapi.dto.TweetResponse;
import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.entity.Tweet;

import java.util.List;

public class Converter {
    public static ProfileResponse profileResponseConverter(Profile profile){
        return new ProfileResponse(profile.getUserHandle(), profile.getFirstName(), profile.getLastName(),
                profile.getEmail(), profile.getPassword(), profile.getCellular(), profile.getBirthday(),
                profile.getCreatedAt(), profile.getLocation(), profile.getProfilePicture());
    }

    public static List<ProfileResponse> profileResponseListConverter(List<Profile> profileList){
        return profileList.stream().map(Converter::profileResponseConverter).toList();
    }

    public static ProfileTweetResponse profileTweetResponseConverter(Profile profile){
        return new ProfileTweetResponse(profile.getFirstName(), profile.getLastName(), profile.getProfilePicture(),
                profile.getUserHandle());
    }

    public static List<ProfileTweetResponse> profileTweetResponseListConverter(List<Profile> profileList){
        return profileList.stream().map(Converter::profileTweetResponseConverter).toList();
    }

    public static TweetResponse tweetResponseConverter(Tweet tweet){
        return new TweetResponse(tweet.getTweetBody(), tweet.getCommentsNumber(), tweet.getRetweetsNumber(),
                tweet.getLikesNumber(), tweet.getCreatedAt(), profileTweetResponseConverter(tweet.getProfile()));
    }

    public static List<TweetResponse> tweetResponseListConverter(List<Tweet> tweetList){
        return tweetList.stream().map(Converter::tweetResponseConverter).toList();
    }
}
