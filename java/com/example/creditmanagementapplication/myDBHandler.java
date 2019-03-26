package com.example.creditmanagementapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBHandler extends SQLiteOpenHelper {
    // Creates the database
    public myDBHandler(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Users.db", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR(255),email VARCHAR(255), credits MEDIUMINT);");
            db.execSQL("INSERT OR REPLACE INTO users VALUES ('Katelyn Duncan','nulla@scelerisque.org',247),('Grant Lucas','egestas.a@in.co.uk',897),('Damon Barr','aliquam@CurabiturdictumPhasellus.edu',376),('Hector Patrick','Duis.ac@rhoncusProinnisl.ca',946),('Belle Norman','Proin@sed.net',730),('Tate Morris','primis.in@enimNunc.org',672),('Alec Pierce','Proin@massa.org',279),('Zelda Schultz','Sed.auctor.odio@euismod.org',394),('Iona Patton','sagittis@vitaealiquam.edu',981),('Vivien Henry','ante@Nuncmaurissapien.org',577);");
            db.execSQL("CREATE TABLE IF NOT EXISTS transfers(fromName VARCHAR(255),toName VARCHAR(255), creditsChanged MEDIUMINT);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    // Display all the data of table
    public String[] DisplayName(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor results = db.rawQuery("SELECT name FROM users",null);
        results.moveToFirst();
        String[] result=new String[10];
        int i=0;
        while(!results.isAfterLast()){
            if(results.getString(results.getColumnIndex("name"))!=null)
            {
                result[i]=results.getString(results.getColumnIndex("name"));
                i++;
             }
             results.moveToNext();
        }
        db.close();
        results.close();
        return result;
    }

    public String[] DisplayDetails(String nameSelected){
        SQLiteDatabase db = getWritableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM users WHERE name = '" + nameSelected + "';",null);
        results.moveToFirst();
        String[] result=new String[3];
        result[0]="Name: " + results.getString(0);
        result[1]="Email: " + results.getString(1);
        result[2]="Credits: " + results.getString(2);
        db.close();
        results.close();
        return result;
    }

    public void transferCredits(String name1, String name2, int creditChange){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO transfers VALUES ('" + name1 +"','"+ name2 + "'," + creditChange+ ");");
        Cursor fromResult = db.rawQuery("SELECT * FROM users WHERE name = '" + name1 + "';",null);
        Cursor toResult = db.rawQuery("SELECT * FROM users WHERE name = '" + name2 + "';",null);
        fromResult.moveToFirst();
        toResult.moveToFirst();
        int fromCredit=Integer.parseInt(fromResult.getString(2))-creditChange;
        int toCredit=Integer.parseInt(toResult.getString(2))+creditChange;
        db.execSQL("UPDATE users SET credits = " + fromCredit + " WHERE name = '" + name1 + "'");
        db.execSQL("UPDATE users SET credits = " + toCredit + " WHERE name = '" + name2 + "'");
        db.close();
        fromResult.close();
        toResult.close();
    }
}