package com.example.holiday;

public class Member {

  private String name,email,pass,manager1,manager2,daysleft,installmentleft,employee,manager_m1_email,manager_m2_email,approve_m1,approve_m2,sum;
    public Member() {
    }

    public String getDaysleft() {
        return daysleft;
    }

    public void setDaysleft(String daysleft) {
        this.daysleft = daysleft;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getInstallmentleft() {
        return installmentleft;
    }

    public void setInstallmentleft(String installmentleft) {
        this.installmentleft = installmentleft;
    }


    public Member(String name, String pass,
                  String manager1, String manager2, String daysleft,
                  String installmentleft, String employee,String email,
                  String manager_m1_email,String manager_m2_email,String approve_m2,String approve_m1
    ,String sum) {
        this.name=name;
        this.email=email;
        this.pass=pass;
        this.manager1=manager1;
        this.manager2=manager2;
        this.daysleft = daysleft;
        this.installmentleft = installmentleft;
        this.employee = employee;
        this.manager_m1_email=manager_m1_email;
        this.manager_m2_email=manager_m2_email;
        this.approve_m2=approve_m2;
        this.approve_m1=approve_m1;
        this.sum=sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getManager1() {
        return manager1;
    }

    public void setManager1(String manager1) {
        this.manager1 = manager1;
    }

    public String getManager2() {
        return manager2;
    }

    public void setManager2(String manager2) {
        this.manager2 = manager2;
    }

    public String getManager_m1_email() {
        return manager_m1_email;
    }

    public void setManager_m1_email(String manager_m1_email) {
        this.manager_m1_email = manager_m1_email;
    }

    public String getManager_m2_email() {
        return manager_m2_email;
    }

    public void setManager_m2_email(String manager_m2_email) {
        this.manager_m2_email = manager_m2_email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApprove_m2() {
        return approve_m2;
    }

    public void setApprove_m2(String approve_m2) {
        this.approve_m2 = approve_m2;
    }

    public String getApprove_m1() {
        return approve_m1;
    }

    public void setApprove_m1(String approve_m1) {
        this.approve_m1 = approve_m1;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
