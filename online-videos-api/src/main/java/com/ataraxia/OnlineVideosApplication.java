package com.ataraxia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author Ataraxia
 * @create 2022/4/20 19:53
 * @description 项目启动类
 */

@SpringBootApplication
public class OnlineVideosApplication {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(OnlineVideosApplication.class, args);
        System.out.println("<========== 启动成功(～￣▽￣)～ ==========>");
    }
}
