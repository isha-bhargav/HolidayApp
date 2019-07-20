package com.example.holiday;

public class Member {
    private String name,email,pass,manager1,manager2;
    public Member() {
    }

    public Member(String name,String email,String pass,String manager1,String manager2) {
        this.name=name;
        this.email=email;
        this.pass=pass;
        this.manager1=manager1;
        this.manager2=manager2;
         }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
