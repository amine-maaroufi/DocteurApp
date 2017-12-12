package com.androidapplication.amine.docteurapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Menu_Activity extends AppCompatActivity implements View.OnClickListener {

    //private Button btn;
    private ImageView imgEyes,imgDentiste,imgCardio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        //show icon in actionbar/toolbar***********
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);/*************/

        imgEyes = (ImageView) findViewById(R.id.catg1);
        imgEyes.setOnClickListener(this);
        imgDentiste = (ImageView) findViewById(R.id.catg2);
        imgDentiste.setOnClickListener(this);
        imgCardio = (ImageView) findViewById(R.id.catg3);
        imgCardio.setOnClickListener(this);
       // btn = (Button) findViewById(R.id.button);
       // btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.catg3)
        {
            Intent i = new Intent(this, List_Docteur_Cardio.class);
            startActivity(i);
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
        if (id == R.id.Logout) {

            //le code lorsque je clique sur item home
            Intent i =new Intent(Menu_Activity.this,Login_Activity.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
