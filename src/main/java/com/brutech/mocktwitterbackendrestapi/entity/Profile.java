package com.brutech.mocktwitterbackendrestapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Entity
@NoArgsConstructor
@Data
@Table(name= "profile", schema = "twitter")
public class Profile {
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

    @Column(name = "profile_walpaper")
    private String profileWalpaper;

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
}
