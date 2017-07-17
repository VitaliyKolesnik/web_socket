package com.kolesnyk.model.dao;


import com.kolesnyk.model.entity.ChatMessageModel;

import java.util.List;
import java.util.UUID;

public interface ChatMessageModelDAO {

    UUID save(ChatMessageModel chatMessageModel);

    ChatMessageModel get(UUID uuid);

    List getAll();
}
