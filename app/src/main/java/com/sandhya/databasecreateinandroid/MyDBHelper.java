package com.sandhya.databasecreateinandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contactDB";
    private static final int DATABASE_VERSION = 1;

    //Contact Table
    private static final String TABLE_CONTACT = "Contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "Phone_No";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Contact table
        //CREATE TABLE Contacts(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,phone_no TEXT)
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_CONTACT+"("+ KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_NAME +" TEXT,"+ KEY_PHONE_NO +" TEXT "+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+TABLE_CONTACT);
        onCreate(sqLiteDatabase);
    }

    //insert Query
    public void addContacts(String name,String phone_no){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_PHONE_NO,phone_no);
        database.insert(TABLE_CONTACT,null,values);
    }

    //select Query
    public ArrayList<ContactModel> fetchContacts(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_CONTACT,null);
        ArrayList<ContactModel> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            ContactModel model = new ContactModel();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.phone_no = cursor.getString(2);
            arrayList.add(model);
        }
        return arrayList;
    }

    //update Query
    public void updateContacts(ContactModel contactModel){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contactModel.name);
        values.put(KEY_PHONE_NO,contactModel.phone_no);
        database.update(TABLE_CONTACT,values,KEY_ID+" = "+contactModel.id,null);
    }

    //delete Query
    public void deleteContect(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_CONTACT,KEY_ID+"=?",new String[]{String.valueOf(id)});
    }
}
