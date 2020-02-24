package com.mediamonitors.gps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mediamonitors.gps.models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Message extends AppCompatActivity {

    ListView listView;
    MessageAdapter adapter;
    UserModel user;
    public static ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    String url = "https://mmgps.000webhostapp.com/user_msg.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        listView = findViewById(R.id.myListView);
        adapter = new MessageAdapter(this, userModelArrayList);
        listView.setAdapter(adapter);
        retreiveData();
    }
    public void retreiveData()
    {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                userModelArrayList.clear();
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if (success.equals("1"))
                    {
                        for(int i =0 ; i<jsonArray.length();i++)
                        {
                          JSONObject object = jsonArray.getJSONObject(i);
                            String id =object.getString("id");
                           String name =object.getString("name");
                           String userLoginID =object.getString("userLoginID");
                            String password =object.getString("password");
                            String message = object.getString("msg_description");
                            user= new UserModel(id, name, userLoginID, password, message);
                            userModelArrayList.add(user);
                            adapter.notifyDataSetChanged();


                        }

                    }


                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Message.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
