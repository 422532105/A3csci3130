package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {
    Button buttonCreate1;
    DatabaseReference mReference;
    ListView listView;

    Data data;
    private FirebaseListAdapter<Data> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new Data();
        buttonCreate1 = (Button) findViewById(R.id.buttonCreate1);
        listView = (ListView) findViewById(R.id.listView);

        buttonCreate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        mReference = FirebaseDatabase.getInstance().getReference("contacts");
        adapter = new FirebaseListAdapter<Data>(this, Data.class,
                android.R.layout.simple_list_item_1, mReference) {
            @Override
            protected void populateView(View v, Data model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//read name into list
                Data person = (Data) adapter.getItem(position);
                showDetailView(person);
            }
        });

    }
    private void showDetailView(Data name)//learn from sample example
    {
        Intent intent = new Intent(this, Main3Activity.class);
        intent.putExtra("Contact", name);
        startActivity(intent);
    }




}