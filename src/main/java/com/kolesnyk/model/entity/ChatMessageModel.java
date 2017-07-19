package com.kolesnyk.model.entity;

import com.kolesnyk.model.service.Parameters;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "chat_message_model")
public class ChatMessageModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "TEXT")
    private String text;
//    @Column(name = "AUTHOR")
//    private String author;
    @Column(name = "DATE")
    private Date createDate;

    public ChatMessageModel() {
    }

    public ChatMessageModel(String text, Date createDate) {
        this.text = text;
//        this.author = author;
        this.createDate = createDate;
    }

    public ChatMessageModel(Parameters parameters){
        this.text = parameters.text;
//        this.author = parameters.author;
        this.createDate = parameters.createDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public String getAuthor() {
//        return author;
//    }

//    public void setAuthor(String author) {
//        this.author = author;
//    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChatMessageModel{");
        sb.append("id='").append(id).append('\'');
        sb.append(", text='").append(text).append('\'');
//        sb.append(", author='").append(author).append('\'');
        sb.append(", createDate=").append(createDate);
        sb.append('}');
        return sb.toString();
    }
}
