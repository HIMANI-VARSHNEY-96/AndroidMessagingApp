package com.example.androidmessagingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmessagingapp.Model.AllChatSummaryModel;
import com.example.androidmessagingapp.R;

import java.util.List;

public class AllChatSummaryAdapter extends RecyclerView.Adapter<AllChatSummaryAdapter.AllChatSummaryViewHolder> {
    private List<AllChatSummaryModel> allChatSummaryList;
    private LayoutInflater layoutInflater;

    public AllChatSummaryAdapter(Context context, List<AllChatSummaryModel> lastMessageModelList){
        layoutInflater = LayoutInflater.from(context);
        this.allChatSummaryList = lastMessageModelList;
    }

    @NonNull
    @Override
    public AllChatSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_all_chats_summary,parent,false);
        AllChatSummaryViewHolder holder = new AllChatSummaryViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return allChatSummaryList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull AllChatSummaryViewHolder holder, int position) {
        AllChatSummaryModel current = allChatSummaryList.get(position);
        holder.setData(current,position);
    }

    class AllChatSummaryViewHolder extends  RecyclerView.ViewHolder{

        private TextView contactName;
        private TextView lastMessage;
        private TextView timeStamp;
        private int position;
        private AllChatSummaryModel allChatSummaryModel;

        public AllChatSummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = (TextView)itemView.findViewById(R.id.contactNameTV);
            lastMessage = (TextView)itemView.findViewById(R.id.lastMessageTV);
            timeStamp = (TextView)itemView.findViewById(R.id.timestampTV);
        }

        public void setData(AllChatSummaryModel current, int position) {
            this.contactName.setText(current.getContactNumber());
            this.lastMessage.setText(current.getLastMessage());
            this.timeStamp.setText(Long.toString(current.getTimeStamp()));
            this.position = position;
            this.allChatSummaryModel = current;


        }
    }
}
