package com.example.androidmessagingapp.Adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmessagingapp.Entity.AllChatSummaryEntity;
import com.example.androidmessagingapp.Entity.IndividualChatEntity;
import com.example.androidmessagingapp.Model.AllChatSummaryModel;
import com.example.androidmessagingapp.Model.IndividualChatModel;
import com.example.androidmessagingapp.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class IndividualChatAdapter extends RecyclerView.Adapter<IndividualChatAdapter.IndividualChatViewHolder> {
    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<IndividualChatEntity> individualChatList;

    private static final String SMS_TYPE = "sent";
    public IndividualChatAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public IndividualChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_individual_chat,parent,false);
        IndividualChatViewHolder holder = new IndividualChatViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        if(individualChatList!=null) {
            return individualChatList.size();
        }else{
            return 0;
        }
    }

    public void setChatNotes(List<IndividualChatEntity> individualChatList){
        this.individualChatList = individualChatList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull IndividualChatViewHolder holder, int position) {
        IndividualChatEntity current = individualChatList.get(position);
        holder.setData(current,position);
    }

    class IndividualChatViewHolder extends  RecyclerView.ViewHolder{

        private TextView individualChatTextView, individualChatTSTextView;
        private LinearLayout layout;
        private CardView card;
        private int position;
        private IndividualChatEntity indiVidualChatElement;

        public IndividualChatViewHolder(@NonNull View itemView) {
            super(itemView);
            individualChatTextView = (TextView)itemView.findViewById(R.id.individualChatTextView);
            individualChatTSTextView = (TextView)itemView.findViewById(R.id.individualChatTSTextView);
            layout = (LinearLayout)itemView.findViewById(R.id.individualChatLayoutWrapper);
            card = (CardView)itemView.findViewById(R.id.icCard);
        }

        public void setData(IndividualChatEntity current, int position) {
            this.individualChatTextView.setText(current.getSmsBody());

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
                formatter = DateFormat.getTimeInstance(DateFormat.SHORT);
                dateText = dateText+" "+formatter.format(date);
            }
            this.individualChatTSTextView.setText(dateText);

            this.position = position;
            this.indiVidualChatElement = current;

            String smsType = current.getSmsType();
            if(smsType.equals(SMS_TYPE)){
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                params.setMargins(50,0,0,0);
                layout.setLayoutParams(params);
                layout.setGravity(Gravity.RIGHT);
                card.setCardBackgroundColor(mContext.getResources().getColor(R.color.senderChatCardColor));
            }else{
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0,0,50,0);
                layout.setLayoutParams(params);
                layout.setGravity(Gravity.LEFT);
                card.setCardBackgroundColor(mContext.getResources().getColor(R.color.receiverChatCardColor));

            }
        }
    }


}
