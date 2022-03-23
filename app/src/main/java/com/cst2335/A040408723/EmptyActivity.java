package com.cst2335.A040408723;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


public class EmptyActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        Bundle bundle=getIntent().getBundleExtra("MessageTrans");

        DetailsFragment fragment=new DetailsFragment();
        fragment.setArguments(bundle);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(R.id.flbox2,fragment);
        ft.commit();
    }
}




   
