package com.androidapplication.amine.docteurapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.androidapplication.amine.docteurapp.DataBase.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class List_Docteur_Yeux extends AppCompatActivity {

    ListView listview;

    // liste JSONArray
    JSONArray liste_docteurs = null;

    public ArrayList<HashMap<String, String>> DocteursList;
    private ProgressDialog pDialog;

    private static final String URL_LISTE_DOCTEURS = "http://169.254.197.128/docteur_serveur/get_liste_docteur_yeux.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_docteur_yeux_activity);

        //show icon in actionbar/toolbar***********
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);/*************/

        listview = (ListView) findViewById(R.id.listview_docteur);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> listitem = DocteursList.get(position);

                //recuperer les donnees de la position cliquee
                String NOM = listitem.get(TAG_NOM);
                String SPEC = listitem.get(TAG_SPECIALITE);
                String DESC = listitem.get(TAG_DESCRIPTION);
                String ADR = listitem.get(TAG_ADRESSE);
                String TEL = listitem.get(TAG_TEL);
                String Educ = listitem.get(TAG_Education);
                String latitude = listitem.get(TAG_Latitude);
                String longitude = listitem.get(TAG_Longitude);

                //transferer les donnees vers l'activite détail
                Intent intent_detail = new Intent(getApplicationContext(), Detail_Docteur_Activity.class);
                intent_detail.putExtra(TAG_NOM, NOM);
                intent_detail.putExtra(TAG_SPECIALITE, SPEC);
                intent_detail.putExtra(TAG_DESCRIPTION, DESC);
                intent_detail.putExtra(TAG_ADRESSE, ADR);
                intent_detail.putExtra(TAG_TEL, TEL);
                intent_detail.putExtra(TAG_Education, Educ);
                intent_detail.putExtra(TAG_Latitude, latitude);
                intent_detail.putExtra(TAG_Longitude, longitude);

                //afficher activite detail
                startActivity(intent_detail);
            }
        });


        // recuperer actualites in Background Thread
        new ListDocteursAsync().execute();
    }



    //async arriere plan pour récuperer users
    class ListDocteursAsync extends AsyncTask<String, String, JSONObject> {
        /**
         * Before starting background thread Show Progress Dialog
         */
        JSONParser jsonParser = new JSONParser();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(List_Docteur_Yeux.this);
            pDialog.setMessage("Recuperer liste des docteurs ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected JSONObject doInBackground(String... args) {
            JSONObject json = null;
            try {
                HashMap<String, String> params = new HashMap<>();
                json = jsonParser.makeHttpRequest(URL_LISTE_DOCTEURS, "GET", params);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;
        }

        protected void onPostExecute(JSONObject json) {


            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }


            // Hashmap for ListView
            DocteursList = new ArrayList<HashMap<String, String>>();
            try {

                if (json != null) {
                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        // todos found
                        // Getting Array of todos
                        Log.v("TEST", "json = " + json.toString());

                        liste_docteurs = json.getJSONArray(TAG_LISTE);

                        // looping through All users
                        for (int i = 0; i < liste_docteurs.length(); i++) {
                            JSONObject c = liste_docteurs.getJSONObject(i);

                            // Storing each json item in variable
                            String nom = c.getString(TAG_NOM);
                            String spec = c.getString(TAG_SPECIALITE);
                            String id= c.getString(TAG_ID);
                            String desc= c.getString(TAG_DESCRIPTION);
                            String adr= c.getString(TAG_ADRESSE);
                            String tel= c.getString(TAG_TEL);
                            String educ= c.getString(TAG_Education);
                            String latitude = c.getString(TAG_Latitude);
                            String longitude = c.getString(TAG_Longitude);

                            // creating new HashMap
                            HashMap<String, String> map = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            map.put(TAG_ID, id);
                            map.put(TAG_NOM, nom);
                            map.put(TAG_SPECIALITE, spec);
                            map.put(TAG_DESCRIPTION,desc);
                            map.put(TAG_ADRESSE,adr);
                            map.put(TAG_TEL,tel);
                            map.put(TAG_Education,educ);
                            map.put(TAG_Latitude,latitude);
                            map.put(TAG_Longitude,longitude);

                            // adding HashList to ArrayList
                            DocteursList.add(map);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            SimpleAdapter adapter = new SimpleAdapter(
                    List_Docteur_Yeux.this, DocteursList,
                    R.layout.list_itemm, new String[]{TAG_NOM, TAG_SPECIALITE},
                    new int[]{R.id.txtTitre, R.id.txtsousTitre});

            listview.setAdapter(adapter);
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
}
