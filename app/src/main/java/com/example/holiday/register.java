package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
    EditText email_ed,pass_ed,confirm_ed,name_ed,employeeID;
    Button reg_btn;
    FirebaseDatabase database;
    DatabaseReference ref;
    Member member;
    String daysleft = "";
    String installmentleft = "30";
    String manager1 = "";
    String manager2 = "";
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email_ed=findViewById(R.id.email_id);
        name_ed=findViewById(R.id.name_id);
        pass_ed=findViewById(R.id.pass_id);
        confirm_ed=findViewById(R.id.confirm_id);
        reg_btn=findViewById(R.id.reg_id);
        employeeID=findViewById(R.id.employee_id);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Member");
        member = new Member();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists());{
                    id = (dataSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(employeeID.getText().toString()))
                {
                    employeeID.setError("enter ID");
                    employeeID.requestFocus();
                    return;
                }
                if (employeeID.getText().toString().length()!=4)
                {
                    employeeID.setError("enter in correct format");
                    employeeID.requestFocus();
                    return;
                } if (TextUtils.isEmpty(email_ed.getText().toString()))
                {
                    email_ed.setError("enter email");
                    email_ed.requestFocus();
                    return;
                }


                if (TextUtils.isEmpty(name_ed.getText().toString()))
                {
                    name_ed.setError("enter name");
                    name_ed.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pass_ed.getText().toString()))
                {
                    pass_ed.setError("enter password");
                    email_ed.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(confirm_ed.getText().toString()))
                {
                    confirm_ed.setError("enter password");
                    confirm_ed.requestFocus();
                    return;
                }
                if (pass_ed.getText().toString().length()<6)
                {
                    pass_ed.setError("enter secured passsword");
                    pass_ed.requestFocus();
                    return;
                }
                if (pass_ed.getText().toString().equals(confirm_ed.getText().toString())) {
                    member.setName(name_ed.getText().toString());
                    member.setEmail(email_ed.getText().toString());
                    member.setPass(pass_ed.getText().toString());
                    member.setManager1(manager1);
                    member.setManager2(manager2);
                    member.setDaysleft(daysleft);
                    member.setEmployee(employeeID.getText().toString());
                    member.setInstallmentleft(installmentleft);
                    member.setManager_m1_email("");
                    member.setManager_m2_email("");
                    member.setApprove_m2("");
                    member.setApprove_m1("");
                    member.setSum("0");
                    id = employeeID.getText().toString();
                    ref.child(id).setValue(member);
                    Intent intent=new Intent(register.this,Main3Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Toast.makeText(register.this,"Success",Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    confirm_ed.setError("passwords not matching");
                    confirm_ed.requestFocus();
                    return;
                }
            }
        });

    }
}
