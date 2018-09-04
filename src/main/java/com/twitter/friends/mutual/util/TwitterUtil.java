package com.twitter.friends.mutual.util;

import com.twitter.friends.mutual.core.service.TwitterService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.auth.AccessToken;

public  class TwitterUtil  {

    @Value("${twitter.consumer.key}")
    static String twitterConsumerkey;

    @Value("${twitter.consumer.secret}")
    static String twitterConsumerSecret;

    @Value("${twitter.token.key}")
    static String twitterTokenKey;

    @Value("${twitter.token.secret}")
    static String twitterTokenSecret;

    public static Twitter getTwitterAuthObject(){

        Twitter twitter = new TwitterFactory().getInstance();
        // Twitter Consumer key & Consumer Secret
        twitter.setOAuthConsumer(twitterConsumerkey, twitterConsumerSecret);
        // Twitter Access token & Access token Secret
        twitter.setOAuthAccessToken(new AccessToken(twitterTokenKey,
                twitterTokenSecret));

        return twitter;
    }

}