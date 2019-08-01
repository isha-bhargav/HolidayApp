package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Manager1Page extends AppCompatActivity {
    static ArrayList<Manger1_list>l1;
    Manger1_list manger1_list;
    DatabaseReference db1;
    RecyclerView recyclerView;
    RecyclerAdapter_m1 recyclerAdapter_m1;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager1_page);

        l1=new ArrayList<>();
        user=FirebaseAuth.getInstance().getCurrentUser();
        recyclerView=findViewById(R.id.recycler_m1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db1=FirebaseDatabase.getInstance().getReference("Manger1_list");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    manger1_list=ds.getValue(Manger1_list.class);
                    if (user.getEmail().equals(manger1_list.getManager_m1_email().toLowerCase()))
                    {
                        if (manger1_list.getApprove_m1().equals("") )
                        l1.add(manger1_list);
                    }
                }

                recyclerAdapter_m1=new RecyclerAdapter_m1(Manager1Page.this,l1);
                recyclerView.setAdapter(recyclerAdapter_m1);
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
                startActivity(new Intent(Manager1Page.this,Dashboard_m1.class));
                break;
            case R.id.item2:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(Manager1Page.this,Manager1.class));

        }
        return super.onOptionsItemSelected(item);
    }
}
