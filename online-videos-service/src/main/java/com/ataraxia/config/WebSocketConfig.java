package com.ataraxia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Ataraxia
 * @create 2022/4/25 15:55
 * @description WebSocket配置类
 */
@Configuration
public class WebSocketConfig {

    /**
     * 用来发现 WebSocket服务
     * @return ServerEndpointExporter
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
