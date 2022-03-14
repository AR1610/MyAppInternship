package com.myappinternship.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myappinternship.R;
import com.myappinternship.models.CategoryModel;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubCatActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edtCatName, edtCatDes;
    Button btnAdd, btnDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_cat);

        // Database
        firebaseDatabase = FirebaseDatabase.getInstance("https://myappinternship-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("SubCategory");
        edtCatName = findViewById(R.id.edt_name);
        edtCatDes = findViewById(R.id.edt_description);
        btnAdd = findViewById(R.id.btn_add);
        btnDisplay = findViewById(R.id.btn_display);
        SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_Internship", MODE_PRIVATE);
        String catID = sharedPreferences.getString("KEY_PREF_CATID", "");


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strCatName = edtCatName.getText().toString();
                String strCatDesc = edtCatDes.getText().toString();
                String strid = databaseReference.push().getKey();

                SubCategoryModel categoryModel = new SubCategoryModel();
                categoryModel.setSubCatID(strid);
                categoryModel.setSubCatName(strCatName);
                categoryModel.setSubCatDescription(strCatDesc);
                categoryModel.setCatID(catID);
                databaseReference.child(strid).setValue(categoryModel);
                edtCatName.setText("");
                edtCatDes.setText("");

            }
        });


    }

}