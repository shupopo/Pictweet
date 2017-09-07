package com.example.business.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String text;
    
    //追加箇所
    @ManyToOne
    private Tweet tweet;

    //追加箇所
    @ManyToOne
    private User tsukaite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    //userプロパティのゲッターとセッターを追加
    public User getUser() {
        return tsukaite;
    }

    public void setUser(User user) {
        this.tsukaite = user;
    }

}