package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
     ArrayList<Member>employee;
     RecyclerView recyclerView;
     DatabaseReference reference4;
     RecyclerViewAdapter adapter;
     ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        employee=new ArrayList<Member>();
         recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reference4 = FirebaseDatabase.getInstance().getReference("Member");
        dialog=new ProgressDialog(Admin.this);
        dialog.setTitle(" Please wait");
        dialog.setMessage("Loading...");
        dialog.show();

        reference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    try {
                        Member member = dataSnapshot1.getValue(Member.class);
                        employee.add(member);
                    }catch (Exception e)
                    {
                        Log.i("log",e.getMessage());
                    }
                    dialog.dismiss();
                }
                adapter = new RecyclerViewAdapter(Admin.this, employee);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        Log.i("search","1");
        inflater.inflate(R.menu.search_menu,menu);
        Log.i("search","2");
        MenuItem item=menu.findItem(R.id.search_id);
        Log.i("search","3");
       /** SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });*/
       SearchView searchView= (SearchView) item.getActionView();
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {
               adapter.getFilter().filter(s);
               return true;
           }
       });

            return true;
    }
}
