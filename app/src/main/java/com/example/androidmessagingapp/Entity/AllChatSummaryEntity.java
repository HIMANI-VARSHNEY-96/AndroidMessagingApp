package com.example.androidmessagingapp.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AllChatSummary")
public class AllChatSummaryEntity {

    public AllChatSummaryEntity(int id, String contactName, String lastMessage, String smsType) {
        this.id = id;
        this.contactName = contactName;
        this.lastMessage = lastMessage;
        this.smsType = smsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private String contactName;
    private String lastMessage;
    private String smsType;


}
