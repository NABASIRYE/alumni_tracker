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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    //Declare views
    TextInputEditText forgotEmail;
    Button forgotPasswordBtn;

    //Declare firebase auth
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //initialise firebase app
        FirebaseApp.initializeApp(this);
        //initialise firebase auth
        mAuth= FirebaseAuth.getInstance();

        //find views
        forgotEmail=findViewById(R.id.forgotEmail);
        forgotPasswordBtn=findViewById(R.id.forgotPasswordBtn);

        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword();
            }
        });
    }
        //forgot password
    private void forgotPassword() {
        //pick user email input
        String forgot_email=forgotEmail.getText().toString().trim();
        
        //check if email is not empty
        if (TextUtils.isEmpty(forgot_email)){
            Toast.makeText(this, "Email can not be empty", Toast.LENGTH_LONG).show();
        }else{
            run(forgot_email);
        }
    }

    private void run(String forgot_email) {
        //reset user's password
        mAuth.sendPasswordResetEmail(forgot_email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
//                if (!task.isSuccessful()){
//                    Toast.makeText(ForgotPassword.this, "Password reset failed, please try again later", Toast.LENGTH_LONG).show();
//                }else {
                    Toast.makeText(ForgotPassword.this, "A reset link has been sent to your email", Toast.LENGTH_LONG).show();
                    updateUi();

//                }
            }
        });
    }

    private void updateUi() {
        Intent intent =new Intent(ForgotPassword.this, LoginActivity.class);
        startActivity(intent);
    }

    public void rememberedPassword(View v){
        Intent intent=new Intent(ForgotPassword.this, LoginActivity.class);
        startActivity(intent);
    }

}