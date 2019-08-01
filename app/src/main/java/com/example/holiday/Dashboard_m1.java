package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class Dashboard_m1 extends AppCompatActivity {
    ArrayList<Manger1_list>l_dashboard;
    RecyclerView recyclerView_m1;
    DatabaseReference ref_m1;
    Recycle_m1 recycle_m1;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_m1);

            l_dashboard = new ArrayList<>();
            user = FirebaseAuth.getInstance().getCurrentUser();
            recyclerView_m1 = findViewById(R.id.recycler_view_m1);
            recyclerView_m1.setLayoutManager(new LinearLayoutManager(this));

            ref_m1 = FirebaseDatabase.getInstance().getReference("Manger2_list");
            ref_m1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        Manger1_list manger1_list = d.getValue(Manger1_list.class);
                        if (manger1_list.getManager_m1_email().toLowerCase().equals(user.getEmail())) {
                            if (manger1_list.getApprove_m1().equals("ok") && manger1_list.getApprove_m2().equals("ok")) {
                                l_dashboard.add(manger1_list);

                            }
                        }
                        }

                     for (int j = 0; j < l_dashboard.size(); j++) {
                     String[] arr = l_dashboard.get(j).getToday_date().split("/");
                     Date today = new Date();
                     Calendar myNextCalender = Calendar.getInstance();
                     myNextCalender.set(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) - 1, Integer.parseInt(arr[2]));
                     Date nyd = myNextCalender.getTime();
                     long days = daysBetween(today, nyd);
                     SimpleDateFormat sdf = new SimpleDateFormat("MM dd,YYYY");
                     String todaysdate = sdf.format(today);
                     String newYeraDay = sdf.format(nyd);
                     Log.i("Days", days + "days from today" + newYeraDay);
                     if (days == 7 || l_dashboard.get(j).getApprove_m1().equals("no"))
                     l_dashboard.remove(j);
                     }
                    recycle_m1 = new Recycle_m1(Dashboard_m1.this, l_dashboard);
                    recyclerView_m1.setAdapter(recycle_m1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }
             public long daysBetween (Date one, Date two)
             {
             long diff = (one.getTime() - two.getTime()) / 86400000;
             return Math.abs(diff);
             }

        }

