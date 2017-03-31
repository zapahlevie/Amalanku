package com.avengers.amalku.amalanku;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    ImageButton imgb;
    EditText edit1;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit1 = (EditText) findViewById(R.id.editText);
        button1 =(Button) findViewById(R.id.button);
        button1.setEnabled(false);
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

    public void openMain(View view) {
        edit1 = (EditText) findViewById(R.id.editText);
        Intent i = new Intent(LoginActivity.this, MenuActivity.class);
        Bundle b = new Bundle();
        b.putString("username", edit1.getText().toString());
        i.putExtras(b);
        startActivity(i);
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
                ImageButton imgb = (ImageButton) findViewById(R.id.imageButton);
                // Set the Image in ImageView after decoding the String
                imgb.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

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
