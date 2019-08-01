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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerAdapter_m1 extends RecyclerView.Adapter<RecyclerAdapter_m1.ViewHolder> {
    ArrayList<Manger1_list>list_manager1;
    Manger1_list manger1_list_manager1;
    DatabaseReference reff_manager1,reff_mem,reff_manager2;
    Context context;
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
                        reff_manager2=FirebaseDatabase.getInstance().getReference("Manger2_list");
                        manger1_list_manager1.setReason(dataSnapshot.child("reason").getValue().toString());
                        manger1_list_manager1.setApprove_m1(dataSnapshot.child("approve_m1").getValue().toString());
                        manger1_list_manager1.setManager_m2_email(dataSnapshot.child("manager_m2_email").getValue().toString());
                        manger1_list_manager1.setManager_m2(dataSnapshot.child("manager_m2").getValue().toString());
                        manger1_list_manager1.setToday_date(dataSnapshot.child("today_date").getValue().toString());
                        manger1_list_manager1.setManager_m1(dataSnapshot.child("manager_m1").getValue().toString());
                        manger1_list_manager1.setName_m1(dataSnapshot.child("name_m1").getValue().toString());
                        manger1_list_manager1.setDates_m1(dataSnapshot.child("dates_m1").getValue().toString());
                        manger1_list_manager1.setApprove_m2(dataSnapshot.child("approve_m2").getValue().toString());
                        manger1_list_manager1.setEmployee(dataSnapshot.child("employee").getValue().toString());
                        manger1_list_manager1.setManager_m1_email(dataSnapshot.child("manager_m1_email").getValue().toString());
                        reff_manager2.child(dataSnapshot.child("employee").getValue().toString()).setValue(manger1_list_manager1);
                        Toast.makeText(context, "Approved", Toast.LENGTH_LONG).show();


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
            }
        });
    }



    @Override
    public int getItemCount() {
        return list_manager1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView t_name,t_date,t_reason;
        Button bt_approve,bt_reject;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t_date=itemView.findViewById(R.id.t2);
            t_name=itemView.findViewById(R.id.t1);
            t_reason=itemView.findViewById(R.id.t3);
            bt_approve=itemView.findViewById(R.id.b1);
            bt_reject=itemView.findViewById(R.id.b2);
            linearLayout=itemView.findViewById(R.id.parent_layout);

        }
    }
}
