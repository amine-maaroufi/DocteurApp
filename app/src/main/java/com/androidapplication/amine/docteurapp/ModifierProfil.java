package com.androidapplication.amine.docteurapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidapplication.amine.docteurapp.DataBase.DatabaseHelper;
import com.androidapplication.amine.docteurapp.DataBase.Members;

/**
 * Created by Allah on 26/03/2017.
 */

public class ModifierProfil extends DatabaseHelper {
    public ModifierProfil(Context context) {
        super(context);
    }

    public boolean update(Members member) {

        ContentValues values = new ContentValues();

        values.put("nom", member.nom);
        values.put("prenom", member.prenom);
        values.put("email", member.email);
        values.put("pseudo", member.pseudo);
        values.put("pass", member.pass);
        values.put("tel", member.tel);

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(member.id) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("members", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;

    }


    public Members readSingleRecord() {

        Members member = null;

        String sql = "SELECT * FROM members ";/*WHERE id = " + ChargeId;*/

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String nom = cursor.getString(cursor.getColumnIndex("nom"));
            String prenom = cursor.getString(cursor.getColumnIndex("prenom"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String pseudo = cursor.getString(cursor.getColumnIndex("pseudo"));
            String pass = cursor.getString(cursor.getColumnIndex("pass"));
            String tel = cursor.getString(cursor.getColumnIndex("tel"));

            member = new Members();
            member.id = id;
            member.nom = nom;
            member.prenom = prenom;
            member.email = email;
            member.pseudo = pseudo;
            member.pass = pass;
            member.tel = tel;

        }

        cursor.close();
        db.close();

        return member;

    }


}
