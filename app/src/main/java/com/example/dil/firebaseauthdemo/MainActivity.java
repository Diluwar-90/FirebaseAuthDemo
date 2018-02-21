package com.example.dil.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        buttonSignup =  findViewById(R.id.buttonSignup);
        progressDialog = new ProgressDialog(this);
        TextView tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Registering......");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Registration is successful",Toast.LENGTH_LONG).show();
                    /*editTextEmail.setText("");
                    editTextPassword.setText("");*/
                    Intent i=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this,"Registration Error..",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}
