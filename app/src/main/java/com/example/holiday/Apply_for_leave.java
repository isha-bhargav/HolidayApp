package com.example.holiday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Apply_for_leave extends AppCompatActivity {
    Manger1_list manger1_list;
    EditText date_1, date_2, date_3, date_4, date_5, date_6,reason ;
    Button applyleave;
    Member member;
    FirebaseDatabase database;

    String today_date,date_day1;
    String dates="",get;
    double sum=0;
    String sumtxt;
    RadioGroup radio_1, radio_2, radio_3, radio_4, radio_5, radio_6;
    RadioButton radioLeave1, radioLeave2, radioLeave3, radioLeave4, radioLeave5, radioLeave6;
    DatabaseReference ref,ref_manager1;
    final Calendar leaveCalender = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_leave);

        today_date= new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date()).toString();
        member = new Member();

        manger1_list=new Manger1_list();
        date_1 = findViewById(R.id.date_1);
        date_2 = findViewById(R.id.date_2);
        date_3 = findViewById(R.id.date_3);
        date_4 = findViewById(R.id.date_4);
        date_5 = findViewById(R.id.date_5);
        date_6 = findViewById(R.id.date_6);
        reason = findViewById(R.id.reason);
        applyleave = findViewById(R.id.applyleave);

        radio_1 = findViewById(R.id.radio_1);
        radio_2 = findViewById(R.id.radio_2);
        radio_3 = findViewById(R.id.radio_3);
        radio_4 = findViewById(R.id.radio_4);
        radio_5 = findViewById(R.id.radio_5);
        radio_6 = findViewById(R.id.radio_6);

        radio_1.setVisibility(View.GONE);
        radio_2.setVisibility(View.GONE);
        radio_3.setVisibility(View.GONE);
        radio_4.setVisibility(View.GONE);
        radio_5.setVisibility(View.GONE);
        radio_6.setVisibility(View.GONE);

        Bundle bundle = getIntent().getExtras();
        final String id = bundle.getString("id");

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                leaveCalender.set(Calendar.YEAR,year);
                leaveCalender.set(Calendar.MONTH,month);
                leaveCalender.set(Calendar.DAY_OF_MONTH, date);
                label1();

            }
        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                leaveCalender.set(Calendar.YEAR,year);
                leaveCalender.set(Calendar.MONTH,month);
                leaveCalender.set(Calendar.DAY_OF_MONTH, date);
                label2();
            }
        };

        final DatePickerDialog.OnDateSetListener date3 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                leaveCalender.set(Calendar.YEAR,year);
                leaveCalender.set(Calendar.MONTH,month);
                leaveCalender.set(Calendar.DAY_OF_MONTH, date);
                label3();
            }
        };
        final DatePickerDialog.OnDateSetListener date4 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                leaveCalender.set(Calendar.YEAR,year);
                leaveCalender.set(Calendar.MONTH,month);
                leaveCalender.set(Calendar.DAY_OF_MONTH, date);
                label4();
            }
        };

        final DatePickerDialog.OnDateSetListener date5 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                leaveCalender.set(Calendar.YEAR,year);
                leaveCalender.set(Calendar.MONTH,month);
                leaveCalender.set(Calendar.DAY_OF_MONTH, date);
                label5();
            }
        };

        final DatePickerDialog.OnDateSetListener date6 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                leaveCalender.set(Calendar.YEAR,year);
                leaveCalender.set(Calendar.MONTH,month);
                leaveCalender.set(Calendar.DAY_OF_MONTH, date);
                label6();
            }
        };
        date_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Apply_for_leave.this, date1, leaveCalender.get(Calendar.YEAR), leaveCalender.get(Calendar.MONTH), leaveCalender.get(Calendar.DAY_OF_MONTH)).show();

                radio_1.setVisibility(View.VISIBLE);

            }

        });

        date_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Apply_for_leave.this, date2, leaveCalender.get(Calendar.YEAR), leaveCalender.get(Calendar.MONTH), leaveCalender.get(Calendar.DAY_OF_MONTH)).show();

                radio_2.setVisibility(View.VISIBLE);

            }
        });

        date_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Apply_for_leave.this, date3, leaveCalender.get(Calendar.YEAR), leaveCalender.get(Calendar.MONTH), leaveCalender.get(Calendar.DAY_OF_MONTH)).show();

                radio_3.setVisibility(View.VISIBLE);

            }
        });

        date_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Apply_for_leave.this, date4, leaveCalender.get(Calendar.YEAR), leaveCalender.get(Calendar.MONTH), leaveCalender.get(Calendar.DAY_OF_MONTH)).show();
                ;
                radio_4.setVisibility(View.VISIBLE);

            }
        });

        date_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Apply_for_leave.this, date5, leaveCalender.get(Calendar.YEAR), leaveCalender.get(Calendar.MONTH), leaveCalender.get(Calendar.DAY_OF_MONTH)).show();
                radio_5.setVisibility(View.VISIBLE);

            }
        });

        date_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Apply_for_leave.this, date6, leaveCalender.get(Calendar.YEAR), leaveCalender.get(Calendar.MONTH), leaveCalender.get(Calendar.DAY_OF_MONTH)).show();
                radio_6.setVisibility(View.VISIBLE);
            }
        });

        //Radio button work


        applyleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkdate1();
                checkdate2();
                checkdate3();
                checkdate4();
                checkdate5();
                checkdate6();
                calculate();

                if(reason.getText().toString().length() > 0){
                    database = FirebaseDatabase.getInstance();
                    ref_manager1 = database.getReference().child("Manger1_list");
                    ref = database.getReference().child("Member").child(id);
                    ref.child("approve_m1").setValue("");
                    ref.child("approve_m2").setValue("");

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           // Log.d("Error checking", "" + dataSnapshot.getValue());
                            //Log.d("Sum null error", "" + sum);
                            sumtxt = String.valueOf(sum);
                           // Log.d("Sum null error --------", "" + sumtxt);
                            ref.child("sum").setValue(sumtxt);
                            Log.d("Sum null error @-0-0-", "" + sumtxt);
                            String manager1list_name = dataSnapshot.child("name").getValue().toString();
                            String manager1mail = dataSnapshot.child("manager_m1_email").getValue().toString();
                            String manager2mail = dataSnapshot.child("manager_m2_email").getValue().toString();
                            String manager1 = dataSnapshot.child("manager1").getValue().toString();
                            String manager2= dataSnapshot.child("manager2").getValue().toString();
                            String employee= dataSnapshot.child("employee").getValue().toString();
                            manger1_list.setName_m1(manager1list_name);
                            manger1_list.setManager_m1_email(manager1mail);
                            manger1_list.setManager_m1(manager1);
                            manger1_list.setDates_m1(dates);
                            manger1_list.setToday_date(today_date);
                            manger1_list.setApprove_m1("");
                            manger1_list.setApprove_m2("");
                            manger1_list.setDate_day1(date_day1);
                            manger1_list.setReason(reason.getText().toString());
                            manger1_list.setEmployee(employee);
                            manger1_list.setManager_m2(manager2);
                            manger1_list.setManager_m2_email(manager2mail);
                            ref_manager1.child(id).setValue(manger1_list);

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(Apply_for_leave.this,"Insufficient leaves",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

                    /*Intent intent=new Intent(Apply_for_leave.this,Main3Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);*/
                    Toast.makeText(Apply_for_leave.this,"Success",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
    private void label1(){
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_1.setText(sdf.format(leaveCalender.getTime()));
        date_day1 = sdf.format(leaveCalender.getTime());
        //dates+="1st day: "+leaveCalender.getTime().toString().substring(0,10)+"\n";
    }

    private void label2(){
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_2.setText(sdf.format(leaveCalender.getTime()));
        //dates+="2nd day: "+leaveCalender.getTime().toString().substring(0,10)+"\n";
    }

    private void label3(){
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_3.setText(sdf.format(leaveCalender.getTime()));
        //dates+="3rd day: "+leaveCalender.getTime().toString().substring(0,10)+"\n";
    }

    private void label4(){
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_4.setText(sdf.format(leaveCalender.getTime()));
       // dates+="4th day: "+leaveCalender.getTime().toString().substring(0,10)+"\n";
    }

    private void label5(){
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_5.setText(sdf.format(leaveCalender.getTime()));
       // dates+="5th day: "+leaveCalender.getTime().toString().substring(0,10)+"\n";
    }

    private void label6(){
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_6.setText(sdf.format(leaveCalender.getTime()));
       // dates+="6th day: "+leaveCalender.getTime().toString().substring(0,10)+"\n";
    }



    public void  checkdate1(){
        if(date_1.getText().toString().length() > 0) {
            int selectedID = radio_1.getCheckedRadioButtonId();
            radioLeave1 = (RadioButton) findViewById(selectedID);
        }
    }

    public void  checkdate2(){
        if(date_2.getText().toString().length() > 0) {
            int selectedID = radio_2.getCheckedRadioButtonId();
            radioLeave2 = (RadioButton) findViewById(selectedID);
        }
    }

    public void  checkdate3(){
        if(date_3.getText().toString().length() > 0) {
            int selectedID = radio_3.getCheckedRadioButtonId();
            radioLeave3 = (RadioButton) findViewById(selectedID);
        }
    }
    public void  checkdate4(){
        if(date_4.getText().toString().length() > 0) {
            int selectedID = radio_4.getCheckedRadioButtonId();
            radioLeave4 = (RadioButton) findViewById(selectedID);
        }
    }
    public void  checkdate5(){
        if(date_5.getText().toString().length() > 0) {
            int selectedID = radio_5.getCheckedRadioButtonId();
            radioLeave5 = (RadioButton) findViewById(selectedID);
        }
    }
    public void  checkdate6(){
        if(date_6.getText().toString().length() > 0) {
            int selectedID = radio_6.getCheckedRadioButtonId();
            radioLeave6 = (RadioButton) findViewById(selectedID);
        }
    }

    public void calculate()
    {
        int Short = 0;
        int half = 0;
        int full = 0;

        //1st date
        if(date_1.getText().length() > 0)
        {
            String radioT1 = String.valueOf(radioLeave1.getText());
            dates+=" 1st day: " + date_1.getText().toString() + "-" + radioT1;
            if (radioLeave1.getText().length() == 5)
            {
                Short++;
            }
            else if (radioLeave1.getText().length() == 9)
            {
                half++;
            }
            else if(radioLeave1.getText().length() == 8)
            {
                full++;
            }
            else {

            }
        }


        //2nd date
        if(date_2.getText().length() > 0) {
            dates+=" 2nd day: " + date_2.getText().toString() + "-" + radioLeave2.getText().toString();

            if (radioLeave2.getText().length() == 5) {
                Short++;
            } else if (radioLeave2.getText().length() == 9) {
                half++;
            } else if (radioLeave2.getText().length() == 8) {
                full++;
            } else {

            }
        }
        //3rd date
        if(date_3.getText().length() > 0) {
            dates+=" 3rd day: " + date_3.getText().toString() + "-" + radioLeave3.getText().toString();

            if (radioLeave3.getText().length() == 5) {
                Short++;
            } else if (radioLeave3.getText().length() == 9) {
                half++;
            } else if (radioLeave3.getText().length() == 8) {
                full++;
            } else {

            }
        }

        if(date_4.getText().length() > 0) {
            dates+=" 4th day: " + date_4.getText().toString() + "-" + radioLeave4.getText().toString();

            if (radioLeave4.getText().length() == 5) {
                Short++;
            } else if (radioLeave4.getText().length() == 9) {
                half++;
            } else if (radioLeave4.getText().length() == 8) {
                full++;
            } else {

            }
        }
        if(date_5.getText().length() > 0) {
            dates+=" 5th day: " + date_5.getText().toString() + "-" + radioLeave5.getText().toString();

            if (radioLeave5.getText().length() == 5) {
                Short++;
            } else if (radioLeave5.getText().length() == 9) {
                half++;
            } else if (radioLeave5.getText().length() == 8) {
                full++;
            } else {

            }
        }
        if(date_6.getText().length() > 0) {
            dates+=" 6th day: " + date_6.getText().toString() + "-" + radioLeave6.getText().toString();

            if (radioLeave6.getText().length() == 5) {
                Short++;
            } else if (radioLeave6.getText().length() == 9) {
                half++;
            } else if (radioLeave6.getText().length() == 8) {
                full++;
            } else {
            }
        }

        sum = (full) + (half * 0.5) + (Short*0.25);
        Log.d("Calculate", " " + sum);
        Toast.makeText(Apply_for_leave.this, " Full day " + full + " \n Half Day " + half + "\n Short " + Short,Toast.LENGTH_SHORT).show();
    }

}
