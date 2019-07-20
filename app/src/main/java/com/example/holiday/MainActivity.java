package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button reg_btn,log_btn,admin_btn;
    EditText email_ed,pass_ed;
    private FirebaseAuth firebaseAuth;
    String email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg_btn=findViewById(R.id.reg_id);
        log_btn=findViewById(R.id.log_id);
        email_ed=findViewById(R.id.email_id);
        pass_ed=findViewById(R.id.pass_id);
        admin_btn=findViewById(R.id.admin_id);
        firebaseAuth=FirebaseAuth.getInstance();
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
        admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Admin_login.class));
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
                }
                firebaseAuth.signInWithEmailAndPassword(email,pass) .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(MainActivity.this,Main3Activity.class));
        }
    }
}
