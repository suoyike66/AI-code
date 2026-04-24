package com.suoyike.aicodespringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * WebFlux 配置
 * 用于支持 SSE 流式响应
 */
@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        ServerCodecConfigurer configurer = ServerCodecConfigurer.create();
        // 设置较大的内存限制,避免大数据流被截断
        configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024); // 16MB
        return configurer;
    }
}
