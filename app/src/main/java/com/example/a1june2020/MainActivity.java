package com.example.a1june2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void goToMain2Activity (View mv){

        Intent intent = new Intent(this, Main2Activity.class);

        startActivity(intent);

    }
}
