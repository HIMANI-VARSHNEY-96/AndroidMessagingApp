package com.example.androidmessagingapp.Model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.androidmessagingapp.Dao.AllChatSummaryDao;
import com.example.androidmessagingapp.Database.ChatDatabase;
import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;

import java.util.ArrayList;
import java.util.List;

public class AllChatSummaryModel extends ViewModel {

    private String TAG = this.getClass().getSimpleName();
    private AllChatSummaryDao allChatSummaryDao;
    private ChatDatabase chatDatabase;

    public AllChatSummaryModel() {
        super();
    }

    public AllChatSummaryModel(@NonNull Application application) {
//        super(application);
        chatDatabase = ChatDatabase.getChatDatabase(application);
        allChatSummaryDao = chatDatabase.allChatSummaryDao();
    }

    public void insert(AllChatSummaryEntity allChatSummaryEntity){
        Log.i(TAG,"Inside insert method");
        new InsertAsyncTask(allChatSummaryDao).execute(allChatSummaryEntity);
    }



    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"View model destroyed");
    }

    private String contactNumber, lastMessage;
    long timeStamp;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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

    /** public static List<AllChatSummaryModel> getAllChatSummaryObjectList(){
        List<AllChatSummaryModel> allChatSummaryList = new ArrayList<>();

        for(int i = 1; i<=10; i++){
            AllChatSummaryModel allChatSummaryModel = new AllChatSummaryModel();
            allChatSummaryModel.setContactNumber("Contact"+i);
            allChatSummaryModel.setLastMessage("Last Message lorel ipsum is default paragraph"+i);
            allChatSummaryModel.setTimeStamp(2345);

            allChatSummaryList.add(allChatSummaryModel);
        }
        return allChatSummaryList;
    }**/
}
