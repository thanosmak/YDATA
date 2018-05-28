package com.example.user.ydata;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String  REGISTER_REQUEST_URL = "https://app.ydata.eu/ydatapi/api/security/register.php";
    private Map<String, String> params;


    //function for the post method
    public RegisterRequest(String email, String password, String passwordconf ,Boolean mobileApp, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email );
        params.put("password", password );
        params.put("passwordconf", passwordconf );
        params.put("mobileApp", mobileApp + "");  //here  should be the boolean/with this method we give a string + boolena

    }

    @Override
    public  Map<String, String> getParams(){
        return params;
    }
}
