package com.myappinternship.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myappinternship.R;
import com.myappinternship.adapters.BookAdapter;
import com.myappinternship.adapters.CategoryAdapter;
import com.myappinternship.models.CategoryModel;
import com.myappinternship.models.SliderItem;

import java.util.ArrayList;

public class CatDisplayActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener  {

    private ListView listView;
    private ArrayList<CategoryModel> categoryModelArrayList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    CategoryAdapter bookAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_display);

        listView = findViewById(R.id.list_view);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

        categoryModelArrayList = new ArrayList<CategoryModel>();
        firebaseDatabase = FirebaseDatabase.getInstance("https://myappinternship-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Category");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    CategoryModel categoryModel = dataSnapshot1.getValue(CategoryModel.class);
                    categoryModelArrayList.add(categoryModel);
                }


                 bookAdapter = new CategoryAdapter(CatDisplayActivity.this,categoryModelArrayList);
                listView.setAdapter(bookAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        bookAdapter.filter(text);
        return false;
    }

}