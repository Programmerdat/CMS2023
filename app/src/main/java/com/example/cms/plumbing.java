package com.example.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class plumbing extends AppCompatActivity {

    private TextInputLayout name, mobile, add;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private TextInputLayout selectedtime, selecteddate;
    private Button btntime, btndate, btnappointment;
    private DatabaseReference appointmentref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing);

        name = findViewById(R.id.et_name);
        mobile = findViewById(R.id.et_phonenumber);
        add = findViewById(R.id.et_address);
        btndate = findViewById(R.id.btn_date);
        btntime = findViewById(R.id.btn_time);
        selecteddate = findViewById(R.id.et_Date);
        selectedtime = findViewById(R.id.et_username);
        btnappointment = findViewById(R.id.btnBookAppointment);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        appointmentref = database.getReference("appointments");


        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(plumbing.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                selectedtime.setHelperText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(plumbing.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selecteddate.setHelperText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, mYear, mMonth, mDay);
            }
        });

        btnappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment();
            }
        });
    }

    private void appointment() {
        String userName = name.getEditText().getText().toString();
        String userMobile = mobile.getEditText().getText().toString();
        String userAddress = add.getEditText().getText().toString();
        String selectedDate = selecteddate.getEditText().getText().toString();
        String selectedTime = selectedtime.getEditText().getText().toString();

        if (!userName.isEmpty() && !userMobile.isEmpty() && !userAddress.isEmpty() && !selectedDate.isEmpty() && !selectedTime.isEmpty()) {
            Toast.makeText(this, "Please Enter the details....", Toast.LENGTH_SHORT).show();
        }

        Appointment appointment = new Appointment(userMobile,userName,userAddress,selectedTime,selectedDate);

        appointmentref.push().setValue(appointment).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(plumbing.this, "Appointment Has booked", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(plumbing.this, AppointmentBooked.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(plumbing.this, "please try again later...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
