package com.androidapplication.amine.docteurapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidapplication.amine.docteurapp.DataBase.Members;

public class Profil_Activity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    final ModifierProfil modifierProfil = new ModifierProfil(context);
    Members members = modifierProfil.readSingleRecord();

    private Button btn;
    private EditText nom,prenom, pseudo, pass1, pass2, email, tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_activity);

        //show icon in actionbar/toolbar***********
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);/*************/

        //show icon in actionbar/toolbar***********
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);/*************/

        nom = (EditText)findViewById(R.id.updatefisrtname);
        prenom = (EditText)findViewById(R.id.updatelastname);
        pseudo = (EditText)findViewById(R.id.updateusername);
        pass1 = (EditText)findViewById(R.id.updatepassword);
        pass2 = (EditText)findViewById(R.id.updateconfirm_password);
        email = (EditText)findViewById(R.id.updateemail);
        tel = (EditText)findViewById(R.id.updatephone);
        btn = (Button) findViewById(R.id.btnupdate) ;
        btn.setOnClickListener(this);

        nom.setText(members.nom);
        prenom.setText(members.prenom);
        email.setText(members.email);
        pseudo.setText(members.pseudo);
        pass1.setText(members.pass);
        pass2.setText(members.pass);
        tel.setText(members.tel);


    }

    @Override
    public void onClick(View view) {
        boolean updateSuccessful = modifierProfil.update(members);

        Members members = new Members();
        //members.id = ChargeId;
        members.nom = nom.getText().toString();
        members.prenom = prenom.getText().toString();
        members.email = email.getText().toString();
        members.pseudo = pseudo.getText().toString();
        members.pass = pass1.getText().toString();
        members.tel = tel.getText().toString();

        if(updateSuccessful){
            Toast.makeText(context, "Modification avec succée.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Impossible de modifier le profil.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Profil) {

            //le code lorsque je clique sur item home
            Intent i =new Intent(this,Profil_Activity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.categorie) {

            //le code lorsque je clique sur item home
            Intent i =new Intent(this,Menu_Activity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.Logout) {

            //le code lorsque je clique sur item home
            Intent i =new Intent(this,Login_Activity.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
