package com.example.wooahhae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WooahhaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WooahhaeApplication.class, args);
    }

}
