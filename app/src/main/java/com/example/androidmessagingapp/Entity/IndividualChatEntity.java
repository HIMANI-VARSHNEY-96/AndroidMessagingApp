package com.example.androidmessagingapp.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "IndividualChat")
public class IndividualChatEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private String sender;
    private String receiver;
    private String smsBody;
    private String smsType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public IndividualChatEntity(int id, String sender, String receiver, String smsBody, String smsType) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.smsBody = smsBody;
        this.smsType = smsType;
    }

}
