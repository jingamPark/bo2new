package com.example.bo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Bo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Bo2Application.class, args);
    }

}
