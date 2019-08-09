package com.example.holiday;

public class Manger1_list {
   private String name_m1,manager_m1,manager_m1_email,dates_m1,today_date,manager_m2,manager_m2_email,approve_m1,
    approve_m2,employee,reason,date_day1;
    public Manger1_list()
    {}
    public Manger1_list(String name_m1,String manager_m1,String manager_m1_email,String dates_m1,String today_date
    ,String manager_m2,String manager_m2_email,String approve_m1,String approve_m2,String employee,String reason
            ,String date_day1)
    {
        this.name_m1=name_m1;
        this.manager_m1=manager_m1;
        this.manager_m1_email=manager_m1_email;
        this.dates_m1=dates_m1;
        this.today_date=today_date;
        this.manager_m2=manager_m2;
        this.manager_m2_email=manager_m2_email;
        this.approve_m1=approve_m1;
        this.approve_m2=approve_m2;
        this.employee=employee;
        this.reason=reason;
        this.date_day1=date_day1;
       }

    public String getName_m1() {
        return name_m1;
    }

    public void setName_m1(String name_m1) {
        this.name_m1 = name_m1;
    }

    public String getManager_m1() {
        return manager_m1;
    }

    public void setManager_m1(String manager_m1) {
        this.manager_m1 = manager_m1;
    }

    public String getManager_m1_email() {
        return manager_m1_email;
    }

    public void setManager_m1_email(String manager_m1_email) {
        this.manager_m1_email = manager_m1_email;
    }

    public String getDates_m1() {
        return dates_m1;
    }

    public void setDates_m1(String dates_m1) {
        this.dates_m1 = dates_m1;
    }

    public String getToday_date() {
        return today_date;
    }

    public void setToday_date(String today_date) {
        this.today_date = today_date;
    }

    public String getManager_m2() {
        return manager_m2;
    }

    public void setManager_m2(String manager_m2) {
        this.manager_m2 = manager_m2;
    }

    public String getManager_m2_email() {
        return manager_m2_email;
    }

    public void setManager_m2_email(String manager_m2_email) {
        this.manager_m2_email = manager_m2_email;
    }

    public String getApprove_m1() {
        return approve_m1;
    }

    public void setApprove_m1(String approve_m1) {
        this.approve_m1 = approve_m1;
    }

    public String getApprove_m2() {
        return approve_m2;
    }

    public void setApprove_m2(String approve_m2) {
        this.approve_m2 = approve_m2;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate_day1() {
        return date_day1;
    }

    public void setDate_day1(String date_day1) {
        this.date_day1 = date_day1;
    }


}
