package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_login extends AppCompatActivity {
    EditText pass;
    Button btn1,btn2;
    DatabaseReference admin_ref;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        admin_ref= FirebaseDatabase.getInstance().getReference("Password");
        //admin_ref.child("pass").setValue("labgo");
        pass=findViewById(R.id.pass_id);
        btn1=findViewById(R.id.btn_id);
        btn2=findViewById(R.id.bt_1);
        admin_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                password=dataSnapshot.child("pass").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass.getText().toString().trim().equals(password))
                    startActivity(new Intent(Admin_login.this,Admin.class));
                else
                {
                    Toast.makeText(Admin_login.this, "password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_login.this,Forgot_password.class));
            }
        });
    }
}
