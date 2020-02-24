package com.mediamonitors.gps.models;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingleTon {
    private static SingleTon singleTon;
    private RequestQueue requestQueue;
    private static Context context1;

    public SingleTon(Context context) {
        context1 = context;
        requestQueue =getRequestQueue();
    }

    public static synchronized SingleTon getSingleTon(Context context){

        if (singleTon == null){
            singleTon=new SingleTon(context);
        }
        return singleTon;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(context1.getApplicationContext());
        }
        return requestQueue;
    }

    public <T>void addIntoReqQue(Request<T> request)
    {
        requestQueue.add(request);
    }
}