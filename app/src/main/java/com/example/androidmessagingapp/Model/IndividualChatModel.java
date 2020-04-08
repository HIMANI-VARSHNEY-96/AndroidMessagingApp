package com.example.androidmessagingapp.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.androidmessagingapp.Dao.IndividualChatDao;
import com.example.androidmessagingapp.Database.ChatDatabase;
import com.example.androidmessagingapp.Entity.IndividualChatEntity;

import java.util.Date;
import java.util.List;

public class IndividualChatModel {

    private String TAG = this.getClass().getSimpleName();
    private IndividualChatDao individualChatDao;
    private ChatDatabase chatDatabase;
    private LiveData<List<IndividualChatEntity>> individualChatList;

    public IndividualChatModel(@NonNull Context context){
        chatDatabase = ChatDatabase.getChatDatabase(context);
        individualChatDao = chatDatabase.individualChatDao();
    }


    public void insert(IndividualChatEntity individualChatEntity){
        Log.i(TAG,"Inside insert method");
        individualChatEntity.setSmsTimestamp(new Date());
        new InsertAsyncTask(individualChatDao).execute(individualChatEntity);
    }

    private class InsertAsyncTask extends AsyncTask<IndividualChatEntity, Void, Void> {

        IndividualChatDao individualChatDao;

        public InsertAsyncTask(IndividualChatDao individualChatDao){
                this.individualChatDao = individualChatDao;
        }

        @Override
        protected Void doInBackground(IndividualChatEntity... individualChatEntities) {
            Log.i(TAG,"Background insertion running");

            individualChatDao.insert(individualChatEntities[0]);
            return null;
        }
    }

    public LiveData<List<IndividualChatEntity>> getAllChats(String contactNumber){
        individualChatList = individualChatDao.getAllChats(contactNumber);
        Log.i(TAG, individualChatList.toString());
        return individualChatList;
    }

}
