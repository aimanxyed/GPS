package com.mediamonitors.gps;


import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class CameraActivity extends AppCompatActivity {
    ImageView chooseImage;
    Button chooseImageButton;
    EditText message;
    Button nextButton;
    String imageName=  UUID.randomUUID().toString() + ".jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        chooseImageButton= (Button)findViewById(R.id.selectButton);
        message =(EditText) findViewById(R.id.messagEditText);
        nextButton = (Button) findViewById(R.id.nextButton);
    }
    public void getPhoto() {
        Intent intent =  new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);

    }
    public void chooseImage(View view)
    {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            getPhoto();
        }
    }



    @Override
    protected void  onActivityResult(int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage = data.getData();


        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                // ImageView imageView= findViewById(R.id.imageView);
                chooseImage= (ImageView) findViewById(R.id.galleryImageView);

                chooseImage.setImageBitmap(bitmap);

            } catch ( Exception  e) {
                e.printStackTrace();
            }

        }

    }
    @Override
    public  void  onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto();
            }
        }
    }
    public void nextClicked(View view ) {
        // Get the data from an ImageView as bytes
        chooseImage.setDrawingCacheEnabled(true);
        chooseImage.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) chooseImage.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();



    }

}
