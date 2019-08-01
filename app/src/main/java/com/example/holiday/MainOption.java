package com.example.holiday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainOption extends AppCompatActivity {
    Button admin_btn,employee_btn,manager1_btn,manager2_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_option);
        admin_btn=findViewById(R.id.admin_id);
        employee_btn=findViewById(R.id.employee_id);
        manager1_btn=findViewById(R.id.manager1_id);
        manager2_btn=findViewById(R.id.manager2_id);
        admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainOption.this,Admin_login.class));
            }
        });
        employee_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainOption.this,MainActivity.class));

            }
        });
        manager1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainOption.this,Manager1.class));

            }
        });
        manager2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainOption.this,Manager2.class));

            }
        });
    }
}
