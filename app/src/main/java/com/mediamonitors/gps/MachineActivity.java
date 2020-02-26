package com.mediamonitors.gps;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.mediamonitors.gps.models.MachineModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;

public class MachineActivity extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    Button nextButton;
    Button loadButton;
    private static ProgressDialog mProgressDialog;
    private TextView txtMachine;
    private ArrayList<MachineModel> machinesList  = new ArrayList<>();;
    private ArrayList<String> machines = new ArrayList<>();
    private String URL_MACHINES = "https://mmgps.000webhostapp.com/machine.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        spinner1= findViewById(R.id.spinner4);
        spinner2=findViewById(R.id.spinner5);
        nextButton=findViewById(R.id.button2);
        loadButton=findViewById(R.id.button);




    }
    public void next(View view)
    {
        Toast.makeText(MachineActivity.this, "next clicked", Toast.LENGTH_SHORT).show();
    }
    public void load(View view)
    {
        Toast.makeText(MachineActivity.this, "load clicked", Toast.LENGTH_SHORT).show();
        retrieveJSON();
    }

    private void retrieveJSON() {

        showSimpleProgressDialog(MachineActivity.this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_MACHINES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            String success = obj.getString("success");

                            if (success.equals("1")){

                                machinesList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    MachineModel machineModel = new MachineModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    machineModel.setName(dataobj.getString("machine_name"));


                                    machinesList.add(machineModel);

                                }

                                for (int i = 0; i < machinesList.size(); i++){
                                    machines.add(machinesList.get(i).getName().toString());
                                }

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MachineActivity.this, simple_spinner_item, machines);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spinner1.setAdapter(spinnerArrayAdapter);
                                removeSimpleProgressDialog();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

