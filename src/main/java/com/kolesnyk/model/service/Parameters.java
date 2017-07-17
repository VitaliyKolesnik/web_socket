package com.kolesnyk.model.service;

import java.util.Date;

public class Parameters {

    public String text;
    public String author;
    public Date createDate;

    public Parameters(String text, String author, Date createDate) {
        this.text = text;
        this.author = author;
        this.createDate = createDate;
    }
}
