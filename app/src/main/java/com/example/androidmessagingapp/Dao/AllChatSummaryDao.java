
package com.example.androidmessagingapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;

import java.util.List;

@Dao
public interface AllChatSummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AllChatSummaryEntity allChatSummaryEntity);

    @Update
    void update(AllChatSummaryEntity allChatSummaryEntity);

    @Query("Select * from AllChatSummary Order By smsTimestamp DESC")
    LiveData<List<AllChatSummaryEntity>> getAllChats();

//    @Query("Select * from AllChatSummary where id IN (Select MAX(id) from  AllChatSummary GROUP BY contactName) Order By smsTimestamp DESC" )
//    LiveData<List<AllChatSummaryEntity>> getAllChats();
//
//    @Query("Select id from AllChatSummary where contactName = :contactName")
//    int validateIfRecordExist(String contactName);
}