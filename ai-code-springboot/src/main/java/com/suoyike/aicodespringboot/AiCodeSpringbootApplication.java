package com.suoyike.aicodespringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.suoyike.aicodespringboot.mapper")
public class AiCodeSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCodeSpringbootApplication.class, args);
    }

}
