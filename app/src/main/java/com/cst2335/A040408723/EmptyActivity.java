package com.cst2335.A040408723;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        DetailsFragment secondFragment=new DetailsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.flbox1,secondFragment)
                .commit();

    }
}