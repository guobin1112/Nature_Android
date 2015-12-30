package com.cn.balala.nature.nature.model;

/**
 * Created by daisy on 15/10/13.
 */
public class MessageModel {
    public String title;
    public String type;
    public String publish_date;

    public MessageModel(String title, String type, String publish_date) {
        this.title = title;
        this.type = type;
        this.publish_date = publish_date;
    }
}
