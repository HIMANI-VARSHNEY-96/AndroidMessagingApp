package com.example.androidmessagingapp.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import com.example.androidmessagingapp.Helper.TimeStampConverter;

@Entity(tableName = "IndividualChat")
public class IndividualChatEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo
    private String contactNumber;
    private String smsBody;
    private String smsType;

    @TypeConverters({TimeStampConverter.class})
    private Date smsTimestamp;

    public Date getSmsTimestamp() {
        return smsTimestamp;
    }

    public void setSmsTimestamp(Date smsTimestamp) {
        this.smsTimestamp = smsTimestamp;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public IndividualChatEntity(String contactNumber, String smsBody, String smsType) {
        if(contactNumber.startsWith("+91")) {
            this.contactNumber = contactNumber.substring(3);
        }else if(contactNumber.startsWith("0")){
            this.contactNumber = contactNumber.substring(1);
        }else{
            this.contactNumber = contactNumber;
        }
        this.smsBody = smsBody;
        this.smsType = smsType;
    }
}
