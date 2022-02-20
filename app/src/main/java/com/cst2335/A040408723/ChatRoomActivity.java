package com.cst2335.A040408723;


import androidx.appcompat.app.AlertDialog;
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
import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    Button sendButton;
    Button receiveButton;
    EditText typeMessage;

    ArrayList<Message> list = new ArrayList<>();
    MyListAdapter myAdapter;

    public class Message {

        private String msgType;
        boolean sendOrReceive;

        public Message(boolean sendOrReceive, String msgType) {
            this.msgType = msgType;
            this.sendOrReceive = sendOrReceive;
        }
        public boolean isSendOrReceive() {
            return sendOrReceive;
        }
    }

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

            Message newMsg = new Message(true, typeText);
            list.add(newMsg);
            typeMessage.setText("");
            myAdapter.notifyDataSetChanged();
        });

        receiveButton.setOnClickListener(click -> {
            String typeText = typeMessage.getText().toString();
            Message newMsg = new Message(false, typeText);
            list.add(newMsg);
            typeMessage.setText("");
            myAdapter.notifyDataSetChanged();
        });

        myListView.setOnItemLongClickListener((p, b, pos, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Do you want to delete this?")
                    .setMessage("The selected row is: " + pos+". " + "The database id is:" + id)
                    .setPositiveButton("Yes", (click, arg) -> {
                        list.remove(pos);
                        myAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", (click, arg) -> {
                    })
                    .create().show();
            return true;
        });

        Button previousButton=findViewById(R.id.previousButton);
        previousButton.setOnClickListener(click -> {
            finish();
        });
    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position).msgType;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            if (list.get(position).isSendOrReceive()) {
                View newView1 = inflater.inflate(R.layout.sendmessage, parent, false);
                EditText messageTyped1 = newView1.findViewById(R.id.sendmessage);
                messageTyped1.setText(getItem(position).toString());
                return newView1;
            } else {
                View newView2 = inflater.inflate(R.layout.message, parent, false);
                EditText messageTyped2 = newView2.findViewById(R.id.receivemessage);
                messageTyped2.setText(getItem(position).toString());
                return newView2;
            }
        }
    }
}



