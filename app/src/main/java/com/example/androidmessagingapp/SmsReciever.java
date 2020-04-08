package com.example.androidmessagingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;
import com.example.androidmessagingapp.Entity.IndividualChatEntity;
import com.example.androidmessagingapp.Model.AllChatSummaryModel;
import com.example.androidmessagingapp.Model.IndividualChatModel;

public class SmsReciever extends BroadcastReceiver {

    private String TAG = this.getClass().getSimpleName();
    private AllChatSummaryModel allChatSummaryModel;
    private IndividualChatModel individualChatModel;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.i(TAG,"Message Recieved");
        if(bundle!=null){
            final Object[] pdusObj = (Object[])bundle.get("pdus");
            String format = bundle.get("format").toString();

            for(int i=0; i<pdusObj.length;i++){

                SmsMessage message = SmsMessage.createFromPdu((byte[])pdusObj[i],format);
                String recieverPhn = message.getDisplayOriginatingAddress();
                String messageBody = message.getDisplayMessageBody();
                Long timestamp = message.getTimestampMillis();

                Log.i(TAG,"Phone "+recieverPhn);
                Log.i(TAG, "Message Body "+messageBody);
                Log.i(TAG, "timestamp "+timestamp);

                allChatSummaryModel = new AllChatSummaryModel(context);
                AllChatSummaryEntity allChatSummaryEntity = new AllChatSummaryEntity(recieverPhn,messageBody,"received");
                allChatSummaryModel.insert(allChatSummaryEntity);

                individualChatModel = new IndividualChatModel(context);
                IndividualChatEntity individualChatEntity = new IndividualChatEntity(recieverPhn,messageBody,"received");
                individualChatModel.insert(individualChatEntity);

                Toast.makeText(context, "Message Received",Toast.LENGTH_LONG).show();

            }
        }
    }
}

