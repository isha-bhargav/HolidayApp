package com.example.holiday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Recycle_m2 extends RecyclerView.Adapter<Recycle_m2.ViewHolder> {
     ArrayList<Manger1_list> list_m2;
    Context context;
    DatabaseReference ref_member,ref_manager2;
    FirebaseUser user;
    public Recycle_m2(Context context,ArrayList<Manger1_list>list_m2)
    {
        this.context=context;
        this.list_m2=list_m2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_m1,parent,false);
        Recycle_m2.ViewHolder holder_m2=new Recycle_m2.ViewHolder(v);
        return holder_m2;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txt_name.setText(list_m2.get(position).getName_m1().toString());
        holder.txt_dates.setText(list_m2.get(position).getDates_m1().toString());
       holder.txt_reason.setText(list_m2.get(position).getReason().toString());
        holder.btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref_member= FirebaseDatabase.getInstance().getReference("Member").child(list_m2.get(position).getEmployee());
                ref_manager2=FirebaseDatabase.getInstance().getReference("Manger2_list").child(list_m2.get(position).getEmployee());
                ref_manager2.child("approve_m2").setValue("ok");
                ref_member.child("approve_m2").setValue("ok");
            }
        });
        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref_member= FirebaseDatabase.getInstance().getReference("Member").child(list_m2.get(position).getEmployee());
                ref_manager2=FirebaseDatabase.getInstance().getReference("Manger2_list").child(list_m2.get(position).getEmployee());
                ref_manager2.child("approve_m2").setValue("no");
                ref_member.child("approve_m2").setValue("no");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_m2.size();
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
            layout=itemView.findViewById(R.id.layout);
            btn_approve=itemView.findViewById(R.id.btn1);
            btn_reject=itemView.findViewById(R.id.btn2);
            txt_reason=itemView.findViewById(R.id.txt3);
        }
    }
}
