package com.twitter.friends.mutual.core.service;

import org.springframework.stereotype.Service;
import twitter4j.Twitter;

import java.util.List;

public interface AuthenticationService {

    public Twitter getTwitterAuthObject();
}
