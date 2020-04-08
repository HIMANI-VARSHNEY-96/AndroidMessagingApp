package com.example.androidmessagingapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidmessagingapp.Adapter.AllChatSummaryAdapter;
import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;
import com.example.androidmessagingapp.Model.AllChatSummaryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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

public class AllChatSummaryActivity extends AppCompatActivity implements AllChatSummaryAdapter.OnChatSummaryListener{

    private AllChatSummaryModel allChatSummaryModel;
    private String TAG = this.getClass().getSimpleName();
    private RecyclerView recyclerView;
    private List<AllChatSummaryEntity> chatSummaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chat_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        allChatSummaryModel = new AllChatSummaryModel(getApplication());

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

        recyclerView = (RecyclerView)findViewById(R.id.allChatSummaryRecyclerView);
        final AllChatSummaryAdapter adapter = new AllChatSummaryAdapter(this, this);

        recyclerView.setAdapter(adapter);

        allChatSummaryModel.getAllChats().observe(this, new Observer<List<AllChatSummaryEntity>>() {
            @Override
            public void onChanged(@Nullable List<AllChatSummaryEntity> allChatList) {
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
}
