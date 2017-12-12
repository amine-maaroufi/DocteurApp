package com.androidapplication.amine.docteurapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.androidapplication.amine.docteurapp.DataBase.DatabaseHelper;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox check_session;
    private Button btn1,btn2;
    private EditText a, b;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //show icon in actionbar/toolbar***********
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);/*************/


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sh.edit();

        a = (EditText) findViewById(R.id.username);
        b = (EditText) findViewById(R.id.password);
        btn1 = (Button) findViewById(R.id.login);
        btn2 = (Button) findViewById(R.id.signup);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        check_session = (CheckBox) findViewById(R.id.checkBox);


        String login = sh.getString("login", null);
        String password = sh.getString("password", null);
        Boolean check = sh.getBoolean("check", false);


        if (check) {
            a.setText(login.toString());
            b.setText(password.toString());
            check_session.setChecked(true);
        } else {
            a.setText("");
            b.setText("");
            check_session.setChecked(false);
        }
    }

    @Override
    public void onClick(View view) {

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sh.edit();


        if (view.getId() == R.id.login) {


            if (check_session.isChecked()) {
                edit.putString("login", a.getText().toString());
                edit.putString("password", b.getText().toString());
                edit.putBoolean("check", true);
                edit.commit();

            } else {

                edit.putBoolean("check", false);
                edit.commit();
            }


            String str = a.getText().toString();
            String pass = b.getText().toString();

            String password = helper.searchPass(str);
            if (pass.equals(password)) {
                Intent i = new Intent(this, Menu_Activity.class);
                startActivity(i);
            } else {
                Toast temp = Toast.makeText(this, "Check Your Informations", Toast.LENGTH_SHORT);
                temp.show();
            }


        }
        if (view.getId() == R.id.signup) {
            Intent i = new Intent(this, Signup_Activity.class);
            startActivity(i);

        }

        if (view.getId() == R.id.btn_demande) {
            Intent i = new Intent(this, Demande_docteur.class);
            startActivity(i);

        }
    }
}
