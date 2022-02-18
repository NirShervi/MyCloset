package com.example.mycloset;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class AddItem extends AppCompatActivity {
    private EditText typeEditText;
    private EditText colorEditText;
    private EditText brandEditText;
    private EditText priceEditText;
    private Button addItemButton;
    private DatabaseReference itemsDbRef;
    private FirebaseAuth mAuth;
    private ImageButton uploadPicture;
    private String imageName = "";
    private FirebaseStorage storage;
    private Uri selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        init();
        clickOnAddItemButton();
        clickOnUploadPicture();
    }

    private void choosePicture(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            selectedImage=data.getData();
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                uploadPicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadFirebase(Uri selectedImage) {
        StorageReference imageRef=storage.getReference().child("images").child(imageName);

        uploadPicture.setDrawingCacheEnabled(true);
        uploadPicture.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) uploadPicture.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference fileRef = imageRef.child(imageName);
        StorageTask storageTask = fileRef.putFile(selectedImage)
                .addOnSuccessListener(taskSnapshot -> {
                    Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uri.isComplete()) ;
                    insertItemData(uri.getResult().toString());
                });
    }

    private void insertItemData(String imageUrl){

        String userID = mAuth.getUid().toString();
        String type = typeEditText.getText().toString();
        String color = colorEditText.getText().toString();
        String brand = brandEditText.getText().toString();
        String price = priceEditText.getText().toString();
        String url = imageUrl;
        Item item = new Item(" ",userID,type,color,brand,price,url);

        itemsDbRef.push().setValue(item);

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }

    private void init(){
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        typeEditText = findViewById(R.id.additem_type);
        colorEditText = findViewById(R.id.additem_color);
        brandEditText = findViewById(R.id.additem_brand);
        priceEditText = findViewById(R.id.additem_price);
        addItemButton = findViewById(R.id.additem_Button);
        itemsDbRef = FirebaseDatabase.getInstance().getReference().child("Items");
        storage = FirebaseStorage.getInstance();
        imageName= UUID.randomUUID().toString();
        uploadPicture = findViewById(R.id.additem_uploadpicture);
    }

    private void clickOnAddItemButton(){
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImage != null){
                    uploadFirebase(selectedImage);
                }
            }
        });
    }

    private void clickOnUploadPicture(){
        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
    }
}