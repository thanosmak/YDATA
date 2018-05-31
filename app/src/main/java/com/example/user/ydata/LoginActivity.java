package com.example.user.ydata;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginButton, forgotpassBtn, registerButton;
    ProgressBar loginProgress;
    private EditText login_username;
    private EditText login_password;
    private String username;
    private String password;
    private String baseUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        baseUrl = "https://app.ydata.eu/ydatapi/api/users/self";

        login_username = findViewById(R.id.login_mail);
        login_password = findViewById(R.id.login_password);


        loginButton = findViewById(R.id.login_button);
        loginProgress = findViewById(R.id.login_progress);
        loginProgress.setVisibility(View.INVISIBLE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    username = login_username.getText().toString();
                    password = login_password.getText().toString();

                    ApiAuthentication apiAuthenticationClient =
                            new ApiAuthentication(
                                    baseUrl
                                    , username
                                    , password,
                                    LoginActivity.this
                            );

                    AsyncTask<Void, Void, String> execute = new ExecuteNetworkOperation(apiAuthenticationClient);
                    execute.execute();
                } catch (Exception ex) {
                    Log.e("thanos_error", "Error on click");
                }
            }
        });

        forgotpassBtn = findViewById(R.id.ForgotPassBtn);
        forgotpassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String google = "https://app.ydata.eu/users/forgotpassword";
                Uri webaddress = Uri.parse(google);
                Intent gotoGoogle = new Intent(Intent.ACTION_VIEW, webaddress);
                if (gotoGoogle.resolveActivity(getPackageManager())!=null){
                    startActivity(gotoGoogle);
                }
            }
        });

        registerButton = findViewById(R.id.RegisterActivityBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                //show how to pass information to another activity
                startIntent.putExtra("org.mentorsxhools.quicklancher.Something","Hello World");
                startActivity(startIntent);

            }
        });

    }


    /**
     * This subclass handles the network operations in a new thread.
     * It starts the progress bar, makes the API call, and ends the progress bar.
     */
    public class ExecuteNetworkOperation extends AsyncTask<Void, Void, String> {

        private ApiAuthentication apiAuthenticationClient;
        private String isValidCredentials;

        /**
         * Overload the constructor to pass objects to this class.
         */
        public ExecuteNetworkOperation(ApiAuthentication apiAuthenticationClient) {
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Display the progress bar.
            loginProgress.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                isValidCredentials = apiAuthenticationClient.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Hide the progress bar.
            loginProgress.setVisibility(View.INVISIBLE);
            loginButton.setVisibility(View.VISIBLE);

            // Login Success
            if (!isValidCredentials.isEmpty()) {
                goToHomeActivity();
            }
            // Login Failure
            else {
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Open a new activity window.
     */
    private void goToHomeActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("password", password);
        bundle.putString("baseUrl", baseUrl);

        Intent intent = new Intent(this, Splasscreen_Activity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
