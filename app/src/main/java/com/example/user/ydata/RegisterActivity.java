package com.example.user.ydata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
//    EditText emailBox, passwordBox, passconfBox;
//    Button registerButton;
//    EditText loginLink;
//    String URL = "https://app.ydata.eu/ydatapi/api/security/register";
//    private RequestQueue requestQueue;
//    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        final EditText emailBox = (EditText) findViewById(R.id.emailBox);
//        final EditText passwordBox = (EditText) findViewById(R.id.passwordBox);
//        final EditText passconfBox = (EditText) findViewById(R.id.passconfBox);
//        final Button registerButton = (Button) findViewById(R.id.registerButton);
//        //final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
//        passwordBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                Pattern PASSWORD_PATTERN
//                        = Pattern.compile(
//                        "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");
//                String pass = passwordBox.getText().toString();
//                if (
//                    //passwordBox.getText().length()<8
//                        !PASSWORD_PATTERN.matcher(pass).find()
//                        ) {
//                    passwordBox.setError("Password must have at least 1 Upercase, 1 lowercase and 1 special character!");
//                }
//            }
//        });
//
//        passconfBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                String pass = passwordBox.getText().toString();
//                String passcon = passconfBox.getText().toString();
//                if (!passcon.equals(pass)
//                        ) {
//                    passconfBox.setError("Password not matching");
//                }
//            }
//        });
//
//        //requestQueue = Volley.newRequestQueue(this);
//        /*
//        passwordBox.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String pass = passwordBox.getText().toString();
//                validatePassword(pass);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//*/
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    InsertSV();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//
//    }
///*
//    public void validatePassword(String password){
//        //Pattern upperCase = Pattern.compile("[A-Z]");
//        //Pattern lowerCase = Pattern.compile("[a-z]");
//        //Pattern digitCase = Pattern.compile("[0-9]");
//        //Pattern upperCase = Pattern.compile("[A-Z]");
//        Pattern PASSWORD_PATTERN
//                = Pattern.compile(
//                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");
//        if (!PASSWORD_PATTERN.matcher(password).find()){
//            passwordBox.setError("Invalid Password");
//        }
//    }
//
///*/
//
//
//
//                /*
//                final String Mail = emailBox.getText().toString();
//                final String Pass = passwordBox.getText().toString();
//                final String Passcon = loginLink.getText().toString();
//                final boolean mobileApp;
//                if (Pass==Passcon) {
//                    mobileApp = true;
//                }
//                else {
//                    mobileApp = false;
//                }
//                //final Boolean mobileApp = Boolean.parseBoolean(checkmobileApp.getText().toString());
//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//                            if (success){
//                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                RegisterActivity.this.startActivity(intent);
//                            }else{
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//                                builder.setMessage("Register Failed")
//                                        .setNegativeButton("Retry", null)
//                                        .create()
//                                        .show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                RegisterRequest registerRequest = new RegisterRequest(Mail, Pass, Passcon, mobileApp, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
//                queue.add(registerRequest);
//            }*/
//
//
//    private void InsertSV() throws JSONException {
//        JSONObject obj = new JSONObject();
//
//        obj.put("mobileApp", true);
//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, URL, obj,
//                new Response.Listener<JSONObject>() {
//                    /**
//                     * Called when a response is received.
//                     *
//                     * @param response
//                     */
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(getApplication(), (CharSequence) response, Toast.LENGTH_SHORT).show();
//                    }
///*
//            //StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getApplication(), response, Toast.LENGTH_SHORT).show();
//
//            }
//            */
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(RegisterActivity.this, error + "", Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                String Mail = emailBox.getText().toString();
//                String Pass = passwordBox.getText().toString();
//                String Passcon = passconfBox.getText().toString();
//                //String mobileApp = "true";
//
//                params.put("email", Mail);
//                params.put("password", Pass);
//                params.put("passwordConfirmation", Passcon);
//                //params.put("mobileApp ", mobileApp );
//
//                return params;
//            }
//        };
//        requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(req);
    }
}
