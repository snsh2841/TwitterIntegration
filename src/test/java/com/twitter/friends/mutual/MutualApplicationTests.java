package com.twitter.friends.mutual;

import com.twitter.friends.mutual.core.service.TwitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutualApplicationTests {

    @Autowired
    TwitterService twitterService;
    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){

        twitterService.getFriendsList("snsh2841","vidhun23");

    }
}
