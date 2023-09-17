package com.example.cms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AppointmentBooked extends AppCompatActivity {

    private TextView text;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);

        text = findViewById(R.id.tvAppointmentConfirmation);
        btn = findViewById(R.id.btnDone);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AppointmentBooked.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}