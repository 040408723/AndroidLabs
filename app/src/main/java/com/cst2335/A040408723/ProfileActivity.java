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
import android.widget.TextView;




public class ProfileActivity extends AppCompatActivity {

    ImageView imgView=findViewById(R.id.imagebutton1);
    public static final String TAG="PROFILE_ACTIVITY";

    ActivityResultLauncher<Intent> myPictureTakerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    Bitmap imgbitmap = (Bitmap) data.getExtras().get("data");

                    imgView.setImageBitmap(imgbitmap);
                } else if (result.getResultCode() == Activity.RESULT_CANCELED)
                    Log.i(TAG, "User refused to capture a picture.");
            });

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent fromMain=getIntent();
        String msg=fromMain.getStringExtra("EMAIL");


        TextView emailEditText=(TextView)findViewById(R.id.email2);
        emailEditText.setText(msg);

        ImageButton btn = findViewById(R.id.imagebutton1);


        btn.setOnClickListener(view -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ActivityResultLauncher<Intent> myPictureTakerLauncher = null;
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                assert false;
                myPictureTakerLauncher.launch(takePictureIntent);
            }
        });

        Log.e(TAG, "onCreate");
    }



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

