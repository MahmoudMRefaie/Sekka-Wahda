package com.example.sekkawa7da;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sekkawa7da.ui.Login;

public class SplashScreen extends AppCompatActivity {

    private static int SPLACH_SCREEN = 5000;

    private ImageView logo;
    private TextView projName;
    Animation topAnim , bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        logo = findViewById(R.id.project_logo);
        projName = findViewById(R.id.project_name);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Animation splashAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);

        logo.startAnimation(topAnim);
        projName.startAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,Login.class);
                startActivity(intent);
                finish();
            }
        },SPLACH_SCREEN);

    }
}