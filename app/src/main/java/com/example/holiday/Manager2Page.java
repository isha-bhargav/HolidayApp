package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Manager2Page extends AppCompatActivity {
    ArrayList<Manger1_list>l2;
    Manger1_list manger1_list;
    FirebaseUser user;
    RecyclerView recyclerView;
    DatabaseReference db2;
    RecyclerAdapter_m2 recyclerAdapter_m2;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager2_page);

        l2=new ArrayList<>();
        user=FirebaseAuth.getInstance().getCurrentUser();
        recyclerView=findViewById(R.id.recycler_m2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db2 = FirebaseDatabase.getInstance().getReference("Manger2_list");
        dialog=new ProgressDialog(Manager2Page.this);
        dialog.setTitle(" Please wait");
        dialog.setMessage("Loading...");
        dialog.show();

        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    manger1_list=ds.getValue(Manger1_list.class);
                    if (user.getEmail().equals(manger1_list.getManager_m2_email().toLowerCase()))
                    {
                        if (manger1_list.getApprove_m2().equals(""))
                        l2.add(manger1_list);
                    }
                }
                dialog.dismiss();
                recyclerAdapter_m2=new RecyclerAdapter_m2(Manager2Page.this,l2);
                recyclerView.setAdapter(recyclerAdapter_m2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_m1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1:
                startActivity(new Intent(Manager2Page.this,Dashboard_m2.class));
                break;
            case R.id.item2:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(Manager2Page.this,Manager2.class));

        }
        return super.onOptionsItemSelected(item);
    }

}
