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

public class Dashboard_m2 extends AppCompatActivity {
    ArrayList<Manger1_list> list_dash;
    RecyclerView recyclerView_m2;
    DatabaseReference ref_m2;
    Recycle_m2 recycle_m2;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_m2);

       user = FirebaseAuth.getInstance().getCurrentUser();
       list_dash=new ArrayList<>();
        recyclerView_m2 = findViewById(R.id.recycler_view_m2);
        recyclerView_m2.setLayoutManager(new LinearLayoutManager(this));
        ref_m2 = FirebaseDatabase.getInstance().getReference("Manger2_list");
        ref_m2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Manger1_list manger1_list = d.getValue(Manger1_list.class);
                    if (manger1_list.getManager_m2_email().toLowerCase().equals(user.getEmail()))
                    {
                        if (manger1_list.getApprove_m2().equals("ok") && manger1_list.getApprove_m1().equals("ok"))
                            list_dash.add(manger1_list);
                    }
                         }

                  for (int j = 0; j < list_dash.size(); j++) {
                      String[] arr = list_dash.get(j).getToday_date().split("/");
                      Date today = new Date();
                      Calendar myNextCalender = Calendar.getInstance();
                      myNextCalender.set(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) - 1, Integer.parseInt(arr[2]));
                      Date nyd = myNextCalender.getTime();
                      long days = daysBetween(today, nyd);
                      SimpleDateFormat sdf = new SimpleDateFormat("MM dd,YYYY");
                      String todaysdate = sdf.format(today);
                      String newYeraDay = sdf.format(nyd);
                      Log.i("Days", days + "days from today" + newYeraDay);
                      if (days == 7 || list_dash.get(j).getApprove_m1().equals("no"))
                          list_dash.remove(j);

                     }

                recycle_m2 = new Recycle_m2(Dashboard_m2.this, list_dash);
                recyclerView_m2.setAdapter(recycle_m2);

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
