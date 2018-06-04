package com.example.user.ydata.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.ydata.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // On login we can save the user's data on a SharedPreferences var
        // and retrieve it here to display it.
        TextView email = findViewById(R.id.email);
        email.setText("makaronasthanos@gmail.com");

        logout = findViewById(R.id.logout_button);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
