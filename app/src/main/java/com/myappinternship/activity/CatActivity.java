package com.myappinternship.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myappinternship.R;
import com.myappinternship.models.CategoryModel;

public class CatActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edtCatName, edtCatDes;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        // Database
        firebaseDatabase = FirebaseDatabase.getInstance("https://myappinternship-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Category");

        edtCatName = findViewById(R.id.edt_name);
        edtCatDes = findViewById(R.id.edt_description);

        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strCatName = edtCatName.getText().toString();
                String strCatDesc = edtCatDes.getText().toString();
                String strid = databaseReference.push().getKey();

                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCat_id(strid);
                categoryModel.setCat_name(strCatName);
                categoryModel.setCat_description(strCatDesc);

                databaseReference.child(strid).setValue(categoryModel);

                edtCatName.setText("");
                edtCatDes.setText("");

            }
        });


    }
}