package com.cst2335.A040408723;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    Button sendButton;
    Button receiveButton;
    EditText typeMessage;
    MyAdapter theAdapter;


    ArrayList<Message> messages=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sendButton=findViewById(R.id.buttonSend);
        receiveButton=findViewById(R.id.buttonReceive);
        typeMessage=findViewById(R.id.edittext1);
        theAdapter=new MyAdapter();
        ListView myList=findViewById(R.id.listview1);
        myList.setAdapter(theAdapter=new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount(){
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return "This is row"+position;
        }

        @Override
        public long getItemId(int position) {
            return (long)position;
        }

        @Override
        public View getView(int position, View old, ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();

            View newView=inflater.inflate(R.layout.message,parent,false);

            TextView tView=newView.findViewById(R.id.sendmessage);
            tView.setText(getItem(position).toString());
            return newView;
        }
    }
}