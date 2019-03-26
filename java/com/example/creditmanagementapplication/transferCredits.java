package com.example.creditmanagementapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class transferCredits extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    myDBHandler dbHandler;
    String nameSelected;
    EditText creditsEditText;
    String[] userSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_credits);

        Bundle b = this.getIntent().getExtras();

        if(b==null){
            return;
        }

        userSelected = b.getStringArray("userSelected");

        dbHandler = new myDBHandler(this,null,null,1);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dbHandler.DisplayName());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        creditsEditText = (EditText)findViewById(R.id.editText);

        }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nameSelected = String.valueOf(parent.getItemAtPosition(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void creditsChange(View view){

        if(nameSelected.equals(userSelected[0].substring(6))){
            Toast.makeText(this,"Please Select Another User", Toast.LENGTH_LONG).show();
        }
        else if(creditsEditText.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Please Enter all the Information", Toast.LENGTH_LONG).show();
        }

        else if(Integer.parseInt(creditsEditText.getText().toString())>Integer.parseInt(userSelected[2].substring(9))){
            Toast.makeText(getApplicationContext(),"Please Enter Valid Credits", Toast.LENGTH_LONG).show();
        }

        else{
            dbHandler.transferCredits(userSelected[0].substring(6),nameSelected,Integer.parseInt(creditsEditText.getText().toString()));
            Toast.makeText(getApplicationContext(),"Transfer Successful", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,ViewUsers.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }
}
