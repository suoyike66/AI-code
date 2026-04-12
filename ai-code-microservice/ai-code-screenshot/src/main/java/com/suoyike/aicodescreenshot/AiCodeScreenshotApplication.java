package com.suoyike.aicodescreenshot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class AiCodeScreenshotApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiCodeScreenshotApplication.class, args);
    }
}