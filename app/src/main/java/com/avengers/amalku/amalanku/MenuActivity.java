package com.avengers.amalku.amalanku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tv1 = (TextView) findViewById(R.id.textView);
        Bundle b = getIntent().getExtras();
        String username = "User";
        if(b != null)
            username = b.getString("username");
        tv1.setText("Assalamu'alaikum, "+username);
    }
}