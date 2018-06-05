package com.example.user.ydata.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.example.user.ydata.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView watermeterCard, billsCard, profileCard, faqCard, reportsCard, infromationCard, paymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Define Cards
        watermeterCard = (CardView) findViewById(R.id.watermeterButton);
        billsCard = (CardView) findViewById(R.id.billsCard);
        profileCard = (CardView) findViewById(R.id.profileButton);
        faqCard = (CardView) findViewById(R.id.faqCard);
        reportsCard = (CardView) findViewById(R.id.reportsCard);
        infromationCard = (CardView) findViewById(R.id.infromationCard);
        paymentButton = (CardView) findViewById(R.id.paymentButton);

        // Set click listeners
        watermeterCard.setOnClickListener(this);
        billsCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
        faqCard.setOnClickListener(this);
        reportsCard.setOnClickListener(this);
        infromationCard.setOnClickListener(this);
        paymentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.watermeterButton : i = new Intent(this, WatermeterActivity.class); startActivity(i); break;
            case R.id.billsCard : i = new Intent(this, WatermeterActivity.class); startActivity(i); break;
            case R.id.profileButton : i = new Intent(this, ProfileActivity.class); startActivity(i); break;
            case R.id.faqCard :
                String google = "https://app.ydata.eu/faq";
                Uri webaddress = Uri.parse(google);
                Intent gotoGoogle = new Intent(Intent.ACTION_VIEW, webaddress);
                if (gotoGoogle.resolveActivity(getPackageManager())!=null){
                    startActivity(gotoGoogle);
                }
                break;
            case R.id.reportsCard :
            case R.id.infromationCard :
            case R.id.paymentButton :
                Toast.makeText(getApplicationContext(), "Comming Soon!!!", Toast.LENGTH_SHORT).show();
            default:break;
        }
    }
}
