package com.example.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupScreen extends AppCompatActivity {

    private ImageView log;
    private TextInputLayout username, password;
    private TextView create;
    private Button reg;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        log = findViewById(R.id.logo);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        create = findViewById(R.id.create);

        reg = findViewById(R.id.register_button);

        firebaseAuth = FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupScreen.this,LoginScreen.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void registeruser() {



        String email,pass;
        email = username.getEditText().toString();
        pass = password.getEditText().toString();

        if (email.isEmpty()||pass.isEmpty()){
            Toast.makeText(this, "Enter the credentials...........", Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignupScreen.this, "Registered successfully.....", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(SignupScreen.this,LoginScreen.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(SignupScreen.this, "Registration failed please try again later.......", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}