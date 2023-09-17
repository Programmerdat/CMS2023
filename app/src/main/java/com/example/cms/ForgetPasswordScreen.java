package com.example.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordScreen extends AppCompatActivity {
    private TextInputLayout inputEmail;

    private Button btnReset;

    private TextView btnBack;

    private FirebaseAuth firebaseAuth;

    private ProgressBar progressBar;

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_screen);

        inputEmail = findViewById(R.id.et_username);
        logo = findViewById(R.id.logoo);
        btnReset = findViewById(R.id.login_button);
        btnBack = findViewById(R.id.back);

        firebaseAuth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.INVISIBLE);

                String email;
                email = inputEmail.getEditText().toString();

                if (email.isEmpty()){
                    Toast.makeText(ForgetPasswordScreen.this, "Enter the email id", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgetPasswordScreen.this, "Please Check your email", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            Intent i = new Intent(ForgetPasswordScreen.this,LoginScreen.class);
                            startActivity(i);
                            finish();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ForgetPasswordScreen.this, "Please Try gain Later", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}