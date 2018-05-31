package com.example.user.ydata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.ydata.adapters.BillRecyclerViewAdapter;
import com.example.user.ydata.model.Bill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillsActivity extends AppCompatActivity {

    private final String JSON_URL = "https://app.ydata.eu/ydatapi/api/bills/mobileApp";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private String pin;
    private List<Bill> billList;
    private RecyclerView recyclerView;
    private String YdataTokenResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        // Get YdataTokenResponse to be used in future request calls
        SharedPreferences prefs = getSharedPreferences("ydata", MODE_PRIVATE);
        YdataTokenResponse = prefs.getString("YdataTokenResponse", "");

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        pin = bundle.getString("pin");

        billList = new ArrayList<>();
        recyclerView = findViewById(R.id.billsRecyclerView);

        getBillsRequest();
    }


    private void getBillsRequest() {

    request = new JsonArrayRequest(Request.Method.GET, JSON_URL + "?asc=false&order=issuedDate&page=0&pinCode=" + pin + "&size=9999999", null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    JSONObject jsonObject = null;

                    for (int i = 0 ; i < response.length() ; i++) {
                        try {
                            // Transform respone to JSON object
                            jsonObject = response.getJSONObject(i);
                            // Construct a class Bill variable
                            Bill bill = new Bill();

                            // Set Bill's attributes
                            bill.setBarcode(jsonObject.getString("barcode"));
                            bill.setIssuedDate(jsonObject.getString("issuedDate"));
                            bill.setPaymentDueDate(jsonObject.getString("paymentDueDate"));
                            bill.setRemainingAmount(jsonObject.getString("remainingAmount"));
                            bill.setChargedConsumption(jsonObject.getString("chargedConsumption"));

                            // Get object from response (usagePeriod)
                            JSONObject usagePeriodObj = jsonObject.getJSONObject("usagePeriod");
                            // Construct inner a usagePeriod class var to set it in Bill class
                            Bill.UsagePeriod usagePeriod = new Bill.UsagePeriod();

                            // Set UsagePeriod's attributes
                            usagePeriod.setFrom(usagePeriodObj.getString("from"));
                            usagePeriod.setTo(usagePeriodObj.getString("to"));

                            // Set UsagePeriod inside Bill
                            bill.setUsagePeriod(usagePeriod);

                            // Add bill object in billList
                            billList.add(bill);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    setupRecyclerView(billList);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("bill error:", String.valueOf(error));
                    Toast.makeText(getApplicationContext(), "Oops. Something went wrong. Please try again later.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("YdataToken", YdataTokenResponse);
                    return headers;
                }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue = Volley.newRequestQueue(BillsActivity.this);
        requestQueue.add(request);
    }

    private void setupRecyclerView(List<Bill> billList) {
        BillRecyclerViewAdapter myAdapter = new BillRecyclerViewAdapter(this, billList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}
