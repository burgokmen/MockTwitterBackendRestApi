package com.brutech.mocktwitterbackendrestapi.entity;

import com.brutech.mocktwitterbackendrestapi.exceptions.TwitterException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must be at least 2 characters")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Size(min = 5, message = "Email must be at least 5 characters")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters")
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

    @ElementCollection
    @Column(name = "liked_tweets")
    private List<Long> likedTweetIdsList;

    @ElementCollection
    @Column(name = "retweeted_tweets ")
    private List<Long> retweetedTweetsIdList;

    @OneToMany(mappedBy = "profile")
    private List<Tweet> tweetsList;

/*    @ManyToMany
    @JoinTable(
            name = "follow_to_follow",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<Profile> followedUserList;

    @ManyToMany
    @JoinTable(
            name = "follow_to_follow",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id"))
    private List<Profile> followerUserList;*/

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

 /*   public void addFollowedUser(Profile profile) {
        if(followedUserList == null) {
            followedUserList = new LinkedList<>();
        }
        followedUserList.add(profile);
    }

    public void addFollowerUser(Profile profile) {
        if(followerUserList == null) {
            followerUserList = new LinkedList<>();
        }
        followerUserList.add(profile);
    }

    public void removeFollowedUser(Profile profile) {
        if(followedUserList == null) {
            throw new TwitterException("Cannot unfollow this user", HttpStatus.BAD_REQUEST);
        }
        followedUserList.remove(profile);
    }

    public void removeFollowerUser(Profile profile) {
        if(followerUserList == null) {
            throw new TwitterException("Cannot unfollow this user", HttpStatus.BAD_REQUEST);
        }
        followerUserList.remove(profile);
    }*/

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
