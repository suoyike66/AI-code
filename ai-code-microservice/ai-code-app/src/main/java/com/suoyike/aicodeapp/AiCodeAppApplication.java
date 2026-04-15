package com.suoyike.aicodeapp;

import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {RedisEmbeddingStoreAutoConfiguration.class})
@MapperScan("com.suoyike.aicodeapp.mapper")
@EnableDubbo
@EnableCaching
@ComponentScan("com.suoyike")
public class AiCodeAppApplication {
    public static void main(String[] args) {

        SpringApplication.run(AiCodeAppApplication.class, args);
    }
}