package com.twitter.friends.mutual.core.service.impl;

import com.twitter.friends.mutual.core.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Service
public  class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${twitter.consumer.key}")
    String twitterConsumerkey;

    @Value("${twitter.consumer.secret}")
    String twitterConsumerSecret;

    @Value("${twitter.token.key}")
    String twitterTokenKey;

    @Value("${twitter.token.secret}")
    String twitterTokenSecret;

    public Twitter getTwitterAuthObject(){

        Twitter twitter = new TwitterFactory().getInstance();
        // Twitter Consumer key & Consumer Secret
        twitter.setOAuthConsumer(twitterConsumerkey, twitterConsumerSecret);
        // Twitter Access token & Access token Secret
        twitter.setOAuthAccessToken(new AccessToken(twitterTokenKey,
                twitterTokenSecret));

        return twitter;
    }

}