package com.example.androidmessagingapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.androidmessagingapp.Entity.IndividualChatEntity;

@Dao
public interface IndividualChatDao {

    @Insert
    void insert(IndividualChatEntity individualChatEntity);
}
