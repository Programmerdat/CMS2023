package com.example.cms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

   @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Bundle extras = getIntent().getExtras();
       assert extras != null;
       String V1 = extras.getString(Intent.EXTRA_TEXT);
       assert V1 != null;
       Log.d("NumberMainActivity",V1);
   }

   public void Electrician(View view){
        Intent i = new Intent(MainActivity.this,electrician.class);
        startActivity(i);
   }

    public void Plumbing(View view){
        Intent i = new Intent(MainActivity.this,plumbing.class);
        startActivity(i);
    }

    public void AcRepair(View view){
        Intent i = new Intent(MainActivity.this,acrepair.class);
        startActivity(i);
    }


    public void HomeCleaning(View view){
        Intent i = new Intent(MainActivity.this,homecleaning.class);
        startActivity(i);
    }

    public void carRepair(View view){
        Intent i = new Intent(MainActivity.this,carrepair.class);
        startActivity(i);
    }

    public void LogOut(View view){
        Intent i = new Intent(MainActivity.this,logout.class);
        startActivity(i);
    }
}
