package com.avengers.amalku.amalanku;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private static int splashInterval = 2000;

    ImageView ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean("dark_theme", false);
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        if(useDarkTheme){
            ib = (ImageView) findViewById(R.id.splashscreen);
            ib.setImageResource(R.drawable.splash2);
        }
        else {
            ib = (ImageView) findViewById(R.id.splashscreen);
            ib.setImageResource(R.drawable.splash);
        }

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                this.finish();
            }

            private void finish() {
                // TODO Auto-generated method stub

            }
        }, splashInterval);

    };

}