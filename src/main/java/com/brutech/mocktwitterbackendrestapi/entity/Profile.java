package com.brutech.mocktwitterbackendrestapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "cellular")
    private String cellular;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "profile")
    private List<Tweet> tweetsList;

    @OneToMany(mappedBy = "followedUser")
    private Set<FollowToFollow> followedUserList;

    @OneToMany(mappedBy = "followerUser")
    private Set<FollowToFollow> followerUserList;

    public void addTweet(Tweet tweet) {
        if(tweetsList == null) {
            tweetsList = new ArrayList<>();
        }
        tweetsList.add(tweet);
    }

    public void addFollowedUser(FollowToFollow followToFollow) {
        if(followedUserList == null) {
            followedUserList = new HashSet<>();
        }
        followedUserList.add(followToFollow);
    }

    public void addFollowerUser(FollowToFollow followToFollow) {
        if(followerUserList == null) {
            followerUserList = new HashSet<>();
        }
        followerUserList.add(followToFollow);
    }
}
