package com.lewjun.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * 握手接口拦截器
 */
@Component
public class MyHttpSessionHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyHttpSessionHandshakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        LOGGER.info("beforeHandshake attrs:{}", attributes);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        LOGGER.info("afterHandshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
