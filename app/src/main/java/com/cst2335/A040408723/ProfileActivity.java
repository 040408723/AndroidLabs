package com.cst2335.A040408723;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import android.widget.ImageButton;
import android.widget.ImageView;


public class ProfileActivity extends AppCompatActivity {


    public static final String TAG="PROFILE_ACTIVITY";
    ImageView imgView;

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        ImageButton btn = findViewById(R.id.imagebutton1);

        btn.setOnClickListener(view -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                myPictureTakerLauncher.launch(takePictureIntent);
            }
        });

        Log.e(TAG, "onCreate");
    }

    ActivityResultLauncher<Intent> myPictureTakerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    Bitmap imgbitmap = (Bitmap) data.getExtras().get("data");

                    imgView.setImageBitmap(imgbitmap);
                } else if (result.getResultCode() == Activity.RESULT_CANCELED)
                    Log.i(TAG, "User refused to capture a picture.");
            });

            protected void onStart () {
                super.onStart();
                Log.e(TAG, "onStart");
            }

            protected void onResume () {
                super.onResume();
                Log.e(TAG, "onResume");
            }

            protected void onPause () {
                super.onPause();
                Log.e(TAG, "onPause");
            }

            protected void onStop () {
                super.onStop();
                Log.e(TAG, "onStop");
            }

            protected void onDestroy () {
                super.onDestroy();
                Log.e(TAG, "onDestroy");
            }

}

