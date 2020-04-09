package com.example.androidmessagingapp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.example.androidmessagingapp.Adapter.AllChatSummaryAdapter;
import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;
import com.example.androidmessagingapp.Model.AllChatSummaryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.widget.TextView;
import android.widget.Toast;

public class AllChatSummaryActivity extends AppCompatActivity implements AllChatSummaryAdapter.OnChatSummaryListener{

    private AllChatSummaryModel allChatSummaryModel;
    private String TAG = this.getClass().getSimpleName();
    private RecyclerView recyclerView;
    private List<AllChatSummaryEntity> chatSummaryList;

    private static final int MY_PERMISSIONS_SEND_RECEIVE_SMS = 1;
    private TextView noChatFoundTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chat_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkPermission();

        allChatSummaryModel = new AllChatSummaryModel(getApplication());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AllChatSummaryActivity.this, SendNewMessageActivity.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.allChatSummaryRecyclerView);
        noChatFoundTV = (TextView)findViewById(R.id.noChatFoundTV);

        final AllChatSummaryAdapter adapter = new AllChatSummaryAdapter(this, this);

        recyclerView.setAdapter(adapter);

        allChatSummaryModel.getAllChats().observe(this, new Observer<List<AllChatSummaryEntity>>() {
            @Override
            public void onChanged(@Nullable List<AllChatSummaryEntity> allChatList) {
                if(adapter.getItemCount()==0){
                    noChatFoundTV.setText(ConstantString.NO_CHAT_RECORD_FOUND);
                }else{
                    noChatFoundTV.setText("");
                }
                adapter.setChatNotes(allChatList);
                chatSummaryList = allChatList;
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onChatSummaryClick(int position) {
        Log.i(TAG, position + "item clicked");
        Intent i = new Intent(AllChatSummaryActivity.this, IndividualChatActivity.class);
        i.putExtra("contactNumber",chatSummaryList.get(position).getContactName());
        startActivity(i);
    }

    //Function to check and request permission
    public void checkPermission(){
        if((ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                        != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(AllChatSummaryActivity.this, new String[] {Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_SEND_RECEIVE_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case MY_PERMISSIONS_SEND_RECEIVE_SMS:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Granted for SMS send/receive", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Please give permissions!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
