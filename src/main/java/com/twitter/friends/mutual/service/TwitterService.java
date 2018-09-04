package com.twitter.friends.mutual.core.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface TwitterService {

    public List<String> getFriendsList(String screenName, String handleName);
}
