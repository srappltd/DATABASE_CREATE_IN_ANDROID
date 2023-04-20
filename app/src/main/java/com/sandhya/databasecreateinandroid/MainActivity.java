package com.sandhya.databasecreateinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtContact;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtContact = findViewById(R.id.txtContact);

    }

    public void initInsertQuery(){
        MyDBHelper myDBHelper = new MyDBHelper(this);
        myDBHelper.addContacts("Abhay Gautam","6260396658");
        myDBHelper.addContacts("Ajay Gautam","626023234");
        myDBHelper.addContacts("Alok","12345658");
        myDBHelper.addContacts("Sundram","62603234558");
        myDBHelper.addContacts("Shivam","626023876558");
        myDBHelper.addContacts("Vikash Gautam","7654323396658");
        myDBHelper.addContacts("Ankit Gautam","7654346658");
        myDBHelper.addContacts("Badal Gautam","987650396658");
        myDBHelper.addContacts("Nandu Gautam","7656796658");
        myDBHelper.addContacts("CV Gautam","98763396658");
    }
    public void initSelectQuery(){
        //select Query
        MyDBHelper myDBHelper = new MyDBHelper(this);
        ArrayList<ContactModel> models = myDBHelper.fetchContacts();
        txtContact.setText("ID: "+models.get(0).id+" ,Name: "+models.get(0).name+" ,Phone: "+models.get(0).phone_no);
        for (int i=0;i<models.size();i++){
            Log.d("Contacts","ID: "+models.get(i).id+" ,Name: "+models.get(i).name+" ,Phone: "+models.get(i).phone_no);
            txtContact.setText("ID: "+models.get(i).id+" ,Name: "+models.get(i).name+" ,Phone: "+models.get(i).phone_no);
        }
    }
    public void initUpdateQuery(){
        MyDBHelper myDBHelper = new MyDBHelper(this);
        ContactModel model = new ContactModel();
        model.id = 1;
        model.name = "Aman Gautam";
        model.phone_no= "123456765432";
        myDBHelper.updateContacts(model);
    }
    public void initDeletedQuery(){
        //delete Query
        MyDBHelper myDBHelper = new MyDBHelper(this);
        myDBHelper.deleteContect(2);
        myDBHelper.deleteContect(4);
    }
}