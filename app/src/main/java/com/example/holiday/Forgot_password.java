package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Forgot_password extends AppCompatActivity {
    EditText pass_old,pass_new;
    Button btn;
    DatabaseReference ref;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        pass_old=findViewById(R.id.ed1);
        pass_new=findViewById(R.id.ed2);
        btn=findViewById(R.id.bt_1);
        ref= FirebaseDatabase.getInstance().getReference().child("Password");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                password=dataSnapshot.child("pass").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass_old.getText().toString().equals(password))
                {
                    ref.child("pass").setValue(pass_new.getText().toString());
                    Toast.makeText(Forgot_password.this, "Password updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Forgot_password.this, "wrong password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
