package com.example.user.ydata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView watermeterCard, billsCard, profileCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Define Cards
        watermeterCard = (CardView) findViewById(R.id.watermeterButton);
        billsCard = (CardView) findViewById(R.id.billsButton);
        profileCard = (CardView) findViewById(R.id.profileButton);

        // Set click listeners
        watermeterCard.setOnClickListener(this);
        billsCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.watermeterButton : i = new Intent(this, WatermeterActivity.class); startActivity(i); break;
            case R.id.billsButton : i = new Intent(this, WatermeterActivity.class); startActivity(i); break;
            case R.id.profileButton : i = new Intent(this, ProfileActivity.class); startActivity(i); break;
            default:break;
        }
    }
}
