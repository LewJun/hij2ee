package com.lewjun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;

// ws://localhost:9090/websocket1 依赖websocket-api
@Component
@ServerEndpoint("/websocket1")
public class WsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WsController.class);

    @OnOpen
    public void onOpen(Session session) {
        LOGGER.info("onOpen {}", session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.info("onClose {}", session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOGGER.info("onError {}, {}", session.getId(), throwable.getMessage());
    }

    @OnMessage
    public void onMessage(byte[] msg, Session session) throws IOException {
        LOGGER.info("sessionId: {}", session.getId());
        LOGGER.info("onMessage byte[]:{}", msg.length);
        session.getBasicRemote().sendText("hello byte[] " + new Date());
    }

    @OnMessage
    public void onMessage(String msg, Session session) throws IOException {
        LOGGER.info("sessionId: {}", session.getId());
        LOGGER.info("onMessage String:{}", msg);
        session.getBasicRemote().sendText("hello " + msg + " " + new Date());
    }
}
