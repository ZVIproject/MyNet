package com.example.zviproject.internetapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Autorization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);
    }

    public void onClick(View view) {
       Intent intent = new Intent(Autorization.this, MainActivity.class);
       startActivity(intent);
    }
}
