package com.example.yousryelwrdany.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Yousry Elwrdany on 16/03/2018.
 */

public class myHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myDataBase";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "contact";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";



    public myHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TableCreation = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" integer primary key,"+KEY_NAME+" varchar(50),"+KEY_PHONE+" varchar(50))";
        db.execSQL(TableCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTable = "drop table " + TABLE_NAME + " IF EXISTS";
        db.execSQL(dropTable);
        onCreate(db);
    }

    public void addContact(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,person.getName());
        values.put(KEY_PHONE,person.getPhone());
        db.insert(TABLE_NAME,null,values);
    }

    public ArrayList<Person> getAllContact(){
        ArrayList<Person> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" ORDER BY "+KEY_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                int ContactId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(KEY_PHONE));
                contacts.add(new Person(ContactId,name,phone));
            }while (cursor.moveToNext());
        }
        return contacts;
    }

    public ArrayList<Person> getOneContact(String val){
        ArrayList<Person> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_NAME+" LIKE '%"+val+"%'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                int ContactId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(KEY_PHONE));
                contacts.add(new Person(ContactId,name,phone));
            }while (cursor.moveToNext());
        }
        return contacts;
    }

    public Person getContactById(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID+"="+id;
        Cursor cursor = db.rawQuery(query,null);

        Person person = null;
        if(cursor.moveToFirst()){
            int ContactId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String ContactName = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String ContactPhone = cursor.getString(cursor.getColumnIndex(KEY_PHONE));
            person = new Person(ContactId,ContactName,ContactPhone);
        }

        return person;
    }

    public void updateContact(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME ,person.getName());
        values.put(KEY_PHONE,person.getPhone());
        db.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(person.getId())});
    }


    public void deleteContact(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }

}
