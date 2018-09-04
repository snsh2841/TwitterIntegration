package com.twitter.friends.mutual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MutualApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutualApplication.class, args);
    }
}
