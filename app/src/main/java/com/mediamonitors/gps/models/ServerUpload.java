package com.mediamonitors.gps.models;

import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;


public class ServerUpload extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://mmgps.000webhostapp.com/location_model.php";

    private Map<String, String> params;
    public ServerUpload(TextView latitude, TextView longitude, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        final String latitude1 =latitude.getText().toString();
        final String longitude1 =longitude.getText().toString();
        params = new HashMap<>();
        params.put("users_latitude", latitude1);
        params.put("user_longitude", longitude1);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}