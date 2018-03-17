package com.example.yousryelwrdany.contactlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContact extends AppCompatActivity {

    EditText textEditName;
    EditText textEditPhone;
    myHelper db;
    Button btnUpdate;
    Intent intent;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        textEditName = findViewById(R.id.txtEditName);
        textEditPhone = findViewById(R.id.txtEditPhone);
        id= getIntent().getIntExtra("id",0);

        db = new myHelper(UpdateContact.this);
        Person onePerson = db.getContactById(id);

        textEditName.setText(onePerson.getName());
        textEditPhone.setText(onePerson.getPhone());

        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updateContact(new Person(id,textEditName.getText().toString(),textEditPhone.getText().toString()));
                intent = new Intent(UpdateContact.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(UpdateContact.this,"Contact Updated successfully",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.btnDelete:
                showAlert();
                break;
            case R.id.btnCancelUpdate:
                intent = new Intent(UpdateContact.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(UpdateContact.this,"You do not Update Contact . . . ",Toast.LENGTH_LONG).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlert(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete contact
                        db.deleteContact(id);
                        intent = new Intent(UpdateContact.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(UpdateContact.this,"Contact is Deleted . . . ",Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
