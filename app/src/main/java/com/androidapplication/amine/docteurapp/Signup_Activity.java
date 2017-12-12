package com.androidapplication.amine.docteurapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidapplication.amine.docteurapp.DataBase.DatabaseHelper;
import com.androidapplication.amine.docteurapp.DataBase.Members;

public class Signup_Activity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper helper = new DatabaseHelper(this);
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        //show icon in actionbar/toolbar***********
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);/*************/

        btn =(Button) findViewById(R.id.btnsignupformm);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()== R.id.btnsignupformm)
        {
            EditText nom = (EditText)findViewById(R.id.etsignupfisrtname);
            EditText prenom = (EditText)findViewById(R.id.etsignuplastname);
            EditText pseudo = (EditText)findViewById(R.id.etsignupusername);
            EditText pass1 = (EditText)findViewById(R.id.etsignuppassword);
            EditText pass2 = (EditText)findViewById(R.id.et_confirm_password);
            EditText email = (EditText)findViewById(R.id.etsignupemail);
            EditText tel = (EditText)findViewById(R.id.etsignupphone);

            String nomstr = nom.getText().toString();
            String prenomstr = prenom.getText().toString();
            String emailstr = email.getText().toString();
            String pseudostr = pseudo.getText().toString();
            String telstr = tel.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            /**teste sur la correspandence de mots de passe*/
            if(!pass1str.equals(pass2str))
            {
                //popup msg
                Toast pass = Toast.makeText(this , "Password mis match" , Toast.LENGTH_SHORT);
                pass.show();
            }
            /**teste sur les champs vide*/
            else if(nom.getText().length() == 0)
            {
                Toast t= Toast.makeText(getApplicationContext(),"Please check your FirstName",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 300);
                t.show();
            }
            else if (prenom.getText().length() == 0)
            {
                Toast t= Toast.makeText(getApplicationContext(),"Please check your LastName",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 320);
                t.show();
            }
            else if (email.getText().length() == 0)
            {
                Toast t= Toast.makeText(getApplicationContext(),"Please check your email",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 340);
                t.show();
            }
            else if (pseudo.getText().length() == 0)
            {
                Toast t= Toast.makeText(getApplicationContext(),"Please check your Login",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 360);
                t.show();
            }
            else if (pass1.getText().length() == 0)
            {
                Toast t= Toast.makeText(getApplicationContext(),"Please check your password",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 380);
                t.show();
            }
            else
            {
                //insert the detailes in database
                Members m = new Members();
                m.setNom(nomstr);
                m.setPrenom(prenomstr);
                m.setEmail(emailstr);
                m.setTel(telstr);
                m.setPseudo(pseudostr);
                m.setPass(pass1str);

                helper.insertMember(m);

                Intent i = new Intent(this, Login_Activity.class);
                startActivity(i);

                Toast reussi = Toast.makeText(this , "Succed Inscription" , Toast.LENGTH_SHORT);
                reussi.show();
            }

        }
    }
}
