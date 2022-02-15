package com.cst2335.A040408723;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    Button sendButton;
    Button receiveButton;
    EditText typeMessage;
    RecyclerView rView;
    MyAdapter theAdapter;

    ArrayList<Message> messages=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sendButton=findViewById(R.id.buttonSend);
        receiveButton=findViewById(R.id.buttonReceive);
        typeMessage=findViewById(R.id.edittext1);
        rView=findViewById(R.id.myRecyclerView);
        theAdapter=new MyAdapter();
        rView.setAdapter(theAdapter);

        rView.setLayoutManager(new LinearLayoutManager(this));


        sendButton.setOnClickListener(click->{
            String typeMessage=edit.getText().toString();
            if(!typeMessage.isEmpty()){
                messages.add(new Message(typeMessage));
                edit.setText("");
                theAdapter.notifyItemInserted(messages.size()-1);
            }
        });

        receiveButton.setOnClickListener(click->{
            String typeMessage=edit.getText().toString();
            if(!typeMessage.isEmpty()){
                messages.add(new Message(typeMessage));
                edit.setText("");
                theAdapter.notifyItemInserted(messages.size()-1);
            }
        });
    }


    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater li=getLayoutInflater();
            View thisRow=li.inflate(R.layout.message, parent, false);
            return new MyViewHolder(thisRow);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Message thisRow=messages.get(position);
            holder.messageView.setText(thisRow.getMessageTyped());
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton sendButton=findViewById(R.id.send);
        ImageButton receiveButton=findViewById(R.id.receive);
        TextView sendMessage=findViewById(R.id.sendmessage);
        TextView receiveMessage=findViewById(R.id.receivemessage);
        public MyViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(click->{
                int position=getAdapterPosition();

            });



    }


    public class Message {
        String sendMessage;
        String receiveMessage;

        public Message(String sendMessage, String receiveMessage) {
            this.sendMessage = sendMessage;
            this.receiveMessage = receiveMessage;
        }

        public String getSendMessage() {
            return sendMessage;
        }

        public String getReceiveMessage() {
            return receiveMessage;
        }
    }
    }
}