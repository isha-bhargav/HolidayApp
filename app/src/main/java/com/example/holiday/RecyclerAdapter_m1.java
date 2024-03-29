package com.example.holiday;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RecyclerAdapter_m1 extends RecyclerView.Adapter<RecyclerAdapter_m1.ViewHolder> {
    ArrayList<Manger1_list>list_manager1;
    Manger1_list manger1_list_manager1;
    DatabaseReference reff_manager1,reff_mem,reff_manager2;
    Context context;
    String dates;
    public  RecyclerAdapter_m1(Context context,ArrayList<Manger1_list>list_manager1)
    {
        this.context=context;
        this.list_manager1=list_manager1;
        manger1_list_manager1=new Manger1_list();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list1,parent,false);
        RecyclerAdapter_m1.ViewHolder holder_m1=new RecyclerAdapter_m1.ViewHolder(v);
        return holder_m1;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        reff_manager1=FirebaseDatabase.getInstance().getReference("Manger1_list").child(list_manager1.get(position).getEmployee());
        String []arr1=list_manager1.get(position).getToday_date().split("/");
        Calendar myNextCalender1 = Calendar.getInstance();
        myNextCalender1.set(Integer.parseInt(arr1[0]), Integer.parseInt(arr1[1]) - 1, Integer.parseInt(arr1[2]));
        Date nyd1 = myNextCalender1.getTime();
        String []arr2=list_manager1.get(position).getDate_day1().split("/");
        Calendar myNextCalender2 = Calendar.getInstance();
        myNextCalender2.set(Integer.parseInt(arr2[2]), Integer.parseInt(arr2[1]) - 1, Integer.parseInt(arr2[0]));
        Date nyd2 = myNextCalender2.getTime();
        Log.i("ref",arr2[0]+" "+arr2[1]+" "+arr2[2]);
        Log.i("ref",arr1[0]+" "+arr1[1]+" "+arr1[2]);

        Log.i("ref",String.valueOf((nyd1.getTime()-nyd2.getTime())/86400000));
        if (Math.abs((nyd1.getTime()-nyd2.getTime())/86400000)<=2)
            holder.t_emergency.setVisibility(View.VISIBLE);
        holder.t_name.setText(list_manager1.get(position).getName_m1());
            holder.t_date.setText(list_manager1.get(position).getDates_m1());
            holder.t_reason.setText(list_manager1.get(position).getReason());
        holder.bt_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff_manager1=FirebaseDatabase.getInstance().getReference("Manger1_list").child(list_manager1.get(position).getEmployee());
                reff_manager1.child("approve_m1").setValue("ok");
                reff_mem= FirebaseDatabase.getInstance().getReference("Member").child(list_manager1.get(position).getEmployee());
                reff_mem.child("approve_m1").setValue("ok");

                reff_manager1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //String manger_m2=dataSnapshot.child("manager_m2").getValue().toString();
                        reff_manager1.child("approve_m1").setValue("ok");
                        reff_manager2=FirebaseDatabase.getInstance().getReference("Manger2_list");
                        manger1_list_manager1.setReason(dataSnapshot.child("reason").getValue().toString());
                        manger1_list_manager1.setApprove_m1("ok");
                        manger1_list_manager1.setManager_m2_email(dataSnapshot.child("manager_m2_email").getValue().toString());
                        manger1_list_manager1.setManager_m2(dataSnapshot.child("manager_m2").getValue().toString());
                        manger1_list_manager1.setToday_date(dataSnapshot.child("today_date").getValue().toString());
                        manger1_list_manager1.setManager_m1(dataSnapshot.child("manager_m1").getValue().toString());
                        manger1_list_manager1.setName_m1(dataSnapshot.child("name_m1").getValue().toString());
                        dates=dataSnapshot.child("dates_m1").getValue().toString();
                        manger1_list_manager1.setDates_m1(dates);
                        manger1_list_manager1.setApprove_m2(dataSnapshot.child("approve_m2").getValue().toString());
                        manger1_list_manager1.setEmployee(dataSnapshot.child("employee").getValue().toString());
                        manger1_list_manager1.setDate_day1(dataSnapshot.child("date_day1").getValue().toString());
                        manger1_list_manager1.setManager_m1_email(dataSnapshot.child("manager_m1_email").getValue().toString());
                        reff_manager2.child(dataSnapshot.child("employee").getValue().toString()).setValue(manger1_list_manager1);
                        Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show();
                        sendMail_approve();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        holder.bt_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff_manager1=FirebaseDatabase.getInstance().getReference("Manger1_list").child(list_manager1.get(position).getEmployee());
                reff_manager1.child("approve_m1").setValue("no");

                reff_mem= FirebaseDatabase.getInstance().getReference("Member").child(list_manager1.get(position).getEmployee());
                reff_mem.child("approve_m1").setValue("no");
                Toast.makeText(context, "Rejected", Toast.LENGTH_SHORT).show();
                sendMail_reject();
            }
        });
    }

    private void sendMail_reject()
    {
        reff_mem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String r=dataSnapshot.child("email").getValue().toString();
               // String name=dataSnapshot.child("name").getValue().toString();
                String subject="Application Rejected";
                String []recipients=r.split(",");
                String message="Your application has been rejected for "+dates;
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                intent.setType("message/rfc822");
                context.startActivity(Intent.createChooser(intent,"choose an email client"));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

        private  void sendMail_approve()
        {
           reff_mem.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   String r=dataSnapshot.child("manager_m2_email").getValue().toString();
                   String name=dataSnapshot.child("name").getValue().toString();
                   String subject="Application approval";
                   String []recipients=r.split(",");
                   String message="I "+name+" needs a leave for "+dates;
                   Intent intent=new Intent(Intent.ACTION_SEND);
                   intent.putExtra(Intent.EXTRA_EMAIL,recipients);
                   intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                   intent.putExtra(Intent.EXTRA_TEXT,message);
                   intent.setType("message/rfc822");
                   context.startActivity(Intent.createChooser(intent,"choose an email client"));

               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });
        }
    @Override
    public int getItemCount() {
        return list_manager1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView t_name,t_date,t_reason,t_emergency;
        Button bt_approve,bt_reject;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t_date=itemView.findViewById(R.id.t2);
            t_name=itemView.findViewById(R.id.t1);
            t_reason=itemView.findViewById(R.id.t3);
            t_emergency=itemView.findViewById(R.id.text);
            bt_approve=itemView.findViewById(R.id.b1);
            bt_reject=itemView.findViewById(R.id.b2);
            linearLayout=itemView.findViewById(R.id.parent_layout);

        }
    }
}
