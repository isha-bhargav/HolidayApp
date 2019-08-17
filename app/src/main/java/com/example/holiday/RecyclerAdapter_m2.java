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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RecyclerAdapter_m2 extends RecyclerView.Adapter<RecyclerAdapter_m2.ViewHolder> {
    ArrayList<Manger1_list>list_manager2,l;
    Manger1_list manger1_list_manager1;
    DatabaseReference reff_mem,reff_manager2,mem_ref;
    FirebaseUser user;
    Context context;
    String name_db,pass_db,email_db,manager1_db,manager2_db,manager_m1_email_db,
    manager_m2_email_db,daysleft_db,installment_db,sum_db,employee_db,approve_m1_db,approve_m2_db;
    Member member;
    String dates;
    public RecyclerAdapter_m2(Context context,ArrayList<Manger1_list>list_manager2)
    {
        this.context=context;
        this.list_manager2=list_manager2;
        manger1_list_manager1=new Manger1_list();
        l=new ArrayList<>();
        user= FirebaseAuth.getInstance().getCurrentUser();
        member=new Member();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list1,parent,false);
        RecyclerAdapter_m2.ViewHolder holder=new RecyclerAdapter_m2.ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String []arr1=list_manager2.get(position).getToday_date().split("/");
        Log.i("date_2",arr1[0]);Log.i("date_2",arr1[1]);Log.i("date_2",arr1[2]);
        Calendar myNextCalender1 = Calendar.getInstance();
        myNextCalender1.set(Integer.parseInt(arr1[0]), Integer.parseInt(arr1[1]) - 1, Integer.parseInt(arr1[2]));
        Date nyd1 = myNextCalender1.getTime();
        String []arr2=list_manager2.get(position).getDate_day1().split("/");
        Calendar myNextCalender2 = Calendar.getInstance();
        Log.i("date_2","1:  "+arr2[0]);Log.i("date_2","1 :  "+arr2[1]);Log.i("date_2","1 :"+arr2[2]);
        myNextCalender2.set(Integer.parseInt(arr2[2]), Integer.parseInt(arr2[1]) - 1, Integer.parseInt(arr2[0]));
        Date nyd2 = myNextCalender2.getTime();
        Log.i("date_2",String.valueOf(Math.abs((nyd1.getTime()-nyd2.getTime())/86400000)));
        if (Math.abs((nyd1.getTime()-nyd2.getTime())/86400000)<=2)
            holder.t_display.setVisibility(View.VISIBLE);

        holder.t_name.setText(list_manager2.get(position).getName_m1());
        holder.t_date.setText(list_manager2.get(position).getDates_m1());
        holder.t_reason.setText(list_manager2.get(position).getReason());


        holder.bt_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff_mem= FirebaseDatabase.getInstance().getReference("Member").child(list_manager2.get(position).getEmployee());
                reff_manager2=FirebaseDatabase.getInstance().getReference("Manger2_list").child(list_manager2.get(position).getEmployee());
                mem_ref=FirebaseDatabase.getInstance().getReference().child("Member");
                reff_manager2.child("approve_m2").setValue("ok");
                reff_mem.child("approve_m2").setValue("ok");
                reff_manager2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reff_manager2.child("approve_m2").setValue("ok");
                        manger1_list_manager1.setReason(dataSnapshot.child("reason").getValue().toString());
                        manger1_list_manager1.setApprove_m1(dataSnapshot.child("approve_m1").getValue().toString());
                        manger1_list_manager1.setManager_m2_email(dataSnapshot.child("manager_m2_email").getValue().toString());
                        manger1_list_manager1.setManager_m2(dataSnapshot.child("manager_m2").getValue().toString());
                        manger1_list_manager1.setToday_date(dataSnapshot.child("today_date").getValue().toString());
                        manger1_list_manager1.setManager_m1(dataSnapshot.child("manager_m1").getValue().toString());
                        manger1_list_manager1.setName_m1(dataSnapshot.child("name_m1").getValue().toString());
                        dates=dataSnapshot.child("dates_m1").getValue().toString();
                        manger1_list_manager1.setDates_m1(dates);
                        manger1_list_manager1.setEmployee(dataSnapshot.child("employee").getValue().toString());
                        Toast.makeText(context, "Approved", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                reff_mem.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        name_db=dataSnapshot.child("name").getValue().toString();
                        email_db=dataSnapshot.child("email").getValue().toString();
                        pass_db=dataSnapshot.child("pass").getValue().toString();
                        manager1_db=dataSnapshot.child("manager1").getValue().toString();
                        manager2_db=dataSnapshot.child("manager2").getValue().toString();
                        manager_m1_email_db=dataSnapshot.child("manager_m1_email").getValue().toString();
                        manager_m2_email_db=dataSnapshot.child("manager_m2_email").getValue().toString();
                        daysleft_db=String.valueOf(Double.parseDouble(dataSnapshot.child("daysleft").getValue().toString())-
                                Double.parseDouble(dataSnapshot.child("sum").getValue().toString()));
                        installment_db=String.valueOf(Integer.parseInt(dataSnapshot.child("installmentleft").getValue().toString())-1);
                        approve_m1_db=dataSnapshot.child("approve_m1").getValue().toString();
                        approve_m2_db=dataSnapshot.child("approve_m2").getValue().toString();
                        employee_db=dataSnapshot.child("employee").getValue().toString();
                        member.setName(name_db);
                        member.setApprove_m2(approve_m2_db);
                        member.setPass(pass_db);
                        member.setDaysleft(daysleft_db);
                        member.setSum("0");
                        member.setApprove_m1(approve_m1_db);
                        member.setEmail(email_db);
                        member.setManager_m2_email(manager_m2_email_db);
                        member.setManager_m1_email(manager_m1_email_db);
                        member.setManager1(manager1_db);
                        member.setManager2(manager2_db);
                        member.setInstallmentleft(installment_db);
                        member.setEmployee(employee_db);
                        reff_mem.removeValue();
                       // mem_ref.child(employee_db).setValue(member);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
               mem_ref.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       mem_ref.child(employee_db).setValue(member);
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               }) ;

                Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show();
                sendMail_approve();
            }
        });
        holder.bt_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff_mem= FirebaseDatabase.getInstance().getReference("Member").child(list_manager2.get(position).getEmployee());
                reff_manager2=FirebaseDatabase.getInstance().getReference("Manger2_list").child(list_manager2.get(position).getEmployee());
                reff_manager2.child("approve_m2").setValue("no");
                reff_mem.child("approve_m2").setValue("no");
                Toast.makeText(context, "Rejected", Toast.LENGTH_SHORT).show();
                sendMail_reject();
                            }
        });

    }

    private void sendMail_approve()
    {
        reff_mem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String r=dataSnapshot.child("email").getValue().toString();
                String subject="Application Approval";
                String []recipients=r.split(",");
                String message="Your application has been approved for "+dates;
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
    private void sendMail_reject()
    {
        reff_mem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String r=dataSnapshot.child("email").getValue().toString();
                String subject="Application Approval";
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
    @Override
    public int getItemCount() {
        return list_manager2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView t_name,t_date,t_reason,t_display;
        Button bt_approve,bt_reject;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            t_date=itemView.findViewById(R.id.t2);
            t_name=itemView.findViewById(R.id.t1);
            t_reason=itemView.findViewById(R.id.t3);
            bt_approve=itemView.findViewById(R.id.b1);
            bt_reject=itemView.findViewById(R.id.b2);
            t_display=itemView.findViewById(R.id.text);
            linearLayout=itemView.findViewById(R.id.parent_layout);

        }
    }
}
