package com.example.mycloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
// Youtube video to create recycle view: https://www.youtube.com/watch?v=M8sKwoVjqU0
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private MyAdapter myAdapter;
    private ArrayList<Item> list;
    private ImageButton addItemButton;
    private TextView title;
    private ImageButton logout;
    private FirebaseAuth mAuth;
    private ImageButton searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
       clickOnAddItem();
       clickOnSearchItems();
       clickOnTitleBackMainActivity();
       clickOnLogOut();

       // add Item to recycleView
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Search by category
                String searchType = getIntent().getStringExtra("searchitemType");
                String searchColor = getIntent().getStringExtra("searchitemColor");
                //String searchBrand = getIntent().getStringExtra("searchitemBrand");
                //String searchPrice = getIntent().getStringExtra("searchitemPrice");
                if (searchType == null){searchType ="";};
                if (searchColor == null){searchColor ="";};

                    Log.d("main Activity", "Search  T parameters are: @@@@@@@@@@@@@@@@"+ searchType+"search color type :@@@@@@@@@@   "+searchColor);
                for (DataSnapshot dataSnapshot: snapshot.getChildren() ){
                    Item item = dataSnapshot.getValue(Item.class);
                    item.setUid(dataSnapshot.getKey());
                    if (mAuth.getUid().toString().equals(item.getUserID())) {
                        if (!searchType.isEmpty()) {
                            if (searchType.equals(item.getType())){
                                Log.d("Equals?", "Searchtype   ---" + searchType+"--    Item type ----"+item.getType());
                                Log.d("Equals?", "searchColor "+searchColor);
                                if (!searchColor.isEmpty()){
                                    if (searchColor.equals(item.getColor())){
                                        list.add(item);
                                    }
                                    else{
                                        Log.d("MainActivity", "Equal(Type) and searchColor equals nothing"  );
                                    }
                                } else{
                                        list.add(item);
                                }
                            }
                        }

                        else { // searchType == ""
                            if (searchColor != "") {
                                if (searchColor.equals(item.getColor())) {
                                    list.add(item);
                                }

                            } else { list.add(item); }
                        }
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init(){

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.itemlist);
        database = FirebaseDatabase.getInstance().getReference("Items");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        // add item button
        addItemButton= findViewById(R.id.main_additem);
        //search item button
        searchButton = findViewById(R.id.main_searchitem);
        // click on main title
        title = findViewById(R.id.main_title);
        // click on logout
        logout = findViewById(R.id.main_logout);
        // click on main title
        title = findViewById(R.id.main_title);
        myAdapter= new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
    }

    private void clickOnAddItem(){
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Clicked on add item");
                Intent intent = new Intent(getApplicationContext(),AddItem.class);
                startActivity(intent);
            }
        });
    }

    private void clickOnSearchItems(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Clicked on search item");
                Intent intent = new Intent(getApplicationContext(),SearchItem.class);
                startActivity(intent);
            }
        });
    }

    private void clickOnTitleBackMainActivity(){
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickOnLogOut(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(),SignInPage.class);
                startActivity(intent);
            }
        });

    }

}