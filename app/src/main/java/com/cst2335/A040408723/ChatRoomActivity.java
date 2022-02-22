package com.cst2335.A040408723;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;


public class ChatRoomActivity extends AppCompatActivity {

    public static final String TAG="ChatRoom_ACTIVITY";

    MyOpenHelper myOpener;
    SQLiteDatabase theDatabase;

    Button sendButton;
    Button receiveButton;
    EditText typeMessage;

    ArrayList<Message> list = new ArrayList<>();
    MyListAdapter myAdapter;

    public class Message {

        private String msgType;
        boolean sendOrReceive;
        long id;

        public Message(boolean sendOrReceive, String msgType, long _id) {
            this.msgType = msgType;
            this.sendOrReceive = sendOrReceive;
            this.id = _id;
        }

        public boolean isSendOrReceive() {
            return sendOrReceive;
        }

        public long getId() {
            return id;
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        myOpener = new MyOpenHelper(this);
        theDatabase = myOpener.getWritableDatabase();

        @SuppressLint("Recycle") Cursor results = theDatabase.rawQuery("Select * from "
                + MyOpenHelper.TABLE_NAME + ";", null);

        int idIndex = results.getColumnIndex(MyOpenHelper.COL_ID);
        int messageIndex = results.getColumnIndex(MyOpenHelper.COL_MESSAGE);

        while (results.moveToNext()) {

            int id = results.getInt(idIndex);
            String message = results.getString(messageIndex);

            list.add(new Message(true, message, id));
            list.add(new Message(false, message, id));
        }

        sendButton = findViewById(R.id.buttonSend);
        receiveButton = findViewById(R.id.buttonReceive);
        typeMessage = findViewById(R.id.edittext1);

        ListView myListView = findViewById(R.id.listview1);
        myListView.setAdapter(myAdapter = new MyListAdapter());

        sendButton.setOnClickListener(click -> {
            String typeText = typeMessage.getText().toString();

            ContentValues newRow = new ContentValues();
            newRow.put(MyOpenHelper.COL_MESSAGE, typeText);
            newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 1);
            long id = theDatabase.insert(MyOpenHelper.TABLE_NAME, null, newRow);

            Message newMsg = new Message(true, typeText, id);

            list.add(newMsg);
            typeMessage.setText("");
            myAdapter.notifyDataSetChanged();
        });

        receiveButton.setOnClickListener(click -> {
            String typeText = typeMessage.getText().toString();

            ContentValues newRow = new ContentValues();
            newRow.put(MyOpenHelper.COL_MESSAGE, typeText);
            newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 1);
            long id = theDatabase.insert(MyOpenHelper.TABLE_NAME, null, newRow);

            Message newMsg = new Message(false, typeText, id);

            list.add(newMsg);
            typeMessage.setText("");
            myAdapter.notifyDataSetChanged();
        });

        myListView.setOnItemLongClickListener((p, b, pos, id) -> {

            Message whatWasClicked = list.get(pos);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Do you want to delete this?")
                    .setMessage("The selected row is: " + pos + ". " + "The database id is:" + id)
                    .setPositiveButton("Yes", (click, arg) -> {
                        list.remove(pos);
                        theDatabase.delete(MyOpenHelper.TABLE_NAME,
                                MyOpenHelper.COL_ID + "=?", new String[]{Long.toString(whatWasClicked.getId())});
                        myAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", (click, arg) -> {
                    })
                    .create().show();
            return true;
        });

        Button previousButton = findViewById(R.id.previousButton);
        previousButton.setOnClickListener(click -> {
            finish();
        });

        Log.i(TAG,"this is a test");
        printCursor(results,1);
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

        public void printCursor (Cursor c,int version){

            int numberColumns = c.getColumnCount();
            String[] nameColumns = c.getColumnNames();
            int numberRows = c.getCount();

            c.moveToFirst();

            while (!c.isAfterLast()) {

                int typeIndex = c.getColumnIndex(MyOpenHelper.COL_MESSAGE);
                int idColIndex = c.getColumnIndex(MyOpenHelper.COL_ID);
                int sOrRColIndex=c.getColumnIndex(MyOpenHelper.COL_SEND_RECEIVE);

                String type = c.getString(typeIndex);
                long id = c.getLong(idColIndex);
                int sOrR=c.getInt(sOrRColIndex);

                String rowValues=String.format("ID="+id+", Message="+type+", SendOrReceive="+sOrR);
                Log.i("ROW OF RESULTS", rowValues );

                c.moveToNext();
            }

            Log.i("DATABASE VERSION NUMBER: ", Integer.toString(version));
            Log.i("NUMBER OF COLUMNS: ", Integer.toString(numberColumns));
            Log.i("COLUMN NAMES: ", Arrays.toString(nameColumns));
            Log.i("NUMBER OF ROWS: ", Integer.toString(numberRows));

        }

}





