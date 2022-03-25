package com.cst2335.A040408723;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;



public class ProfileActivity extends AppCompatActivity {


    public static final String TAG="PROFILE_ACTIVITY";
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton btn=findViewById(R.id.imagebutton1);

            btn.setOnClickListener(view ->{
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    myPictureTakerLauncher.launch(takePictureIntent);
                }
            });
        Button button=findViewById(R.id.button1);
        button.setOnClickListener(v->{
            Intent goToRoom=new Intent(ProfileActivity.this,ChatRoomActivity.class);
            startActivity(goToRoom);
        });

        Log.e(TAG, "onCreate");

        Intent fromMain=getIntent();
        String emailInfo=fromMain.getStringExtra("EMAIL");
        EditText emailEditText=findViewById(R.id.email22);
        emailEditText.setText(emailInfo);

        Button b=findViewById(R.id.toolbar1);
        b.setOnClickListener(v->{
            Intent goToToolBar=new Intent(ProfileActivity.this,TestToolbar.class);
            startActivity(goToToolBar);
        });

    }


    ActivityResultLauncher<Intent> myPictureTakerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            this::onActivityResult);

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

    private void onActivityResult(ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            Bitmap imgbitmap = (Bitmap) data.getExtras().get("data");
            imgView=(ImageView) findViewById(R.id.imagebutton1);
            imgView.setImageBitmap(imgbitmap);
        } else if (result.getResultCode() == Activity.RESULT_CANCELED)
            Log.i(TAG, "User refused to capture a picture.");
    }
}

