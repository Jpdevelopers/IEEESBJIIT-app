package com.dev.ieeesbjiit.ieeesbjiit;

/**
 * Created by yugank on 28/9/17.
 */

public class MessageClass {
    private String sender;
    private String content;
    private String uid;
    public MessageClass() {
    }

    public MessageClass(String sender, String content, String uid) {
        this.sender = sender;
        this.content = content;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public MessageClass(String sender, String content) {
        this.sender= sender;

        this.content = content;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
