package com.androidapplication.amine.docteurapp.DataBase;

/**
 * Created by Allah on 29/04/2016.
 */


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class JSONParser {

    String charset = "UTF-8";
    HttpURLConnection conn;
    DataOutputStream wr;
    StringBuilder result;   //le resultat reçu du serveur
    URL urlObj;
    JSONObject jObj = null; //l'objet json retourné
    StringBuilder sbParams;

    /*
    makeHttpRequest : une fonction qui permet d'envoyer une requette HTTP POST ou GET et attendre une réponse
     */
    public JSONObject makeHttpRequest(String url, String method, HashMap<String, String> params) {

        sbParams = new StringBuilder();
        int i = 0;


        // http://192.168.1.15/login.php?username=admin&password=azerty
        //encoder les paramétres
        for (String key : params.keySet()) {
            try {
                if (i != 0) {
                    sbParams.append("&");
                }
                sbParams.append(key).append("=")
                        .append(URLEncoder.encode(params.get(key), charset));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }

        //si la requete est POST
        if (method.equals("POST")) {
            // request method is POST
            try {
                urlObj = new URL(url);
                Log.v("makehttprequest","url :" + url);
                //créer une connection vers l'url
                conn = (HttpURLConnection) urlObj.openConnection();

                //setDoOutput prends true dans la méthode POST pour permettre d'envoyer
                //les paramétres dans le Body du paquet HTTP
                conn.setDoOutput(true);

                //définir la méthode
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept-Charset", charset);

                //le timeout necessaire pour lire les paramétres post
                conn.setReadTimeout(10000);

                //le timeout necessaire pour la connexion
                conn.setConnectTimeout(15000);
                conn.connect();

                //poster les paramétres
                wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(sbParams.toString());
                wr.flush();
                wr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("GET")){
            // request method is GET

            //concaténer à l'url les paramétres encodés
            if (sbParams.length() != 0) {
                url += "?" + sbParams.toString();
            }

            try {
                urlObj = new URL(url);
                Log.v("makehttprequest","url :" + url);

                conn = (HttpURLConnection) urlObj.openConnection();

                //nous n'avons pas d'output pour les requetes GET
                conn.setDoOutput(false);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept-Charset", charset);
                conn.setConnectTimeout(15000);
                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            //recevoir le résultat du serveur
            InputStream in = new BufferedInputStream(conn.getInputStream());

            //lire le resultat reçu dans un reader
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            if (result!=null) {
                Log.v("JSON Parser", "result: " + result.toString());
                // essayer de convertir la réponse reçue vers un objet JSON
                try {
                    jObj = new JSONObject(result.toString());
                } catch (JSONException e) {
                    Log.v("JSON Parser", "Error parsing data " + e.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //fermer la connexion
        conn.disconnect();

        // retourner le JSON Object
        return jObj;
    }
}

