package com.example.AlumniTracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText loginEmail, loginPassword;
    Button loginBtn;
    //declare firebaseAuth auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialise firebase app
        FirebaseApp.initializeApp(this);
        //initialise firebase auth
        mAuth=FirebaseAuth.getInstance();

        //find views
        loginEmail=findViewById(R.id.loginEmail);
        loginPassword=findViewById(R.id.loginPassword);
        loginBtn=findViewById(R.id.loginBtn);
        //set onclick listener for login
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        //store user inputs in variables
        String login_email=loginEmail.getText().toString().trim();
        String login_password=loginPassword.getText().toString().trim();

        //validate user inputs
        if(TextUtils.isEmpty(login_email)){
            Toast.makeText(this, "Email can not be empty", Toast.LENGTH_LONG).show();
        }else {
            run(login_email, login_password);
        }
        if(TextUtils.isEmpty(login_password)){
            Toast.makeText(this, "Password should have a minimum of five characters", Toast.LENGTH_LONG).show();
        }else {
            run(login_email, login_password);
        }
    }

    private void run(String login_email, String login_password) {
        //login a user
        mAuth.signInWithEmailAndPassword(login_email, login_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (!task.isSuccessful()){
//                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
//                }else {
                    updateUi();
//                }
            }
        });
    }

    private void updateUi() {
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View v){
        Intent intent=new Intent(LoginActivity.this, ForgotPassword.class);
        startActivity(intent);
    }
    public void CreateAccount(View v){
        Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }


}