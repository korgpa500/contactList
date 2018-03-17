package com.example.yousryelwrdany.contactlist;

/**
 * Created by Yousry Elwrdany on 16/03/2018.
 */

public class Person {

    private int mId;
    private String mName;
    private String mPhone;


    public Person(int id, String name, String phone) {
        mId = id;
        mName = name;
        mPhone = phone;
    }


    public Person(String name, String phone) {
        mName = name;
        mPhone = phone;
    }


    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }
}
