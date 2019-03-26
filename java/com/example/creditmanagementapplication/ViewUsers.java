package com.example.creditmanagementapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ViewUsers extends AppCompatActivity {

    myDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        dbHandler = new myDBHandler(this,null,null,1);

        ListAdapter myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dbHandler.DisplayName());
        ListView myListView = (ListView)findViewById(R.id.usersList);
        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String nameSelected = String.valueOf(parent.getItemAtPosition(position));
                        String[] userSelected = dbHandler.DisplayDetails(nameSelected);
                        Bundle b=new Bundle();
                        b.putStringArray("userSelected", userSelected);
                        Intent i = new Intent(view.getContext(),UserDetails.class);
                        i.putExtras(b);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
        );

    }



}
