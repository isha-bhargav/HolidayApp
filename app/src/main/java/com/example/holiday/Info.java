package com.example.holiday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Info extends AppCompatActivity {
TextView txt1,txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        txt1=findViewById(R.id.t_display);
        txt2=findViewById(R.id.tx);
        Intent intent=getIntent();
        String in=intent.getStringExtra("info");
        String name=intent.getStringExtra("name");
        txt2.setText(name);
        txt1.setText(in);
    }
}
