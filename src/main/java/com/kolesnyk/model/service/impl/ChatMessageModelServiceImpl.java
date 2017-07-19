package com.kolesnyk.model.service.impl;


import com.kolesnyk.model.dao.ChatMessageModelDAO;
import com.kolesnyk.model.entity.ChatMessageModel;
import com.kolesnyk.model.service.ChatMessageModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatMessageModelServiceImpl implements ChatMessageModelService {

    @Autowired
    private ChatMessageModelDAO chatMessageModelDAO;

    private List<String> messages;

    public ChatMessageModelServiceImpl(ChatMessageModelDAO chatMessageModelDAO) {
        this.chatMessageModelDAO = chatMessageModelDAO;
    }

    @Override
    public UUID save(ChatMessageModel chatMessageModel) {
        return chatMessageModelDAO.save(chatMessageModel);
    }

    @Override
    public ChatMessageModel get(UUID uuid) {
        return chatMessageModelDAO.get(uuid);
    }

    @Override
    public List getAll() {
        messages = new ArrayList<>();
        Optional<List<ChatMessageModel>> first = Optional.ofNullable(chatMessageModelDAO.getAll());
        first.ifPresent(chatMessageModels -> {
            for (ChatMessageModel model : chatMessageModels)
                messages.add(model.getText());
        });
        return messages;
    }

}
