package com.example.AlumniTracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    //declare views
    TextInputEditText firstName, lastName, loginEmail, signPassword, workingStatus, alumnusPic;
    Button registerBtn;
    //declare firebase authentication
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //initialise firebase app
        FirebaseApp.initializeApp(this);
        //initialise firebase auth
        mAuth=FirebaseAuth.getInstance();

        //find views
        firstName=findViewById(R.id.firstName);
       lastName=findViewById(R.id.lastName);
        loginEmail=findViewById(R.id.loginEmail);
        signPassword=findViewById(R.id.signPassword);
        workingStatus=findViewById(R.id.workingStatus);
        alumnusPic=findViewById(R.id.alumnusPic);
        registerBtn=findViewById(R.id.registerBtn);

        //next step is to register a user to firebase
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    //signing up a user using firebase
    private void registerUser() {
        //get user input
        String signed_name=firstName.getText().toString().trim();
        String signed_last=lastName.getText().toString().trim();
        String signed_email=loginEmail.getText().toString().trim();
        String signed_password=signPassword.getText().toString().trim();
        String signed_status=workingStatus.getText().toString().trim();
        String signed_pic=alumnusPic.getText().toString().trim();

        //validate user to avoid submission of empty fields
        //TextUtils means input text fields
        if(TextUtils.isEmpty(signed_name)){
            Toast.makeText(this, "First Name can not be empty", Toast.LENGTH_LONG).show();
        }else {
            run(signed_name,signed_password);
        }
        if(TextUtils.isEmpty(signed_last)){
            Toast.makeText(this, "Last Name can not be empty", Toast.LENGTH_LONG).show();
        }else {
            run(signed_last,signed_password);
        }
        if(TextUtils.isEmpty(signed_email)){
            Toast.makeText(this, "Email can not be empty", Toast.LENGTH_LONG).show();
        }else {
            run(signed_email,signed_password);
        }
        if(signed_password.length()<5){
            Toast.makeText(this, "Password should have a minimum of five characters", Toast.LENGTH_LONG).show();
        }else {
            run(signed_password,signed_password);
        }
        if(TextUtils.isEmpty(signed_status)){
            Toast.makeText(this, "Working status can not be empty", Toast.LENGTH_LONG).show();
        }else {
            run(signed_status,signed_password);
        }
        if(TextUtils.isEmpty(signed_pic)){
            Toast.makeText(this, "Please attach a pic", Toast.LENGTH_LONG).show();
        }else {
            run(signed_pic,signed_password);
        }

    }

    private void run(String signed_email, String signed_password) {
        //lets create a user from the validated user inputs
        mAuth.createUserWithEmailAndPassword(signed_email, signed_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (!task.isSuccessful()){
//                    Toast.makeText(SignUpActivity.this, "Account creation failed", Toast.LENGTH_LONG).show();
//
//                }else {
                    updateUi();
//                }
            }
        });
    }

    private void updateUi() {
        Intent intent=new Intent(SignUpActivity.this, Log.class);
        startActivity(intent);
    }



    public void tryLogin(View view) {
        Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}