package com.cst2335.A040408723;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button GreatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add onClickListener
        GreatButton = findViewById(R.id.button1);
        GreatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //toast message translate
                String toastMessage=MainActivity.this.getResources().getString(R.string.toast_message);
                Toast.makeText(MainActivity.this, toastMessage,Toast.LENGTH_LONG).show();
            }
        });


        //add a switch change listener
        Switch switchBox = findViewById(R.id.switchbox1);
        switchBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean b) {
                switchBox.setChecked(b);
                if (switchBox.isChecked()) {
                    Snackbar.make(switchBox, "The switch is on", Snackbar.LENGTH_LONG).setAction("Undo", click -> cb.setChecked(!b)).show();
                } else {
                    Snackbar.make(switchBox, "The switch is off", Snackbar.LENGTH_LONG).setAction("Undo", click -> cb.setChecked(!b)).show();
                }
            }

        });
    }
}