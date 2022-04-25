package com.ataraxia.websocket;

import com.alibaba.fastjson.JSONObject;
import com.ataraxia.domain.BarrageDO;
import com.ataraxia.service.BarrageService;
import com.ataraxia.service.impl.BarrageServiceImpl;
import com.ataraxia.util.TokenUtil;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ataraxia
 * @create 2022/4/25 15:57
 * @description WebSocket服务
 */
@Component
@ServerEndpoint("/imserver/{token}")
public class WebSocketService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 长连接人数：在线人数
     * AtomicInteger：原子性操作保证线程安全
     */
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    private static final ConcurrentHashMap<String, WebSocketService> WEBSOCKET_MAP = new ConcurrentHashMap<>();

    private Session session;

    private String sessionId;

    private Long userId;

    private static ApplicationContext APPLICATION_CONTEXT;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketService.APPLICATION_CONTEXT = applicationContext;
    }

    @OnOpen
    public void openConnection(Session session, @PathParam("token") String token) {
        try {
            this.userId = TokenUtil.verifyToken(token);
        } catch (Exception ignored){}
        this.sessionId = session.getId();
        this.session = session;
        // 是否有这个sessionId
        if (WEBSOCKET_MAP.containsKey(sessionId)) {
            WEBSOCKET_MAP.remove(sessionId);
            WEBSOCKET_MAP.put(sessionId, this);
        } else {
            WEBSOCKET_MAP.put(sessionId, this);
            ONLINE_COUNT.getAndIncrement();
        }
        logger.info("用户连接成功：" + sessionId + "，当前在线人数为：" + ONLINE_COUNT.get());
        try {
            this.sendMessage("0");
        } catch (Exception e) {
            logger.error("连接异常！");
        }
    }

    @OnClose
    public void closeConnection() {
        if (WEBSOCKET_MAP.containsKey(sessionId)) {
            WEBSOCKET_MAP.remove(sessionId);
            ONLINE_COUNT.getAndDecrement();
        }
        logger.info("用户退出：" + sessionId + "，当前在线人数为：" + ONLINE_COUNT.get());
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("用户信息：" + sessionId + "，报文：" + message);
        if (!StringUtils.isNullOrEmpty(message)) {
            try {
                // 群发消息
                for (Map.Entry<String, WebSocketService> entry : WEBSOCKET_MAP.entrySet()) {
                    WebSocketService webSocketService = entry.getValue();

                    if (webSocketService.session.isOpen()) {
                        webSocketService.sendMessage(message);
                    }
                }
                if (Objects.nonNull(this.userId)) {
                    BarrageDO barrage = JSONObject.parseObject(message, BarrageDO.class);
                    barrage.setUserId(userId);
                    barrage.setCreateTime(new Date());
                    BarrageService barrageService = APPLICATION_CONTEXT.getBean(BarrageServiceImpl.class);
                    barrageService.saveBarrage(barrage);
                    // 保存弹幕到redis
                    barrageService.saveBarrageToRedis(barrage);
                }

            } catch (Exception e) {
                logger.error("弹幕接收出现问题！");
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Throwable error) {

    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
