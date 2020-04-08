package com.example.androidmessagingapp.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.androidmessagingapp.Helper.TimeStampConverter;

import java.util.Date;

@Entity(tableName = "AllChatSummary")
public class AllChatSummaryEntity {

    public AllChatSummaryEntity(String contactName, String lastMessage, String smsType) {

        if(contactName.startsWith("+91")) {
            this.contactName = contactName.substring(3);
        }else if(contactName.startsWith("0")){
            this.contactName = contactName.substring(1);
        }else{
            this.contactName = contactName;
        }
        this.lastMessage = lastMessage;
        this.smsType = smsType;
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

    public Date getSmsTimestamp() {
        return smsTimestamp;
    }

    public void setSmsTimestamp(Date smsTimestamp) {
        this.smsTimestamp = smsTimestamp;
    }

    @PrimaryKey
    @NonNull
    private String contactName;
    private String lastMessage;
    private String smsType;

    @TypeConverters({TimeStampConverter.class})
    private Date smsTimestamp;
}
