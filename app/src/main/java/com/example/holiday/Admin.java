package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    static ArrayList<Member>employee;
    static RecyclerView recyclerView;
    DatabaseReference reference4;
    RecyclerViewAdapter adapter;
    //Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        employee=new ArrayList<>();
         recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reference4 = FirebaseDatabase.getInstance().getReference("Member");
        reference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("log",dataSnapshot.toString());
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Member member = dataSnapshot1.getValue(Member.class);
                    employee.add(member);
                    Log.i("log","list added");
                }
                adapter = new RecyclerViewAdapter(Admin.this, employee);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
