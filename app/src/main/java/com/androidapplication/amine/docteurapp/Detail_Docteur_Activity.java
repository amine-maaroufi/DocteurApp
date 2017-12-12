package com.androidapplication.amine.docteurapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail_Docteur_Activity extends AppCompatActivity implements View.OnClickListener {
    public ImageView btnloclaiser;
    private static final String TAG_LISTE = "docteur";
    private static final String TAG_NOM = "nom_prenom";
    private static final String TAG_SPECIALITE = "specialite";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_ADRESSE = "adresse";
    private static final String TAG_TEL = "tel";
    private static final String TAG_Education = "education";
    private static final String TAG_Latitude = "longitude";
    private static final String TAG_Longitude = "latitude";
    private static final String TAG_ID = "id";

    private TextView txtnom, txtspec, txtadr, txttel, txtdesc, txteduc;
    public String txtlatitude, txtlongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail__docteur_activity);

        //show icon in actionbar/toolbar***********
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);/*************/

        txtnom = (TextView) findViewById(R.id.txt_nom_doc);
        txtspec = (TextView) findViewById(R.id.txt_specialte);
        txtadr = (TextView) findViewById(R.id.txt_adresse);
        txttel = (TextView) findViewById(R.id.txt_tel);
        //txtdesc = (TextView) findViewById(R.id.txt_apropos);
        txteduc = (TextView) findViewById(R.id.txt_education);
        btnloclaiser = (ImageView)findViewById(R.id.btn_drivelocation);
        btnloclaiser.setOnClickListener(this);

        //recuperer intent
        //puis recuperer les donnees venant avec l'intent
        //et les afficher dans les textview

        txtnom.setText(getIntent().getStringExtra(TAG_NOM));
        txtspec.setText(getIntent().getStringExtra(TAG_SPECIALITE));
        txtadr.setText(getIntent().getStringExtra(TAG_ADRESSE));
        txttel.setText(getIntent().getStringExtra(TAG_TEL));
        //txtdesc.setText(getIntent().getStringExtra(TAG_DESCRIPTION));
        txteduc.setText(getIntent().getStringExtra(TAG_Education));
        txtlatitude = (getIntent().getStringExtra(TAG_Latitude));
        txtlongitude = (getIntent().getStringExtra(TAG_Longitude));

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
        /*if (id == R.id.Profil) {

            //le code lorsque je clique sur item home
            Intent i =new Intent(this,Profil_Activity.class);
            startActivity(i);
            return true;
        }*/

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

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_drivelocation)
        {
            Intent i =  new Intent(this,MapsActivity.class);
            i.putExtra(TAG_Latitude, txtlatitude);
            i.putExtra(TAG_Longitude, txtlongitude);
            i.putExtra(TAG_NOM, txtnom.getText());
            startActivity(i);
        }
    }
}
