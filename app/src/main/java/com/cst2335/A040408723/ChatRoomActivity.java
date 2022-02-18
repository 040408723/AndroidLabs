package com.cst2335.A040408723;


import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {

    Button sendButton;
    Button receiveButton;
    EditText typeMessage;


    private ArrayList<Message> list = new ArrayList<>();
    MyListAdapter myAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sendButton = findViewById(R.id.buttonSend);
        receiveButton = findViewById(R.id.buttonReceive);
        typeMessage = findViewById(R.id.edittext1);

        ListView myListView = findViewById(R.id.listview1);
        myListView.setAdapter(myAdapter = new MyListAdapter());

        sendButton.setOnClickListener(click -> {

            String typeText = typeMessage.getText().toString();
            View image = findViewById(R.id.send);

            if (!typeText.isEmpty()) {
                Message newMsg = new Message(true, typeText);
                list.add(newMsg);
                myAdapter.notifyDataSetChanged();

            }
        });

        receiveButton.setOnClickListener(click -> {
            String typeText = typeMessage.getText().toString();
            View image = findViewById(R.id.receive);

            if (!typeText.isEmpty()) {

                Message newMsg = new Message(false, typeText);
                list.add(newMsg);
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    private class MyListAdapter extends BaseAdapter {
        List<Message> messages=new ArrayList<Message>();

        public void add(Message message){
            this.messages.add(message);
            notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return messages.get(i);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MessageViewHolder holder = new MessageViewHolder();
            View newView = inflater.inflate(R.layout.message, parent, false);
            LayoutInflater inflater = getLayoutInflater();
            Message message=messages.get(position);
            if(message.isSendOrReceive()) {
                holder.avatar = findViewById(R.id.send);

                newView.setTag(holder);
                holder.messageBody.setText(message.getText());
            }else{
                holder.avatar=findViewById(R.id.receive);
                newView.setTag(holder);
                holder.messageBody.setText(message.getText());
            }
            return newView;


        }

        class MessageViewHolder {
            public View avatar;
            public TextView messageBody;

        }

        public class Message {
            public boolean sendOrReceive;
            String msgType;

            public Message(boolean sendOrReceive, String msgType) {
                this.msgType = msgType;
                this.sendOrReceive = sendOrReceive;

            }

            public String getMsgType() {
                return msgType;
            }

            public boolean isSendOrReceive() {
                return sendOrReceive;
            }

        }
    }
}


