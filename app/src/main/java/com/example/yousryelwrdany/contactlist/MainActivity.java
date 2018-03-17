package com.example.yousryelwrdany.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Person> personList;
    ListView contactList;
    myAdapter adapter;
    Button btnAdd;
    Intent intent;
    myHelper helper;
    EditText searchContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new myHelper(MainActivity.this);

        personList = helper.getAllContact();

        contactList = findViewById(R.id.contactList);
        adapter = new myAdapter(MainActivity.this,personList);
        contactList.setAdapter(adapter);

        searchContact = findViewById(R.id.searchContact);
        searchContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                personList = helper.getOneContact(searchContact.getText().toString());
                adapter = new myAdapter(MainActivity.this,personList);
                contactList.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Person onePerson = (Person)parent.getItemAtPosition(position);
                intent = new Intent(MainActivity.this,UpdateContact.class);
                intent.putExtra("id",onePerson.getId());
                startActivity(intent);
                finish();
            }
        });

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,AddContact.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
