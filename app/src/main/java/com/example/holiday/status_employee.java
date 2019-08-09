package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class status_employee extends AppCompatActivity {
    TextView status, dates;
    DatabaseReference ref, ref2;
    String days_apply="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_employee);
        Log.i("status","1");
        status = findViewById(R.id.status);
        dates = findViewById(R.id.dates);
        Log.i("status","2");
        Bundle bundle = getIntent().getExtras();
        final String id = bundle.getString("id");
        ref = FirebaseDatabase.getInstance().getReference().child("Member").child(id);
        ref2=FirebaseDatabase.getInstance().getReference().child("Manger1_list").child(id);
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("satus","data");
                Log.i("satus",dataSnapshot.child("dates_m1").getValue().toString());
                dates.setText(dataSnapshot.child("dates_m1").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // String days_apply = dataSnapshot.child("dates_m1").getValue().toString();

                String status_leave = dataSnapshot.child("approve_m1").getValue().toString();
              //  dates.setText(days_apply);
                if (status_leave.equals("ok")) {
                    status.setText("Manager 1 Approved");
                   }
                if(dataSnapshot.child("approve_m1").getValue().toString().equals("ok") &&
                dataSnapshot.child("approve_m2").getValue().toString().equals("ok"))
                {
                    status.setText("Approved");
                }
                if (dataSnapshot.child("approve_m1").getValue().toString().equals("no"))
                    status.setText("REJECTED");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
