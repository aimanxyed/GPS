package com.mediamonitors.gps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class splashActivity extends AppCompatActivity {
    ImageView logoImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logoImageView = findViewById(R.id.imageView);
        logoImageView.animate().translationYBy(-6).setDuration(300);
        logoImageView.getCropToPadding();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent (splashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);




    }
}
