package com.brutech.mocktwitterbackendrestapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name= "tweet", schema = "twitter")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tweet_boody")
    private String tweetBoody;

    @Column(name ="comments_number")
    private int commentsNumber;

    @Column(name = "retweets_number")
    private int retweetsNumber;

    @Column(name = "likes_number")
    private int likesNumber;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Profile profile;
}
