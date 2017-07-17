package com.kolesnyk.model.dao.impl;

import com.kolesnyk.model.dao.ChatMessageModelDAO;
import com.kolesnyk.model.entity.ChatMessageModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class ChatMessageModelDAOImpl extends HibernateDaoSupport implements ChatMessageModelDAO {

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public ChatMessageModelDAOImpl(SessionFactory sessionFactory){
//        HibernateTemplate hibernateTemplate = createHibernateTemplate(sessionFactory);
//        setHibernateTemplate(hibernateTemplate);
//        this.hibernateTemplate = hibernateTemplate;
        hibernateTemplate = createHibernateTemplate(sessionFactory);
        setHibernateTemplate(hibernateTemplate);
    }

    @Override
    @Transactional
    public UUID save(ChatMessageModel chatMessageModel) {
        return (UUID) getHibernateTemplate().save(chatMessageModel);
    }

    @Override
    public ChatMessageModel get(UUID uuid) {
        return hibernateTemplate.get(ChatMessageModel.class, uuid);
    }

    @Override
    public List getAll() {
        return hibernateTemplate.findByExample(ChatMessageModel.class);
    }
}
