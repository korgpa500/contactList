package com.example.yousryelwrdany.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    EditText txtAddName;
    EditText txtAddPhone;
    Button btnSave;
    Button btnCancel;
    Intent intent;
    myHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        txtAddName = findViewById(R.id.txtAddName);
        txtAddPhone = findViewById(R.id.txtAddPhone);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper = new myHelper(AddContact.this);
                helper.addContact(new Person(txtAddName.getText().toString(),txtAddPhone.getText().toString()));
                Toast.makeText(AddContact.this,"Successfully Added",Toast.LENGTH_LONG).show();
                intent = new Intent(AddContact.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddContact.this,"You are canceled Saving,no Contact Saved . . . . . ",Toast.LENGTH_LONG).show();
                intent = new Intent(AddContact.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
