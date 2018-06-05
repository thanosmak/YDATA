package com.example.user.ydata.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.example.user.ydata.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText emailField, passwordField, passwordConfirmField;
    CheckBox termsCheckbox;
    Button registerButton;
    String URL = "https://app.ydata.eu/ydatapi/api/security/register";
    private RequestQueue requestQueue;
    private JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        passwordConfirmField = findViewById(R.id.passwordConfirm);
        registerButton = findViewById(R.id.registerButton);
        //final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        passwordField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Pattern PASSWORD_PATTERN
                        = Pattern.compile(
                        "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");
                String pass = passwordField.getText().toString();
                if (
                    //passwordBox.getText().length()<8
                        !PASSWORD_PATTERN.matcher(pass).find()
                        ) {
                    passwordField.setError("Password must have at least 1 Upercase, 1 lowercase and 1 special character!");
                }
            }
        });

        passwordConfirmField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                String pass = passwordConfirmField.getText().toString();
                String passcon = passwordConfirmField.getText().toString();
                if (!passcon.equals(pass)
                        ) {
                    passwordConfirmField.setError("Password not matching");
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InsertSV();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private void InsertSV() throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("email", emailField.getText().toString());
        obj.put("password", passwordField.getText().toString());
        obj.put("passwordConfirmation", passwordConfirmField.getText().toString());
//        obj.put("mobileApp", true);

        request = new JsonObjectRequest(Request.Method.POST, URL, obj,
            new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), "Αγαπητέ χρήστη του app.ydata.eu, ο λογαριασμός σας δημιουργήθηκε επιτυχώς στο Σύστημα. Στην διεύθυνση mail που ορίσατε στην διαδικασία εγγραφής, σας έχει αποσταλεί ένα mail με θέμα: \"Ενεργοποίηση λογαριασμού χρήστη\". Μέσα σε αυτό υπάρχει ένας σχετικός σύνδεσμος ( link ) με τίτλο: \"Ενεργοποίηση λογαριασμού\". Επιλέγοντάς τον, ο λογαριασμός σας στο σύστημα θα ενεργοποιηθεί οπότε και θα μπορέσετε να κάνετε login στο σύστημα.", Toast.LENGTH_LONG).show();
                    goToLoginView();
                }

            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.e("register error:", String.valueOf(error));
                    Toast.makeText(getApplicationContext(), "Κάτι πήγε στραβά. Δοκιμάστε πάλι αργότερα.", Toast.LENGTH_LONG).show();
                }
            }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(request);
    }

    private void goToLoginView() {
        Bundle bundle = new Bundle();
        bundle.putString("email", String.valueOf(emailField));
        bundle.putString("password", String.valueOf(passwordField));

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
