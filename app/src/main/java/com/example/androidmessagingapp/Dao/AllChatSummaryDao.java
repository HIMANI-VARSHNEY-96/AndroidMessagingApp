package com.example.androidmessagingapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;

@Dao
public interface AllChatSummaryDao {

    @Insert
    void insert(AllChatSummaryEntity allChatSummaryEntity);
}
