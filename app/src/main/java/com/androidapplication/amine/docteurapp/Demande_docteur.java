package com.androidapplication.amine.docteurapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class Demande_docteur extends AppCompatActivity implements ClassAddressIP {

    EditText e_spec, e_desc, e_educ, e_name, e_adr,e_tel;
    String strname,strspec, streduc, stradr,strtel,strlat="0",strlong="0";
    public String longitude ="";
    public String latitude ="";
    Button insert;
    RequestQueue requestQueue;
    String insertUrl = ClassAddressIP.adrip+"/docteur/demande_doc.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_docteur);

        //show icon in actionbar/toolbar***********
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);/*************/

        e_name=(EditText) findViewById(R.id.name);
        e_adr=(EditText) findViewById(R.id.adresse);
        e_tel=(EditText) findViewById(R.id.tel);
        e_educ=(EditText) findViewById(R.id.education);
        e_spec=(EditText) findViewById(R.id.speciality);
        insert=(Button) findViewById(R.id.btn_demande);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname = e_name.getText().toString();
                stradr = e_adr.getText().toString();
                strspec = e_spec.getText().toString();
                strtel = e_tel.getText().toString();
                streduc = e_educ.getText().toString();
                if (strname.equals("") || stradr.equals("") || strspec.equals("") || streduc.equals("") || strtel.equals("")) {
                    Toast t= Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 320);
                    t.show();
                } else {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    String message = jsonObject.getString("message");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("name", strname);
                                params.put("adresse", stradr);
                                params.put("tel", strtel);
                                params.put("education", streduc);
                                params.put("specialite", strspec);
                                params.put("latitude", strlat);
                                params.put("longitude", strlong);
                                return params;
                            }
                        };
                        MySingleton.getInstance(Demande_docteur.this).addToRequestqueue(stringRequest);

                    }
                Toast t= Toast.makeText(getApplicationContext(),"Succed request",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 320);
                t.show();
                e_educ.setText("");
                e_name.setText("");
                e_tel.setText("");
                e_spec.setText("");
                e_adr.setText("");
                Intent i = new Intent(Demande_docteur.this,Login_Activity.class);
                startActivity(i);
                }

            });

    }

}


