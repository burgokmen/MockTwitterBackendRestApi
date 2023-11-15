package com.brutech.mocktwitterbackendrestapi.entity;

import com.brutech.mocktwitterbackendrestapi.exceptions.TwitterException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@NoArgsConstructor
@Data
@Table(name= "profile", schema = "twitter")
public class Profile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_handle")
    private String userHandle;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "cellular")
    private String cellular;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "location")
    private String location;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "profile_wallpaper")
    private String profileWallpaper;


    @Column(name = "liked_tweets")
    private List<Long> likedTweetIdsList;


    @Column(name = "retweeted_tweets ")
    private List<Long> retweetedTweetsIdList;

    @OneToMany(mappedBy = "profile")
    private List<Tweet> tweetsList;

    @OneToMany(mappedBy = "followedUser")
    private List<FollowToFollow> followedUserList;

    @OneToMany(mappedBy = "followerUser")
    private List<FollowToFollow> followerUserList;

    public void addTweet(Tweet tweet) {
        if(tweetsList == null) {
            tweetsList = new ArrayList<>();
        }
        tweetsList.add(tweet);
    }

    public void addLikedTweetIds(long id) {
        if (likedTweetIdsList == null) {
            likedTweetIdsList = new ArrayList<>();
        }
        if (!likedTweetIdsList.contains(id)) {
            likedTweetIdsList.add(id);
        }
        throw new TwitterException("User already liked this tweet", HttpStatus.BAD_REQUEST);
    }

    public void removeLikedTweetIds(long id) {
        if (likedTweetIdsList == null) {
            throw new TwitterException("Cannot unlike this tweet", HttpStatus.BAD_REQUEST);
        }
        if (likedTweetIdsList.contains(id)) {
            likedTweetIdsList.remove(id);
        }
        throw new TwitterException("Cannot unlike this tweet", HttpStatus.BAD_REQUEST);
    }

    public void addRetweetedTweetsIdList(long id) {
        if (retweetedTweetsIdList == null) {
            retweetedTweetsIdList = new ArrayList<>();
        }
        if (!retweetedTweetsIdList.contains(id)) {
            retweetedTweetsIdList.add(id);
        }
        throw new TwitterException("User already retweeted this tweet", HttpStatus.BAD_REQUEST);
    }

    public void removeRetweetedTweetsIdList(long id) {
        if (retweetedTweetsIdList == null) {
            throw new TwitterException("Cannot unretweet this tweet", HttpStatus.BAD_REQUEST);
        }
        if (retweetedTweetsIdList.contains(id)) {
            retweetedTweetsIdList.remove(id);
        }
        throw new TwitterException("Cannot unretweet this tweet", HttpStatus.BAD_REQUEST);
    }

    public void addFollowedUser(FollowToFollow followToFollow) {
        if(followedUserList == null) {
            followedUserList = new LinkedList<>();
        }
        followedUserList.add(followToFollow);
    }

    public void addFollowerUser(FollowToFollow followToFollow) {
        if(followerUserList == null) {
            followerUserList = new LinkedList<>();
        }
        followerUserList.add(followToFollow);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userHandle;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
