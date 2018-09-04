package com.twitter.friends.mutual.controller;

import com.twitter.friends.mutual.core.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.util.List;

@RestController
public  class TwitterController {

    @Autowired
    TwitterService twitterService;

    @GetMapping("/twitter/mutualfriends/{userName}/{friendName}")
    public List<String > getMutualFriends(@PathVariable String userName,
                                            @PathVariable String friendName){

        List<String> mutualFriends = twitterService.getFriendsList(userName,friendName);

        return mutualFriends;


    }


}