package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketChatServer.class);

    private static void sendMessageToAll(String msg) {
        onlineSessions.forEach((s, session) -> {
            try {
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(msg);
                } else {
                    onlineSessions.remove(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineSessions.put(session.getId(), session);
        Message message = new Message();
        message.setType(Message.MessageType.ENTER);
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        Message message = JSON.parseObject(jsonStr, Message.class);
        message.setType(Message.MessageType.SPEAK);
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        session.close();
        onlineSessions.remove(session.getId());
        Message message = new Message();
        message.setType(Message.MessageType.LEAVE);
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(JSON.toJSONString(message));
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
