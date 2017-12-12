package com.androidapplication.amine.docteurapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Allah on 23/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Docteur_bd.db";
    private static final String TABLE_members_NAME = "members";
    private static final String members_COLUMN_ID = "id";
    private static final String members_COLUMN_NOM = "nom";
    private static final String members_COLUMN_PRENOM = "prenom";
    private static final String members_COLUMN_EMAIL = "email";
    private static final String members_COLUMN_PSEUDO = "pseudo";
    private static final String members_COLUMN_PASS = "pass";
    private static final String members_COLUMN_Tel = "tel";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table members (id integer primary key not null , " +
            "nom text not null ,prenom text not null , email text not null , pseudo text not null , pass text not null, tel text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertMember(Members m) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from members";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();

        values.put(members_COLUMN_ID , count);
        values.put(members_COLUMN_NOM, m.getNom());
        values.put(members_COLUMN_PRENOM, m.getPrenom());
        values.put(members_COLUMN_EMAIL, m.getEmail());
        values.put(members_COLUMN_PSEUDO, m.getPseudo());
        values.put(members_COLUMN_PASS, m.getPass());
        values.put(members_COLUMN_Tel, m.getTel());

        db.insert(TABLE_members_NAME, null, values);
        db.close();
    }


    public String searchPass(String pseudo)
    {
        db = this.getReadableDatabase();
        String query = "select pseudo, pass from "+TABLE_members_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(pseudo))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_members_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
