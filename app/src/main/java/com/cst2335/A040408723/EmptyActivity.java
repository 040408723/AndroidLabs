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
    boolean isTablet = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        isTablet = findViewById(R.id.flbox1) != null;

        
        if(savedInstanceState==null) {

            DetailsFragment firstFragment = new DetailsFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setReorderingAllowed(true);
            ft.add(R.id.details, firstFragment);
            ft.commit();
            if (isTablet) {
                DetailsFragment secondFragment=new DetailsFragment();
                Bundle args=new Bundle();
                args.putInt("position",0);
                secondFragment.setArguments(args);

                FragmentTransaction ft2=getSupportFragmentManager().beginTransaction();
                ft2.setReorderingAllowed(true);
                ft2.add(R.id.details,secondFragment);
                ft2.commit();
            }
        }

        Button button1=findViewById(R.id.hide);
        button1.setOnClickListener(view -> {

            Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.details);
            if(fragment!=null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(fragment)
                        .commit();
            }

        });
    }
}

   
