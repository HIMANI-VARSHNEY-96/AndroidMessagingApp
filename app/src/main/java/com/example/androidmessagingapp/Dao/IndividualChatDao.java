package com.example.androidmessagingapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidmessagingapp.Entity.IndividualChatEntity;

import java.util.List;

@Dao
public interface IndividualChatDao {

    @Insert
    void insert(IndividualChatEntity individualChatEntity);

    @Query("Select * from IndividualChat where contactNumber = :contactNumber")
    LiveData<List<IndividualChatEntity>> getAllChats(String contactNumber);
}
