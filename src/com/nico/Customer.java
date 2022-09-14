package com.nico;

public class Customer {
    private String name;
    private String ssn;
    private String paidDate;
    private int sincePaidDate;
    private boolean isMember;

    public Customer(String name, String ssn, String paidDate, int sincePaidDate) {
        this.name = name;
        this.ssn = ssn;
        this.paidDate = paidDate;
        this.sincePaidDate = sincePaidDate;
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public int getSincePaidDate() {
        return sincePaidDate;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }
}
