package com.example.holiday;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    static ArrayList<Member>list;
    Context context;
    public RecyclerViewAdapter(Context context,ArrayList<Member>list)
    {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tname.setText(list.get(position).getName().toString());
        holder.edmanager1.getText().toString().trim();
        holder.edmanager2.getText().toString().trim();
        holder.email_m1.getText().toString().trim();
        holder.ednumber.getText().toString().trim();
        if (list.get(position).getManager1().equals("")) {
            holder.t_display.setVisibility(View.VISIBLE);
            holder.t_display.setText("Please Assign");
            holder.info_btn.setVisibility(View.GONE);
        }
        else {
            holder.t_display.setVisibility(View.GONE);
            holder.info_btn.setVisibility(View.VISIBLE);
            holder.info_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,Info.class);
                    intent.putExtra("info"," Manager1:  "+list.get(position).getManager1()+"\n\n Manager2: "+list.get(position)
                            .getManager2()+"\n\n Leaves: "+list.get(position).getDaysleft());
                    intent.putExtra("name",list.get(position).getName());
                    context.startActivity(intent);

                }
            });

        }
        holder.assign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Member").child(list.get(position).getEmployee());
                if(!TextUtils.isEmpty(holder.edmanager1.getText().toString()) && !TextUtils.isEmpty(holder.edmanager2.getText().toString()) ) {
                    rootRef.child("manager1").setValue(holder.edmanager1.getText().toString().trim());
                    rootRef.child("manager2").setValue(holder.edmanager2.getText().toString().trim());
                    rootRef.child("daysleft").setValue(holder.ednumber.getText().toString().trim());
                    rootRef.child("manager_m1_email").setValue(holder.email_m1.getText().toString().trim());
                    rootRef.child("manager_m2_email").setValue(holder.email_m2.getText().toString().trim());
                    Toast.makeText(context, "Managers assigned", Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(context, "please complete the information", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

}
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tname,t_display;
        EditText edmanager1,edmanager2,ednumber,email_m1,email_m2;
         Button assign_btn,info_btn;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tname=itemView.findViewById(R.id.name_id);
            edmanager1=itemView.findViewById(R.id.manager1_id);
            edmanager2=itemView.findViewById(R.id.manager2_id);
            ednumber=itemView.findViewById(R.id.number_id);
            assign_btn=itemView.findViewById(R.id.assign_id);
            t_display=itemView.findViewById(R.id.t1);
            info_btn=itemView.findViewById(R.id.btn);
            email_m1=itemView.findViewById(R.id.manager1_emailid);
            email_m2=itemView.findViewById(R.id.manager2_emailid);
            layout=itemView.findViewById(R.id.layout_id);
        }
    }
}
