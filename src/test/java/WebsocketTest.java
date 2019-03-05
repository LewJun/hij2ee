import com.lewjun.handler.MyWebSocketHandler;
import org.junit.Test;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class WebsocketTest {
    @Test
    public void connectTest2() {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketHandler webSocketHandler = new MyWebSocketHandler();
        String uriTemplate = "ws://127.0.0.1:9090/websocket";
        UriComponentsBuilder fromUriString = UriComponentsBuilder.fromUriString(uriTemplate);
        fromUriString.queryParam("account", "111111");
        /*
         * 作用同上，都是将请求参数填入到URI中
         * MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         * params.add("account","111111");
         * fromUriString.queryParams(params);
         * */
        URI uri = fromUriString.buildAndExpand().encode().toUri();
        WebSocketHttpHeaders headers = null;
        ListenableFuture<WebSocketSession> doHandshake = client.doHandshake(webSocketHandler, headers, uri);
        try {
            WebSocketSession session = doHandshake.get();
            session.sendMessage(new TextMessage("hello world"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
