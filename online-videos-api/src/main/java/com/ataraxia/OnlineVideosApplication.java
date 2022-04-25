package com.ataraxia;

import com.ataraxia.websocket.WebSocketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ataraxia
 * @create 2022/4/20 19:53
 * @description 项目启动类
 */

@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
public class OnlineVideosApplication {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(OnlineVideosApplication.class, args);
        WebSocketService.setApplicationContext(app);
        System.out.println("<========== 启动成功(～￣▽￣)～ ==========>");
    }
}
