package com.example.ourdevboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OurDevBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurDevBoardApplication.class, args);
    }

}
