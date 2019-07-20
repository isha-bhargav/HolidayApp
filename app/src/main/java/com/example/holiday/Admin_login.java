package com.example.holiday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_login extends AppCompatActivity {
    EditText pass;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        pass=findViewById(R.id.pass_id);
        btn=findViewById(R.id.btn_id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass.getText().toString().trim().equals("isha"))
                    startActivity(new Intent(Admin_login.this,Admin.class));
                else
                {
                    Toast.makeText(Admin_login.this, "password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
