package com.example.user.ydata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.ydata.adapters.IndicationsRecuclerViewAdapter;
import com.example.user.ydata.model.Waterconnection;

import java.util.List;

public class LastMovesActivity extends AppCompatActivity {

    private List<Waterconnection.Indications> indicationsList;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_moves);

        recyclerView = findViewById(R.id.indicationsRecyclerView);

        Bundle bundle = getIntent().getExtras();
        indicationsList = (List<Waterconnection.Indications>) bundle.getSerializable("indications");

        Log.i("indications: ", String.valueOf(indicationsList));

        setupRecyclerView(indicationsList);
    }

    private void setupRecyclerView(List<Waterconnection.Indications> indicationsList) {

        IndicationsRecuclerViewAdapter indicationsRecuclerViewAdapter = new IndicationsRecuclerViewAdapter(this, indicationsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(indicationsRecuclerViewAdapter);
    }
}
