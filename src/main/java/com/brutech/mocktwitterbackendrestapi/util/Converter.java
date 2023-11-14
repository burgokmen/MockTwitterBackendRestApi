package com.brutech.mocktwitterbackendrestapi.util;

import com.brutech.mocktwitterbackendrestapi.dto.ProfileResponse;
import com.brutech.mocktwitterbackendrestapi.dto.ProfileTweetResponse;
import com.brutech.mocktwitterbackendrestapi.dto.TweetResponse;
import com.brutech.mocktwitterbackendrestapi.entity.Profile;
import com.brutech.mocktwitterbackendrestapi.entity.Tweet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Converter {
    public static ProfileResponse profileResponseConverter(Profile profile){
        return new ProfileResponse(profile.getId(), profile.getUserHandle(), profile.getFirstName(),
                profile.getLastName(), profile.getEmail(), profile.getPassword(),
                profile.getCellular(), formatDate(profile.getBirthday()),
                formatDate(profile.getCreatedAt()), profile.getLocation(),
                profile.getProfilePicture());
    }



    public static List<ProfileResponse> profileResponseListConverter(List<Profile> profileList){
        return profileList.stream().map(Converter::profileResponseConverter).toList();
    }

    public static ProfileTweetResponse profileTweetResponseConverter(Profile profile){
        return new ProfileTweetResponse(profile.getId(), profile.getFirstName(),
                profile.getLastName(), profile.getProfilePicture(), profile.getUserHandle());
    }

    public static List<ProfileTweetResponse> profileTweetResponseListConverter(List<Profile> profileList){
        return profileList.stream().map(Converter::profileTweetResponseConverter).toList();
    }

    public static TweetResponse tweetResponseConverter(Tweet tweet){
        return new TweetResponse(tweet.getId(), tweet.getTweetBody(),
                formatDate(tweet.getCreatedAt()), profileTweetResponseConverter(tweet.getProfile()),
                tweet.getLikedByUserIdList(), tweet.getCommentedByTweetIdList(),
                tweet.getRetweetedByUserIdList(), tweet.getCommentedTweet());
    }

    public static List<TweetResponse> tweetResponseListConverter(List<Tweet> tweetList){
        return tweetList.stream().map(Converter::tweetResponseConverter).toList();
    }

    public static String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }
}
