package com.avengers.amalku.amalanku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    EditText edit1;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean("dark_theme", false);
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
        }
        setContentView(R.layout.activity_user);

        edit1 = (EditText) findViewById(R.id.editText2);
        button1 =(Button) findViewById(R.id.button6);

        Bundle b = getIntent().getExtras();
        String username = "User";
        if(b != null)
            username = b.getString("username");
        edit1.setText(username);

        edit1.setFocusableInTouchMode(true);
        edit1.requestFocus();
        edit1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    button1.performClick();
                    return true;
                }
                return false;
            }
        });
        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableSubmitIfReady();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void saveUser(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda yakin ingin keluar?")
                .setTitle("Konfirmasi")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        // CONFIRM
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        // CANCEL
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alert =  builder.create();
        alert.show();
    }
    public void openGallery(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    public void enableSubmitIfReady() {
        edit1 = (EditText) findViewById(R.id.editText);
        button1 =(Button) findViewById(R.id.button);
        boolean isReady = edit1.getText().toString().length()>0;
        button1.setEnabled(isReady);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageButton imgb = (ImageButton) findViewById(R.id.imageButton2);
                // Set the Image in ImageView after decoding the String
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), imgDecodableString);
                roundedBitmapDrawable.setCircular(true);
                imgb.setImageDrawable(roundedBitmapDrawable);

            } else {
                Toast.makeText(this, "Anda belum memilih gambar.",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Terjadi kesalahan.", Toast.LENGTH_LONG)
                    .show();
        }

    }
}
