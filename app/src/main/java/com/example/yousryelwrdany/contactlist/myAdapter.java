package com.example.yousryelwrdany.contactlist;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yousry Elwrdany on 16/03/2018.
 */

public class myAdapter extends ArrayAdapter<Person> {


    public myAdapter(@NonNull Context context, @NonNull List<Person> personList) {
        super(context, 0, personList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_listview,parent,false);

        TextView name = convertView.findViewById(R.id.txtName);
        TextView phone = convertView.findViewById(R.id.txtPhone);

        Person personList = getItem(position);

        name.setText(personList.getName());
        phone.setText(personList.getPhone());

        return convertView;
    }
}
