package com.example.user.ydata;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.user.ydata.R;
import com.example.user.ydata.model.Waterconnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WatermeterDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private String deya, pin, address, consumer_code,owner, status, name_on_bill, watermeter_number;
    List<Waterconnection.Indications> serializedIndicationsList;
    List<Waterconnection.Indications> indicationsList;
    Button latestMovesButton, billsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watermeter_details);

        latestMovesButton = findViewById(R.id.latestMovesButton);
        billsButton = findViewById(R.id.billsButton);

        latestMovesButton.setOnClickListener(this);
        billsButton.setOnClickListener(this);

        // Hide the default actionbar
        getSupportActionBar().hide();


        // Recieve data
        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        deya = bundle.getString("deya");
        pin = bundle.getString("pin");
        address = bundle.getString("address");
        consumer_code = bundle.getString("consumer_code");
        owner = bundle.getString("owner");
        status = bundle.getString("status");
        name_on_bill = bundle.getString("name_on_bill");
        watermeter_number = bundle.getString("watermeter_number");
        serializedIndicationsList = (List<Waterconnection.Indications>) bundle.getSerializable("indications");
        // [{}, {}, {}]

        indicationsList = new ArrayList<>();

        for (int i = 0 ; i < serializedIndicationsList.size() ; i++) {
            Waterconnection.Indications indication = serializedIndicationsList.get(i);

            indicationsList.add(indication);
            Log.i("indications: ", indication.getUsage());
        }




        // Init views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

//        TextView tv_deya = findViewById(R.id.da_watermeterDeya);
        TextView tv_pin = findViewById(R.id.da_watermeterPin);
        TextView tv_address = findViewById(R.id.da_watermeterAddress);
        TextView tv_consumer_code = findViewById(R.id.da_consumerNumber);
        TextView tv_owner = findViewById(R.id.da_watermeterOwner);
        TextView tv_status = findViewById(R.id.da_watermeterStatus);
        TextView tv_name_on_bill = findViewById(R.id.da_watermeterNameOnBill);
        TextView tv_watermeter_number = findViewById(R.id.da_watermeterNumber);

        // Setting values to each view
//        tv_deya.setText(deya);
        tv_pin.setText("PIN: " + pin);
        tv_address.setText("Διεύθυνση:" + address);
        tv_consumer_code.setText("Κωδικός Καταναλωτή: " + consumer_code);
        tv_owner.setText("Ιδιοκτήτης: " + owner);
        tv_status.setText("Κατάσταση: " + status);
        tv_name_on_bill.setText("Υπόχρεος: " + name_on_bill);
        tv_watermeter_number.setText("Αριθμός Υδρομέτρου: " + watermeter_number);

        collapsingToolbarLayout.setTitle(deya);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }



    @Override
    public void onClick(View v) {
        Intent i;
        Bundle bundle = new Bundle();

        switch (v.getId()){
            case R.id.latestMovesButton :

                bundle.putSerializable("indications", (Serializable) indicationsList);

                i = new Intent(this, LastMovesActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                break;

            case R.id.billsButton :

                bundle.putString("pin", pin);

                i = new Intent(this, BillsActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                break;

            default:break;
        }
    }
}
