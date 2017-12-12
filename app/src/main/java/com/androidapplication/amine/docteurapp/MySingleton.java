package com.androidapplication.amine.docteurapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Hamza-Info on 09/01/2017.
 */

public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mctx;

    public MySingleton(Context context)
    {
        mctx = context;
        requestQueue = getRequestQueue();

    }
    public RequestQueue getRequestQueue()
    {if (requestQueue == null)
    {
        requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
    }
       return  requestQueue;

    }
    public static synchronized  MySingleton getInstance(Context context)
    {if (mInstance == null)
    {
        mInstance = new MySingleton(context);
    }
        return mInstance;
    }
    public <T> void addToRequestqueue (Request<T> request)
    {
        requestQueue.add(request);
    }
}
