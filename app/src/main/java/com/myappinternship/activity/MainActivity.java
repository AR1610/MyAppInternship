package com.myappinternship.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myappinternship.R;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin, btnSend;
    ImageView imgLogo;
    TextView tvfp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edt_email);
        btnLogin = findViewById(R.id.btn_login);
        imgLogo = findViewById(R.id.img_logo);
        btnSend = findViewById(R.id.btn_send);
        tvfp = findViewById(R.id.tv_fp);
        edtPassword = findViewById(R.id.edt_password);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://myappinternship-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Register");


        tvfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View fpView = layoutInflater.inflate(R.layout.raw_fp, null);
                EditText edtFPEmail = fpView.findViewById(R.id.edt_email);
                Button btnSubmit = fpView.findViewById(R.id.btn_submit);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(fpView);
                alertDialog.show();

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Submit", Toast.LENGTH_SHORT).show();

                        if (alertDialog.isShowing()) {

                            alertDialog.dismiss();
                        }
                    }
                });
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();
                if (strEmail.equals("")) {
                    edtEmail.setError("Enter Email ID");
                    Toast.makeText(MainActivity.this, "Enter Email ID ", Toast.LENGTH_SHORT).show();
                } else if (!strEmail.matches(emailPattern)) {
                    Toast.makeText(MainActivity.this, "Enter valid EmailId ", Toast.LENGTH_SHORT).show();
                } else if (strPassword.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (strPassword.length() < 8) {
                    Toast.makeText(MainActivity.this, "Enter valid Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Email ID is  " + strEmail, Toast.LENGTH_SHORT).show();
                    imgLogo.setImageResource(R.drawable.icon_2);

                    firebaseAuth.signInWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                String strUID = firebaseAuth.getUid();

                                databaseReference.child(strUID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        RegisterModel registerModel = dataSnapshot.getValue(RegisterModel.class);
                                        String loginEmail = registerModel.getUser_email();

                                        SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_Internship", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("KEY_PREF_EMAIL", loginEmail);
                                        editor.commit();

                                        // Explicit Intent
                                        Intent i = new Intent(MainActivity.this, NavHomeActivity.class);
                                        i.putExtra("KEY_EMAIL", loginEmail);
                                        startActivity(i);
                                        finish();
                                        // Over Explicit Intent
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }
}