package com.example.user.ydata.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.ydata.R;
import com.example.user.ydata.adapters.WatermeterRecyclerViewAdapter;
import com.example.user.ydata.model.Waterconnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WatermeterActivity extends AppCompatActivity {

    private final String JSON_URL = "https://app.ydata.eu/ydatapi/api/waterconnections/all";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Waterconnection> waterconnectionList;
    private RecyclerView recyclerView;
    private String YdataTokenResponse;
    ProgressBar pbWatermetersRecycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watermeter);

        // Get YdataTokenResponse to be used in future request calls
        SharedPreferences prefs = getSharedPreferences("ydata", MODE_PRIVATE);
        YdataTokenResponse = prefs.getString("YdataTokenResponse", "");

        waterconnectionList = new ArrayList<>();
        recyclerView = findViewById(R.id.watermeterRecyclerView);

        pbWatermetersRecycler = findViewById(R.id.pbWatermetersRecycler);
        pbWatermetersRecycler.setVisibility(View.VISIBLE);

        getWatermetersRequest();
    }

    // Makes request to get all watermeters
    private void getWatermetersRequest() {

        request = new JsonArrayRequest(Request.Method.GET, JSON_URL, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    pbWatermetersRecycler.setVisibility(View.INVISIBLE);

                    JSONObject jsonObject = null;

                    for (int i = 0 ; i < response.length() ; i++) {

                        try {
                            jsonObject = response.getJSONObject(i);
                            Waterconnection waterconnection = new Waterconnection();

                            waterconnection.setDeya(jsonObject.getString("deya"));
                            waterconnection.setPin(jsonObject.getString("pin"));
                            waterconnection.setStatus(jsonObject.getString("status"));
                            waterconnection.setOwner(jsonObject.getString("owner"));
                            waterconnection.setAddress(jsonObject.getString("address"));

                            // Construct Indication object
                            List<Waterconnection.Indications> indicationsList = new ArrayList<>();

                            for (int k = 0 ; k < jsonObject.getJSONArray("indications").length() ; k++) {
                                JSONObject indicationObject = jsonObject.getJSONArray("indications").getJSONObject(k);
                                Waterconnection.Indications indication = new Waterconnection.Indications();

                                indication.setUsage(indicationObject.getString("usage"));
                                indication.setNewIndication(indicationObject.getString("newIndication"));
                                indication.setNewIndicationDate(indicationObject.getString("newIndicationDate"));
                                indication.setCubicDifference(indicationObject.getString("cubicDifference"));
                                indication.setCubicCharged(indicationObject.getString("cubicCharged"));
                                indication.setComments(indicationObject.getString("comments"));

                                indicationsList.add(indication);
                            }
                            waterconnection.setIndicationsList(indicationsList);
                            // END of Construct Indication object

                            // Construct Watermeter object
                            JSONObject watermeterObject = jsonObject.getJSONObject("watermeter");
                            Waterconnection.Watermeter watermeter = new Waterconnection.Watermeter();

                            watermeter.setWatermeterPin(watermeterObject.getString("watermeterPin"));
                            watermeter.setWatermeterNumber(watermeterObject.getString("watermeterNumber"));
                            watermeter.setConsumerCode(watermeterObject.getString("consumerCode"));
                            watermeter.setStatus(watermeterObject.getString("status"));
                            watermeter.setFullNameOnBill(watermeterObject.getString("fullNameOnBill"));
                            watermeter.setRrdto(watermeterObject.getString("rrdto"));

                            waterconnection.setWatermeter(watermeter);
                            // END of Construct Watermeter object

                            waterconnectionList.add(waterconnection);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    setupRecyclerView(waterconnectionList);

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pbWatermetersRecycler.setVisibility(View.INVISIBLE);
                    Log.e("watermeter request err:", String.valueOf(error));
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("YdataToken", YdataTokenResponse);
                    return headers;
                }
        };

        requestQueue = Volley.newRequestQueue(WatermeterActivity.this);
        requestQueue.add(request);

    }

    private void setupRecyclerView(List<Waterconnection> waterconnectionList) {

        WatermeterRecyclerViewAdapter myadapter = new WatermeterRecyclerViewAdapter(this, waterconnectionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
