package com.avengers.amalku.amalanku;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class NotifActivity extends AppCompatActivity {

    ModelNotif[] modelItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean("dark_theme", false);
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
        }
        setContentView(R.layout.activity_notif);

        modelItems = new ModelNotif[10];
        modelItems[0] = new ModelNotif("Sholat Subuh", 1);
        modelItems[1] = new ModelNotif("Sholat Dzuhur", 1);
        modelItems[2] = new ModelNotif("Sholat Ashar", 1);
        modelItems[3] = new ModelNotif("Sholat Maghrib", 1);
        modelItems[4] = new ModelNotif("Sholat Isya", 1);
        modelItems[5] = new ModelNotif("Sholat Dhuha", 0);
        modelItems[6] = new ModelNotif("Sholat Tahajjud", 0);
        modelItems[7] = new ModelNotif("Sholat Rawatib", 0);
        modelItems[8] = new ModelNotif("Tilawah Al-Quran", 0);
        modelItems[9] = new ModelNotif("Sedekah", 0);

        CustomAdapterNotif adapter = new CustomAdapterNotif(NotifActivity.this, modelItems);
        ListView listView = (ListView) findViewById(R.id.listnotif);
        listView.setAdapter(adapter);

    }
}
