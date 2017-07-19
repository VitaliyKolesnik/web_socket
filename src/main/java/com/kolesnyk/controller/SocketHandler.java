package com.kolesnyk.controller;


import com.kolesnyk.model.entity.ChatMessageModel;
import com.kolesnyk.model.service.ChatMessageModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;
import java.util.List;

@Controller
public class SocketHandler extends TextWebSocketHandler {

    @Autowired
    public ChatMessageModelService modelService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
        TextMessage payload = new TextMessage("");
        modelService.save(new ChatMessageModel(message.getPayload(), new Date()));
        List<String> messagesList = modelService.getAll();
        for (String string : messagesList) {
            payload = new TextMessage(payload.getPayload() + "\n" + string);
        }
        session.sendMessage(payload);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Fuck yeah");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        exception.printStackTrace();
    }


}
