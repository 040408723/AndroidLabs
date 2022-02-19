package com.cst2335.A040408723;


import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;

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


    ArrayList<Message> list = new ArrayList<>();
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


            if (!typeText.isEmpty()) {
                Message newMsg = new Message(true,typeText);
                list.add(newMsg);
                typeMessage.setText("");
                myAdapter.notifyDataSetChanged();

            }
        });

        receiveButton.setOnClickListener(click -> {
            String typeText = typeMessage.getText().toString();


            if (!typeText.isEmpty()) {
                Message newMsg = new Message(false,typeText);
                list.add(newMsg);
                typeMessage.setText("");
                myAdapter.notifyDataSetChanged();
            }
        });
    }


    private class MyListAdapter extends BaseAdapter {
        List<Message> messages = new ArrayList<>();

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
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint("ViewHolder") View newView = inflater.inflate(R.layout.message, parent, false);

            Message msg = new Message();

            if (msg.isSendOrReceive()) {

                holder.avatar = findViewById(R.id.send);
                holder.text=(TextView)convertView.findViewById(R.id.sendmessage);
                newView.setTag(holder);

            } else {
                holder.avatar = findViewById(R.id.receive);
                //holder.text=(TextView)convertView.findViewById(R.id.receivemessage);
                newView.setTag(holder);
            }

            return newView;

        }

        class MessageViewHolder {
            public View avatar;
            TextView text;
        }
    }

        public class Message {
            public boolean sendOrReceive;
            String msgType;

            public Message(boolean sendOrReceive, String msgType) {
                this.msgType = msgType;
                this.sendOrReceive = sendOrReceive;

            }

            public Message() {

            }

            public String getMsgType() {
                return msgType;
            }

            public boolean isSendOrReceive() {
                return sendOrReceive;
            }

        }

}


