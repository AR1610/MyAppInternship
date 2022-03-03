package com.myappinternship.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

public class CatDetailsActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edtCatName, edtCatDes;
    Button btnUpdate, btnDelete;
    CircleImageView circleImageView;

    FirebaseStorage firebaseStorage;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_details);

        // Database
        firebaseDatabase = FirebaseDatabase.getInstance("https://myappinternship-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Category");

        firebaseStorage = FirebaseStorage.getInstance();
        circleImageView = findViewById(R.id.img_dp);
        btnDelete = findViewById(R.id.btn_delete);
        edtCatName = findViewById(R.id.edt_name);
        edtCatDes = findViewById(R.id.edt_description);
        btnUpdate = findViewById(R.id.btn_update);

        Intent i = getIntent();
        String strId = i.getStringExtra("KEY_ID");
        String strName = i.getStringExtra("KEY_NAME");
        String strDescription = i.getStringExtra("KEY_DESC");
        String strURL = i.getStringExtra("KEY_URL");

        Glide.with(this).load(strURL).into(circleImageView);
        edtCatName.setText(strName);
        edtCatDes.setText(strDescription);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View fpView = layoutInflater.inflate(R.layout.raw_dp, null);
                ImageView imgGallery = fpView.findViewById(R.id.img_gallery);
                ImageView imgCamera = fpView.findViewById(R.id.img_camera);
                AlertDialog.Builder builder = new AlertDialog.Builder(CatDetailsActivity.this);
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(fpView);
                alertDialog.show();
                imgGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CatDetailsActivity.this, "Submit", Toast.LENGTH_SHORT).show();

                        if (alertDialog.isShowing()) {

                            alertDialog.dismiss();
                        }
                        Intent i = new Intent();
                        i.setType("image/*");
                        i.setAction(Intent.ACTION_PICK);
                        startActivityForResult(i, 11);
                    }
                });


            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child(strId).removeValue();

                Intent i = new Intent(CatDetailsActivity.this, CatDisplayActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strCatName = edtCatName.getText().toString();
                String strCatDesc = edtCatDes.getText().toString();

                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCat_id(strId);
                categoryModel.setCat_name(strCatName);
                categoryModel.setCat_description(strCatDesc);
                categoryModel.setCat_url(imageUrl);
                databaseReference.child(strId).setValue(categoryModel);
                edtCatName.setText("");
                edtCatDes.setText("");


                Intent i = new Intent(CatDetailsActivity.this, CatDisplayActivity.class);
                startActivity(i);
              //  finish();

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            circleImageView.setImageURI(uri);
            uploadFile(uri);
        }
       /* if (requestCode == 12){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            circleImageView.setImageBitmap(bitmap);
        }*/
    }


    //this method will upload the file
    private void uploadFile(Uri uri) {
        //if there is a file to upload
        if (uri != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            StorageReference storageReference = firebaseStorage.getReference().child("images/" + UUID.randomUUID().toString() + ".png");
            storageReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri downloadUri) {

                                    imageUrl = downloadUri.toString();
                                    Log.e("TAG", "onSuccess: " + imageUrl);

                                }
                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();
                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }


}