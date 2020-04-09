package com.example.androidmessagingapp.Adapter;

import java.util.Date;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;
import com.example.androidmessagingapp.R;

import java.text.DateFormat;
import java.util.List;

public class AllChatSummaryAdapter extends RecyclerView.Adapter<AllChatSummaryAdapter.AllChatSummaryViewHolder> {
    private LayoutInflater layoutInflater;
    private List<AllChatSummaryEntity> allChatList;
    private OnChatSummaryListener onChatSummaryListener;

    public AllChatSummaryAdapter(Context context, OnChatSummaryListener onChatSummaryListener){
        layoutInflater = LayoutInflater.from(context);
        this.onChatSummaryListener = onChatSummaryListener;
    }

    @NonNull
    @Override
    public AllChatSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_all_chats_summary,parent,false);
        AllChatSummaryViewHolder holder = new AllChatSummaryViewHolder(view, onChatSummaryListener);
        return holder;
    }

    @Override
    public int getItemCount() {
        if(allChatList!=null) {
            return allChatList.size();
        }else{
            return 0;
        }
    }

    public void setChatNotes(List<AllChatSummaryEntity> allChatList){
        this.allChatList = allChatList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AllChatSummaryViewHolder holder, int position) {
        AllChatSummaryEntity current = allChatList.get(position);
        holder.setData(current,position);
    }

    public class AllChatSummaryViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView contactName;
        private TextView lastMessage;
        private TextView timeStamp;
        private int position;
        private AllChatSummaryEntity allChatList;
        OnChatSummaryListener onChatSummaryListener;

        public AllChatSummaryViewHolder(@NonNull View itemView, OnChatSummaryListener onChatSummaryListener) {
            super(itemView);
            contactName = (TextView)itemView.findViewById(R.id.contactNameTV);
            lastMessage = (TextView)itemView.findViewById(R.id.lastMessageTV);
            timeStamp = (TextView)itemView.findViewById(R.id.timestampTV);

            this.onChatSummaryListener = onChatSummaryListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            onChatSummaryListener.onChatSummaryClick(getAdapterPosition());
        }

        public void setData(AllChatSummaryEntity current, int position) {
            this.contactName.setText(current.getContactName());

            String lastMessageText = current.getLastMessage();
            if(lastMessageText.length() >= 50){
                lastMessageText = lastMessageText.substring(0,49)+"...";
            }
            this.lastMessage.setText(lastMessageText);


            Date date = current.getSmsTimestamp();
            Date currentDate = new Date();
            DateFormat formatter;
            String dateText;
            formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
            if(formatter.format(date).equals(formatter.format(currentDate))){
                formatter = DateFormat.getTimeInstance(DateFormat.SHORT);
                dateText = formatter.format(date);
            }else{
                formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
                dateText = formatter.format(date);
            }
            this.timeStamp.setText(dateText);

            this.position = position;
            this.allChatList = current;
        }
    }


    public interface OnChatSummaryListener{
        void onChatSummaryClick(int position);
    }


}
