package com.example.dil.firebaseauthdemo;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button btnLogin;
    ProgressDialog progDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        progDialog = new ProgressDialog(this);

        btnLogin = findViewById(R.id.btnLoging);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityLogin();
            }
        });
    }

    private void activityLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this,"Enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"Enter password",Toast.LENGTH_LONG).show();
            return;
        }
        progDialog.setMessage("Loging......");
        progDialog.show();

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Loging successfull",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this,"Login Error...",Toast.LENGTH_LONG).show();
                }
                progDialog.dismiss();
            }
        });
    }
}
