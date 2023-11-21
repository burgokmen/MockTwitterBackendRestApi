package com.brutech.mocktwitterbackendrestapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "comment", schema = "twitter")
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "tweet_id")
    private List<Tweet> tweet;

    @Column(name = "commented_by_tweet_id")
    private Long commentedByTweetId;


}
