package com.example.androidmessagingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;
import com.example.androidmessagingapp.Model.AllChatSummaryModel;

public class SendNewMessageActivity extends AppCompatActivity {

    EditText recieverContactET;
    EditText messageBodyET;
    Button sendMsgButton;
    private AllChatSummaryModel allChatSummaryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_new_message);

        recieverContactET = (EditText)findViewById(R.id.recieverContactET);
        messageBodyET = (EditText)findViewById(R.id.typeMessageET);
        sendMsgButton = (Button)findViewById(R.id.sendMessageButton);

        allChatSummaryModel = new ViewModelProvider(this).get(AllChatSummaryModel.class);

    }

    public void sendMessage(View view){
        String contact = recieverContactET.getText().toString();
        String messageBody = messageBodyET.getText().toString();

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        SmsManager sms = SmsManager.getSmsManagerForSubscriptionId(SubscriptionManager.getDefaultSubscriptionId());
        Log.i("SendmEssage","SubscriptionManager.getDefaultSmsSubscriptionId "+SubscriptionManager.getDefaultSubscriptionId());
        sms.sendTextMessage(contact,null,messageBody,pi,null);


        AllChatSummaryEntity allChatSummaryEntity = new AllChatSummaryEntity(1,contact,messageBody,"sent");
        allChatSummaryModel.insert(allChatSummaryEntity);

        Toast.makeText(this, "Message Sent", Toast.LENGTH_LONG);


    }
}
