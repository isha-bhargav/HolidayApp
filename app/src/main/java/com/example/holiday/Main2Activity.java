package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText email_ed,pass_ed,confirm_ed,name_ed;
    Button reg_btn;
    static String name,email,pass,confirm;
    DatabaseReference reff;
    FirebaseUser user;
    //ArrayList<String>employee;
    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //employee=new ArrayList<>();
        email_ed=findViewById(R.id.email_id);
        name_ed=findViewById(R.id.name_id);
        pass_ed=findViewById(R.id.pass_id);
        confirm_ed=findViewById(R.id.confirm_id);
        reg_btn=findViewById(R.id.reg_id);
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        member = new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              name=name_ed.getText().toString().trim();
              email=email_ed.getText().toString().trim();
              pass=pass_ed.getText().toString().trim();
              confirm=confirm_ed.getText().toString().trim();
              if (TextUtils.isEmpty(name))
              {
                    name_ed.setError("enter name");
                    name_ed.requestFocus();
                    return;
              }
                if (TextUtils.isEmpty(email))
                {
                    email_ed.setError("enter email");
                    email_ed.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pass))
                {
                    pass_ed.setError("enter password");
                    email_ed.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(confirm))
                {
                    confirm_ed.setError("enter password");
                    confirm_ed.requestFocus();
                    return;
                }
                if (pass.length()<6)
                {
                    pass_ed.setError("enter secured passsword");
                    pass_ed.requestFocus();
                    return;
                }
                if (pass.equals(confirm))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.i("log",",mng");
                                        //Log.i("log",Admin.employee.get(1).toString());
                                        name=name_ed.getText().toString().trim();
                                        member.setName(name);
                                        member.setEmail(email_ed.getText().toString().trim());
                                        member.setPass(pass_ed.getText().toString().trim());
                                        member.setManager1("");
                                        member.setManager2("");
                                        reff.child(name).setValue(member);
                                        //reff.push().setValue(member);
                                        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        Toast.makeText(Main2Activity.this, "registration successful..Now please log in", Toast.LENGTH_SHORT).show();
                                      } else {
                                        Toast.makeText(Main2Activity.this, "registration failed", Toast.LENGTH_SHORT).show();
                                          }
                                }
                            });
                }else
                {
                    confirm_ed.setError("passwords not matching");
                    confirm_ed.requestFocus();
                    return;
                }
               }
        });
    }
}
