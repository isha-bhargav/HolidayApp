package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    Button apply, status, profile, logout;
    public TextView daysLefts, installment_left;
    DatabaseReference ref;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        daysLefts = findViewById(R.id.daysleft);
        installment_left = findViewById(R.id.installment_left);
        apply = findViewById(R.id.apply);
        status = findViewById(R.id.status);
        profile = findViewById(R.id.profile);
        logout = findViewById(R.id.logout);
        Bundle bundle = getIntent().getExtras();
        final String id = bundle.getString("id");
        Toast.makeText(Main3Activity.this," " + id, Toast.LENGTH_SHORT).show();

        ref = FirebaseDatabase.getInstance().getReference().child("Member").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String daysleft = dataSnapshot.child("daysleft").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();

                daysLefts.setText(daysleft);
                id.equals(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(daysLefts.getText().toString().equals("0"))
                {
                    Toast.makeText(Main3Activity.this,"You have no leaves left",Toast.LENGTH_SHORT).show();
                }
                else if(installment_left.getText().toString().equals("0"))
                {
                    Toast.makeText(Main3Activity.this,"You have no installment left",Toast.LENGTH_SHORT).show();
                }
                else{

                    Intent intent=new Intent(Main3Activity.this,Apply_for_leave.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main3Activity.this,employeeProfile.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }

    }

