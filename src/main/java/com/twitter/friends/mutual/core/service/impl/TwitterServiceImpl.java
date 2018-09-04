package com.twitter.friends.mutual.core.service.impl;

import com.twitter.friends.mutual.core.service.AuthenticationService;
import com.twitter.friends.mutual.core.service.TwitterService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.twitter.friends.mutual.util.TwitterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.auth.AccessToken;

@Service
public class TwitterServiceImpl implements TwitterService {

    @Autowired
    AuthenticationService authenticationService;

    /**
     * Method returns the list of mutual friends for the given usernames
     *
     * Method calls the getFriendsList and returns the list of friends for the particular user ,
     * once the list is available we are adding the username / count as key value pair to a hashmap.
     * Hashmap initially stores all the key's value as 1 , if a second occurrence for the same username is
     * found in the second user's friend list , increment the count to 2
     * Final list of friends which are mutual are found by filtering only the entries
     * with value as 2
     * @param screenName : Screen name of the first user
     * @param handleName : Screen name of the second user
     * @return : Returns the list of mutual friends
     */
    public List<String> getFriendsList(String screenName, String handleName){

        Twitter twitter = authenticationService.getTwitterAuthObject();
        try {

            //initializing cursor to -1
            long cursor = -1;

            //initializing response object
            PagableResponseList<User> response;

            ResponseList<UserList> firstUser = twitter.getUserLists(screenName);
            if(firstUser.size() > 0){

                System.out.println ("Invalid first account");
            }

            ResponseList<UserList> secondUser = twitter.getUserLists(handleName);

            if(secondUser.size() > 0){

                System.out.println ( "Invalid second account");
            }

            //map to store friends list with key as no of occurrence
            Map<String,Integer> friendsMap = new HashMap<>();

            do {

                //get the friend list of user A
                response = twitter.getFriendsList(screenName, cursor, 100);

                //iterate through the response and add entry to the hashmap of friends and occurrence
                response.forEach(user -> friendsMap.put(user.getScreenName(),1));


            } while ((cursor = response.getNextCursor()) != 0);

            //reset the cursor
            cursor = -1;

            do {

                //get the friends list of second user
                response = twitter.getFriendsList(handleName, cursor, 100);

                //iterate through the response
                response.forEach(user -> {

                    //initialize value of count to 1
                    int count =1;

                    //if the friend name already exists , set value as 2
                    if(friendsMap.get(user.getScreenName()) != null){

                        count = 2 ;
                    }

                    //add the entry to the friends map
                    friendsMap.put(user.getScreenName(),count);

                });


            } while ((cursor = response.getNextCursor()) != 0);

            //filter the map and get the list of friends with count as 2
            List<String> mutualFriendsList = friendsMap.keySet().stream().filter(userNameKey -> friendsMap.get(userNameKey) == 2).collect(Collectors.toList());

            mutualFriendsList.forEach(username -> System.out.println ( username));

            //return the response
            return mutualFriendsList;


    } catch (TwitterException e) {
            e.printStackTrace();

            return new ArrayList<String>(0);

        }

    }



    public static void main(String args[]){

            new TwitterServiceImpl().getFriendsList("snsh","ss");
    }
}