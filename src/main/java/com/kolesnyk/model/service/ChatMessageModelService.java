package com.kolesnyk.model.service;

import com.kolesnyk.model.entity.ChatMessageModel;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ChatMessageModelService {

    UUID save(ChatMessageModel chatMessageModel);

    ChatMessageModel get (UUID uuid);

    List<String> getAll();
}
