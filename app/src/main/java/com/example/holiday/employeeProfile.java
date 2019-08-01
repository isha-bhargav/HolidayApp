package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class employeeProfile extends AppCompatActivity {
    TextView nameD,emailD,manager1D,manager2D;
    DatabaseReference ref;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        nameD = findViewById(R.id.name);
        emailD = findViewById(R.id.email);
        manager1D = findViewById(R.id.manager1);
        manager2D = findViewById(R.id.manager2);

        //id = name_ed.getText().toString();
        Bundle bundle = getIntent().getExtras();
        final String id = bundle.getString("id");

        //ref= FirebaseDatabase.getInstance().getReference().child("Member").child(name);
        ref = FirebaseDatabase.getInstance().getReference().child("Member").child(id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String manager1 = dataSnapshot.child("manager1").getValue().toString();
                String manager2 = dataSnapshot.child("manager2").getValue().toString();

                nameD.setText(" Name : " + name);
                emailD.setText(" Email : " + email);
                manager1D.setText("Manager 1 : " + manager1);
                manager2D.setText("Manager 2 : " + manager2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
