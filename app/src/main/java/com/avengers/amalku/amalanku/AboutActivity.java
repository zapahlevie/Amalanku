package com.avengers.amalku.amalanku;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean("dark_theme", false);
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
        }
        setContentView(R.layout.activity_about);
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setText("Amalanku\n" +
                "Current Version\n" +
                "v0.1.7 (beta)\n" +
                "\n" +
                "Description\n" +
                "Aplikasi ini terpaksa dibuat demi memenuhi tuntutan tugas mata kuliah Pengembangan Perangkat Lunak.\n" +
                "\n" +
                "Developer\n" +
                "Hamba Allah\n" +
                "\n" +
                "Contact Us\n" +
                "www.avengers-dev.com\n" +
                "(021)123456789");
    }
}
