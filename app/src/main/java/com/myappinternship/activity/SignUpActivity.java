package com.myappinternship.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myappinternship.R;

public class SignUpActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword,edtFname,edtLname;
    Button btnRegister;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtFname = findViewById(R.id.edt_Fname);
        edtLname = findViewById(R.id.edt_Lname);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnRegister = findViewById(R.id.btn_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://myappinternship-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Register");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strFn = edtFname.getText().toString();
                String strLn = edtLname.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){


                            String uID = firebaseAuth.getUid();
                            RegisterModel  registerModel = new RegisterModel();
                            registerModel.setUser_id(uID);
                            registerModel.setUser_firstName(strFn);
                            registerModel.setUser_lastName(strLn);
                            registerModel.setUser_email(strEmail);
                            registerModel.setUser_password(strPassword);

                            databaseReference.child(uID).setValue(registerModel);

                            Intent  i = new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }

                    }
                });




            }
        });





    }
}