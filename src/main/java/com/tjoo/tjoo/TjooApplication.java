package com.tjoo.tjoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class TjooApplication {

    public static void main(String[] args) {
        SpringApplication.run(TjooApplication.class, args);
    }

}
