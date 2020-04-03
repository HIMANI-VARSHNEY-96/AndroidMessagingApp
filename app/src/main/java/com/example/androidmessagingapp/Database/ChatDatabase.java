package com.example.androidmessagingapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.androidmessagingapp.ConstantString;
import com.example.androidmessagingapp.Dao.AllChatSummaryDao;
import com.example.androidmessagingapp.Dao.IndividualChatDao;
import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;
import com.example.androidmessagingapp.Entity.IndividualChatEntity;
import com.example.androidmessagingapp.R;

@Database(entities = {AllChatSummaryEntity.class, IndividualChatEntity.class}, version = 1)
public abstract class ChatDatabase extends RoomDatabase {

    public abstract AllChatSummaryDao allChatSummaryDao();

    public abstract IndividualChatDao individualChatDao();

    private static volatile ChatDatabase chatDatabase;

    public static ChatDatabase getChatDatabase(final Context context){
        if(chatDatabase == null){
            synchronized (ChatDatabase.class){
                if(chatDatabase == null){
                    chatDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            ChatDatabase.class, ConstantString.CHAT_DATABASE)
                            .build();
                }
            }
        }
        return chatDatabase;
    }
}
