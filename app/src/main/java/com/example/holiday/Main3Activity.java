package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    Button out_btn;
    TextView tname, tm1, tm2;
    ArrayList<Member> list;
    DatabaseReference reff3;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        out_btn = findViewById(R.id.out_id);
        list = new ArrayList<>();
        Member member=new Member();
        tname = findViewById(R.id.name_id);
        tm1 = findViewById(R.id.manage1_id);
        tm2 = findViewById(R.id.manage2_id);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
            reff3 = FirebaseDatabase.getInstance().getReference().child("Member");
            out_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(Main3Activity.this, MainActivity.class));
                }
            });
            reff3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //String name=dataSnapshot.child(user.getUid()).child("name").getValue().toString();
                    // Log.i("log",dataSnapshot.child(user.getUid()).child("pass").getValue().toString());
                    // String manager1 = dataSnapshot.child("manager1").getValue().toString();
                    // String manager2 = dataSnapshot.child("manager2").getValue().toString();
                    // tm1.setText(manager1);
                   // tm2.setText(manager2);
                    // tname.setText(name);
                    Log.i("log",dataSnapshot.child(user.getUid()).toString());
}
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
