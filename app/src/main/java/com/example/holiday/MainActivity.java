package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
   EditText employeeID, email_id, pass_id;
    Button reg_id, log_id;
    String id;
    String email, pass,employee_id;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeID = findViewById(R.id.employee_id);
      //  email_id = findViewById(R.id.email_id);
        pass_id = findViewById(R.id.pass_id);

        reg_id=findViewById(R.id.reg_id);
        log_id=findViewById(R.id.log_id);



        log_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference().child("Member");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren())

                        if(ds.child("employee").getValue().toString().equals(employeeID.getText().toString())) {
                           // email = dataSnapshot.child(employeeID.getText().toString()).child("email").getValue().toString();
                            //pass = dataSnapshot.child(employeeID.getText().toString()).child("pass").getValue().toString();

                            //Toast.makeText(MainActivity.this, "ID match", Toast.LENGTH_SHORT).show();
                           // if (dataSnapshot.child("employee").equals(employeeID.getText().toString()))
                            /// if(email_id.getText().toString().equals(email)  /*&&pass.equals(pass_id.getText().toString())*/){
                            /**   Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                             Bundle bundle = new Bundle();
                             bundle.putString("id",employeeID.getText().toString());
                             intent.putExtras(bundle);
                             startActivity(intent);*/
                            //}

                            //   else{
                            //     Toast.makeText(MainActivity.this,"Enter Valid Credentials",Toast.LENGTH_SHORT).show();
                            //}
                            pass=ds.child("pass").getValue().toString();
                            employee_id=ds.child("employee").toString();
                            if (pass_id.getText().toString().equals(pass))
                            {
                                Toast.makeText(MainActivity.this,"Logged in",Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("id",employeeID.getText().toString());
                                intent.putExtras(bundle);
                                startActivity(intent);

                            }
                            else
                                Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //Toast.makeText(MainActivity.this,"Wrong credentials",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                      //  Toast.makeText(MainActivity.this,"Not exist",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        reg_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,register.class));
            }
        });
    }

}
