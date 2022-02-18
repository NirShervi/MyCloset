package com.example.mycloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchItem extends AppCompatActivity {
    private EditText typeEditText;
    private EditText colorEditText;
    private EditText brandEditText;
    private EditText priceEditText;
    private Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);
        init();
        clickOnSearchButton();
    }

    private void init(){

        typeEditText = findViewById(R.id.search_type);
        colorEditText = findViewById(R.id.search_color);
        brandEditText = findViewById(R.id.search_brand);
        priceEditText = findViewById(R.id.search_price);
        searchButton = findViewById(R.id.search_Button);
    }

    private void clickOnSearchButton(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("searchitemType", typeEditText.getText().toString());
                intent.putExtra("searchitemColor", colorEditText.getText().toString());
                intent.putExtra("searchitemBrand", brandEditText.getText().toString());
                intent.putExtra("searchitemPrice", priceEditText.getText().toString());
                Log.d("SearchItem", "Search  T parameters are: @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+ typeEditText.getText().toString());
                startActivity(intent);
            }
        });
    }

}