package com.example.holiday;

import android.content.Context;
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

public class Recycle_m1 extends RecyclerView.Adapter<Recycle_m1.ViewHolder> {

     ArrayList<Manger1_list>list_m1;
     Manger1_list manger2_list;
    Context context;
    DatabaseReference ref_manager2,ref_manager1,ref_member;
    public Recycle_m1(Context context, ArrayList<Manger1_list> list_m1)
    {
        this.context=context;
        this.list_m1=list_m1;
        manger2_list=new Manger1_list();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_m1,parent,false);
        Recycle_m1.ViewHolder holder_m1=new Recycle_m1.ViewHolder(v);
        return holder_m1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txt_name.setText(list_m1.get(position).getName_m1().toString());
        holder.btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref_manager1=FirebaseDatabase.getInstance().getReference("Manger1_list").child(list_m1.get(position).getEmployee());
                ref_manager1.child("approve_m1").setValue("ok");
                ref_member= FirebaseDatabase.getInstance().getReference("Member").child(list_m1.get(position).getEmployee());
                ref_member.child("approve_m1").setValue("ok");

                ref_manager1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         ref_manager2=FirebaseDatabase.getInstance().getReference("Manger2_list");
                         manger2_list.setReason(dataSnapshot.child("reason").getValue().toString());
                         manger2_list.setApprove_m1(dataSnapshot.child("approve_m1").getValue().toString());
                         manger2_list.setManager_m2_email(dataSnapshot.child("manager_m2_email").getValue().toString());
                         manger2_list.setManager_m2(dataSnapshot.child("manager_m2").getValue().toString());
                         manger2_list.setToday_date(dataSnapshot.child("today_date").getValue().toString());
                         manger2_list.setManager_m1(dataSnapshot.child("manager_m1").getValue().toString());
                         manger2_list.setName_m1(dataSnapshot.child("name_m1").getValue().toString());
                         manger2_list.setDates_m1(dataSnapshot.child("dates_m1").getValue().toString());
                         manger2_list.setEmployee(dataSnapshot.child("employee").getValue().toString());
                        ref_manager2.child(dataSnapshot.child("employee").getValue().toString()).setValue(manger2_list);
                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
                holder.btn_reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ref_manager1=FirebaseDatabase.getInstance().getReference("Manger1_list").child(list_m1.get(position).getEmployee());
                        ref_manager1.child("approve_m1").setValue("no");
                        ref_member= FirebaseDatabase.getInstance().getReference("Member").child(list_m1.get(position).getEmployee());
                        ref_member.child("approve_m1").setValue("no");
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list_m1.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout layout;
        TextView txt_name,txt_dates,txt_reason;
        Button btn_approve,btn_reject;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txt_name=itemView.findViewById(R.id.txt1);
            txt_dates=itemView.findViewById(R.id.txt2);
            txt_reason=itemView.findViewById(R.id.txt3);
            layout=itemView.findViewById(R.id.layout);
            btn_approve=itemView.findViewById(R.id.btn1);
            btn_reject=itemView.findViewById(R.id.btn2);
            txt_reason=itemView.findViewById(R.id.txt3);
        }
    }
   }
