package com.example.androidmessagingapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidmessagingapp.Adapter.AllChatSummaryAdapter;
import com.example.androidmessagingapp.Model.AllChatSummaryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.AllocationAdapter;
import android.view.View;

public class AllChatSummaryActivity extends AppCompatActivity {

    private AllChatSummaryModel allChatSummaryModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chat_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent i = new Intent(AllChatSummaryActivity.this, SendNewMessageActivity.class);
                startActivity(i);
            }
        });

        allChatSummaryModel = new ViewModelProvider(this).get(AllChatSummaryModel.class);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.allChatSummaryRecyclerView);
//        AllChatSummaryAdapter adapter = new AllChatSummaryAdapter(this, AllChatSummaryModel.getAllChatSummaryObjectList());
//        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
