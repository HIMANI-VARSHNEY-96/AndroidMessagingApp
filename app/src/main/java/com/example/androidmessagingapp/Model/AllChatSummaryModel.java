package com.example.androidmessagingapp.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.androidmessagingapp.Dao.AllChatSummaryDao;
import com.example.androidmessagingapp.Database.ChatDatabase;
import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;
import java.util.Date;
import java.util.List;

public class AllChatSummaryModel {

    private String TAG = this.getClass().getSimpleName();
    private AllChatSummaryDao allChatSummaryDao;
    private ChatDatabase chatDatabase;
    private LiveData<List<AllChatSummaryEntity>> allChatList;

    public AllChatSummaryModel(@NonNull Context context){
        chatDatabase = ChatDatabase.getChatDatabase(context);
        allChatSummaryDao = chatDatabase.allChatSummaryDao();
        allChatList = allChatSummaryDao.getAllChats();
    }


    public void insert(AllChatSummaryEntity allChatSummaryEntity){
        Log.i(TAG,"Inside insert method");
        allChatSummaryEntity.setSmsTimestamp(new Date());
        new InsertAsyncTask(allChatSummaryDao).execute(allChatSummaryEntity);
    }

    private class InsertAsyncTask extends AsyncTask<AllChatSummaryEntity, Void, Void> {

        AllChatSummaryDao allChatSummaryDao;

        public InsertAsyncTask(AllChatSummaryDao allChatSummaryDao){
                this.allChatSummaryDao = allChatSummaryDao;
        }

        @Override
        protected Void doInBackground(AllChatSummaryEntity... allChatSummaryEntities) {
            Log.i(TAG,"Background insertion running");

            allChatSummaryDao.insert(allChatSummaryEntities[0]);
            return null;
        }
    }

    public LiveData<List<AllChatSummaryEntity>> getAllChats(){
        allChatList = allChatSummaryDao.getAllChats();
        Log.i(TAG, allChatList.toString());
        return allChatList;
    }

}
