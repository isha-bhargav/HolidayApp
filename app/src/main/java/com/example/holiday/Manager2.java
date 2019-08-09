package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Manager2 extends AppCompatActivity {
    Button reg_btn,log_btn;
    EditText email_ed,pass_ed;
    private FirebaseAuth firebaseAuth;
    String email,pass;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager2);
        reg_btn = findViewById(R.id.reg_id);
        log_btn = findViewById(R.id.log_id);
        email_ed = findViewById(R.id.email_id);
        pass_ed = findViewById(R.id.pass_id);
        dialog=new ProgressDialog(Manager2.this);
        dialog.setTitle(" Please wait");
        dialog.setMessage("Signing in...");
        firebaseAuth = FirebaseAuth.getInstance();
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Manager2.this,Manager12.class));
            }
        });
        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=email_ed.getText().toString().trim();
                pass=pass_ed.getText().toString().trim();
                if(email.isEmpty())
                {
                    email_ed.setError("email is required");
                    email_ed.requestFocus();
                    return;
                }
                if(pass.isEmpty())
                {
                    pass_ed.setError("email is required");
                    pass_ed.requestFocus();
                    return;
                }
                if(pass.length()<6)
                {
                    pass_ed.setError("password should be minimum 6 characters");
                    pass_ed.requestFocus();
                    return;
                }dialog.show();
                firebaseAuth.signInWithEmailAndPassword(email,pass) .addOnCompleteListener(Manager2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            dialog.dismiss();
                            Intent intent=new Intent(Manager2.this,Manager2Page.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(Manager2.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(Manager2.this,Manager2Page.class));
        }
    }

}
