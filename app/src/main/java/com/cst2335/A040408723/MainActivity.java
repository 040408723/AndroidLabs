package com.cst2335.A040408723;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            EditText inputAddress = findViewById(R.id.email2);

            SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String s1 = sh.getString("emailAddress", "");
            inputAddress.setText(s1);
            SharedPreferences.Editor myEdit=sh.edit();
            //myEdit.commit();

            Button button1=findViewById(R.id.button1);

            button1.setOnClickListener((click) ->{
                Intent goToProfile=new Intent(MainActivity.this, ProfileActivity.class);
                goToProfile.putExtra("EMAIL",s1);
                startActivity(goToProfile);
            });
        }

    protected void onPause () {
        super.onPause();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit=sh.edit();
        myEdit.apply();

    }

}