package com.example.user.ydata.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.ydata.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private final String JSON_URL = "https://app.ydata.eu/ydatapi/api/profile";
    private JsonObjectRequest request;
    private RequestQueue requestQueue;
    private String YdataTokenResponse;
    private Button logout;
    TextView email, tv_name, tv_phone;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Get YdataTokenResponse to be used in future request calls
        SharedPreferences prefs = getSharedPreferences("ydata", MODE_PRIVATE);
        YdataTokenResponse = prefs.getString("YdataTokenResponse", "");


        email = findViewById(R.id.email);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phone);

        email.setText("makaronasthanos@gmail.com");

        logout = findViewById(R.id.logout_button);
        logout.setOnClickListener(this);

        callProfileService();
    }

    private void callProfileService() {
        request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String responseEmail="", responseFirstName="", responseLastName="", responsePhone="";
                        Log.i("profile: ", String.valueOf(response));
                        // Get first name
                        try {
                            JSONObject field1 = response.getJSONObject("field1");
                            responseFirstName = field1.getString("value");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Get last name
                        try {
                            JSONObject field2 = response.getJSONObject("field2");
                            responseLastName = field2.getString("value");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Get email
                        try {
                            JSONObject field4 = response.getJSONObject("field4");
                            responseEmail = field4.getString("value");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Get mobile
                        try {
                            JSONObject field9 = response.getJSONObject("field9");
                            responsePhone = field9.getString("value");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        email.setText(responseEmail);
                        tv_name.setText(responseFirstName + " " + responseLastName);
                        tv_phone.setText(responsePhone);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("YdataToken", YdataTokenResponse);
                    return headers;
                }
        };


        requestQueue = Volley.newRequestQueue(ProfileActivity.this);
        requestQueue.add(request);
    }

    @Override
    public void onClick(View v) {
        Intent i;
//        // Clear ydata token
//        SharedPreferences prefs = context.getSharedPreferences("ydata", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.remove("ydata");
//        editor.apply();

        i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
