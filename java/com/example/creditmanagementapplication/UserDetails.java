package com.example.creditmanagementapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class UserDetails extends AppCompatActivity {

    String[] userSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // Getting the object of the data passed
        Bundle b = this.getIntent().getExtras();

        if(b==null){
            return;
        }

        userSelected = b.getStringArray("userSelected");

        ListAdapter myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userSelected);
        ListView myListView = (ListView)findViewById(R.id.userDetailsList);
        myListView.setAdapter(myAdapter);

    }

    public void transfer(View view){
        Intent i = new Intent(this,transferCredits.class);
        i.putExtra("userSelected",userSelected);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
