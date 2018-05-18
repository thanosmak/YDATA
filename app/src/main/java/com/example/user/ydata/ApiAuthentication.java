package com.example.user.ydata;

/**
 * Created by user on 28-Apr-18.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.oreilly.servlet.Base64Encoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ApiAuthentication {
    private String baseUrl;
    private String username;
    private String password;
    private String urlResource;
    private String httpMethod; // GET, POST, PUT, DELETE
    private String urlPath;
    private String lastResponse;
    private String payload;
    private HashMap<String, String> parameters;
    private Map<String, List<String>> headerFields;
    private String YdataTokenResponse;
    private Context context;


    /**
     *
     * @param baseUrl String
     * @param username String
     * @param password String
     */
    public ApiAuthentication(String  baseUrl, String username, String password, Context context) {
        setBaseUrl(baseUrl);
        this.username = username;
        this.password = password;
        this.urlResource = "";
        this.urlPath = "";
        this.httpMethod = "GET";
        this.context = context;
        parameters = new HashMap<>();
        lastResponse = "";
        payload = "";
        headerFields = new HashMap<>();
        // This is important. The application may break without this line.
        System.setProperty("jsse.enableSNIExtension", "false");
    }


    /**
     * --&gt;http://BASE_URL.COM&lt;--/resource/path
     * @param baseUrl the root part of the URL
     * @return this
     */
    public ApiAuthentication setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
//        if (!baseUrl.substring(baseUrl.length() - 1).equals("/")) {
//            this.baseUrl += "/";
//        }
        return this;
    }


    /**
     * Set the name of the resource that is used for calling the Rest API.
     * @param urlResource http://base_url.com/--&gt;URL_RESOURCE&lt;--/url_path
     * @return this
     */
    public ApiAuthentication setUrlResource(String urlResource) {
        this.urlResource = urlResource;
        return this;
    }

    /**
     * Set the path  that is used for calling the Rest API.
     * This is usually an ID number for Get single record, PUT, and DELETE functions.
     * @param urlPath http://base_url.com/resource/--&gt;URL_PATH&lt;--
     * @return this
     */
    public final ApiAuthentication setUrlPath(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    /**
     * Sets the HTTP method used for the Rest API.
     * GET, PUT, POST, or DELETE
     * @return this
     */
    public ApiAuthentication setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    /**
     * Get the output from the last call made to the Rest API.
     * @return String
     */
    public String getLastResponse() {
        return lastResponse;
    }


    /**
     * Get the output from the last call made to the Rest API.
     * @return String
     */
    public String getYdataTokenResponse() {
        return YdataTokenResponse;
    }

    /**
     * Get a list of the headers returned by the last call to the Rest API.
     * @return Map&lt;String, List&lt;String&gt;&gt;
     */
    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    /**
     * Replace all of the existing parameters with new parameters.
     * @param parameters
     * @return this
     */
    public ApiAuthentication setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    /**
     * Set a parameter to be used in the call to the Rest API.
     * @param key the name of the parameter
     * @param value the value of the parameter
     * @return this
     */
    public ApiAuthentication setParameter(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    /**
     * Delete all parameters that are set for the Rest API call.
     * @return this
     */
    public ApiAuthentication clearParameters() {
        this.parameters.clear();
        return this;
    }

    /**
     * Remove a specified parameter
     * @param key the name of the parameter to remove
     */
    public ApiAuthentication removeParameter(String key) {
        this.parameters.remove(key);
        return this;
    }

    /**
     * Deletes all values used to make Rest API calls.
     * @return this
     */
    public ApiAuthentication clearAll() {
        parameters.clear();
        baseUrl = "";
        this.username = "";
        this.password = "";
        this.urlResource = "";
        this.urlPath = "";
        this.httpMethod = "";
        lastResponse = "";
        payload = "";
        headerFields.clear();
        return this;
    }

    /**
     * Get the last response from the Rest API as a JSON Object.
     * @return JSONObject
     */
    public JSONObject getLastResponseAsJsonObject() {
        try {
            return new JSONObject(String.valueOf(lastResponse));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the last response from the Rest API as a JSON Array.
     * @return JSONArray
     */
    public JSONArray getLastResponseAsJsonArray() {
        try {
            return new JSONArray(String.valueOf(lastResponse));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the payload as a string from the existing parameters.
     * @return String
     */
    private String getPayloadAsString() {
        // Cycle through the parameters.
        StringBuilder stringBuffer = new StringBuilder();
        Iterator it = parameters.entrySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (count > 0) {
                stringBuffer.append("&");
            }
            stringBuffer.append(pair.getKey()).append("=").append(pair.getValue());

            it.remove(); // avoids a ConcurrentModificationException
            count++;
        }
        return stringBuffer.toString();
    }

    /**
     * Make the call to the Rest API and return its response as a string.
     * @return String
     */
    public String execute() {
        String line;
        StringBuilder outputStringBuilder = new StringBuilder();

        try {
            StringBuilder urlString = new StringBuilder(baseUrl + urlResource);

            if (!urlPath.equals("")) {
                urlString.append("/" + urlPath);
            }

            if (parameters.size() > 0 && httpMethod.equals("GET")) {
                payload = getPayloadAsString();
                urlString.append("?" + payload);
            }

            URL url = new URL(urlString.toString());

            String encoding = Base64Encoder.encode(username + ":" + password);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("YdataTokenRequest", encoding);

            // Make the network connection and retrieve the output from the server.
            if (httpMethod.equals("POST") || httpMethod.equals("PUT")) {

                payload = getPayloadAsString();

                connection.setDoInput(true);
                connection.setDoOutput(true);

                try {
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                    writer.write(payload);

                    headerFields = connection.getHeaderFields();
                    YdataTokenResponse = connection.getHeaderField("YdataTokenResponse");

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        outputStringBuilder.append(line);
                    }
                } catch (Exception ex) {Log.e("thanos_error:", ex.toString());}
                connection.disconnect();
            }
            else {
                InputStream content = connection.getInputStream();

                headerFields = connection.getHeaderFields();
                YdataTokenResponse = connection.getHeaderField("YdataTokenResponse");

                Log.v("thanos_headers:", headerFields.toString());
                Log.v("thanos_YdataTokenResp:", YdataTokenResponse);
                //connection.
                BufferedReader in = new BufferedReader(new InputStreamReader(content));

                while ((line = in.readLine()) != null) {
                    outputStringBuilder.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("thanos_error:", e.toString());
        }

        SharedPreferences prefs = context.getSharedPreferences("ydata", MODE_PRIVATE);
        prefs.edit().putString("YdataTokenResponse", getYdataTokenResponse()).apply();


        // If the outputStringBuilder is blank, the call failed.
        if (!outputStringBuilder.toString().equals("")) {

            lastResponse = outputStringBuilder.toString();
        }

        Log.v("thanos_response:", outputStringBuilder.toString());
        return outputStringBuilder.toString();
    }
}

