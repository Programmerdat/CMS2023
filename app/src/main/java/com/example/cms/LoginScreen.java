package com.example.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginScreen extends AppCompatActivity {


    private ImageView log;
    private TextInputLayout username, password;
    private TextView create, changepassword;
    private Button login;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        log = findViewById(R.id.logoo);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        create = findViewById(R.id.create);
        changepassword = findViewById(R.id.change_password);
        login = findViewById(R.id.login_button);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginScreen.this,SignupScreen.class);
                startActivity(i);
                finish();
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginScreen.this,ForgetPasswordScreen.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void auth() {

        String email,pass;
        email = username.getEditText().toString();
        pass = password.getEditText().toString();

        if (email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Please enter the credentials.........", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginScreen.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(LoginScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginScreen.this, "Login Failed Please check the credentials", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}