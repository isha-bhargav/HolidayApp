package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class Manager22 extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText email_ed,pass_ed,confirm_ed,name_ed;
    Button reg_btn;
    static String name,email,pass,confirm;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager22);
        email_ed=findViewById(R.id.email_id);
        name_ed=findViewById(R.id.name_id);
        pass_ed=findViewById(R.id.pass_id);
        confirm_ed=findViewById(R.id.confirm_id);
        reg_btn=findViewById(R.id.reg_id);
        dialog=new ProgressDialog(Manager22.this);
        dialog.setTitle(" Please wait");
        dialog.setMessage("Registering...");

        firebaseAuth=FirebaseAuth.getInstance();
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
                    dialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(Manager22.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        dialog.dismiss();
                                        Intent intent=new Intent(Manager22.this,Manager2.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        Toast.makeText(Manager22.this, "registration successful..Now please log in", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Manager22.this, "registration failed", Toast.LENGTH_SHORT).show();
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
