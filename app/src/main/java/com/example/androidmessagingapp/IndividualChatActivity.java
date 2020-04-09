package com.example.androidmessagingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidmessagingapp.Adapter.IndividualChatAdapter;
import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;
import com.example.androidmessagingapp.Entity.IndividualChatEntity;
import com.example.androidmessagingapp.Model.AllChatSummaryModel;
import com.example.androidmessagingapp.Model.IndividualChatModel;

import java.util.List;

public class IndividualChatActivity extends AppCompatActivity {

    EditText messageBodyET;
    Button sendMsgButton;
    private AllChatSummaryModel allChatSummaryModel;
    private IndividualChatModel individualChatModel;
    private String contactNumber;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_chat);
        Intent i = getIntent();
        contactNumber = i.getStringExtra("contactNumber");
        this.setTitle(contactNumber);

        messageBodyET = (EditText)findViewById(R.id.typeMessageET);
        sendMsgButton = (Button)findViewById(R.id.sendMessageButton);

        Log.i(TAG, "Contact Number is "+contactNumber);
        Toast.makeText(this, "contact number is "+contactNumber, Toast.LENGTH_LONG).show();

        allChatSummaryModel = new AllChatSummaryModel(getApplication());
        individualChatModel = new IndividualChatModel(getApplication());

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.individualChatRecyclerView);
        final IndividualChatAdapter adapter = new IndividualChatAdapter(this);

        recyclerView.setAdapter(adapter);

        individualChatModel.getAllChats(contactNumber).observe(this, new Observer<List<IndividualChatEntity>>() {
            @Override
            public void onChanged(@Nullable List<IndividualChatEntity> individualChatList) {
                adapter.setChatNotes(individualChatList);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void sendMessage(View view){
        String messageBody = messageBodyET.getText().toString();

        SmsManager sms = SmsManager.getSmsManagerForSubscriptionId(SubscriptionManager.getDefaultSubscriptionId());
        Log.i(TAG,"SubscriptionManager.getDefaultSmsSubscriptionId "+SubscriptionManager.getDefaultSubscriptionId());
        sms.sendTextMessage(contactNumber,null,messageBody,null,null);


        AllChatSummaryEntity allChatSummaryEntity = new AllChatSummaryEntity(contactNumber,messageBody,"sent");
        allChatSummaryModel.insert(allChatSummaryEntity);

        IndividualChatEntity individualChatEntity = new IndividualChatEntity(contactNumber,messageBody,"sent");
        individualChatModel.insert(individualChatEntity);

        messageBodyET.setText("");

        Toast.makeText(this, "Message Sent", Toast.LENGTH_LONG);

    }
}
