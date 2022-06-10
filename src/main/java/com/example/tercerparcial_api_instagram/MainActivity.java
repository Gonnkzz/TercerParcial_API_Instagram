package com.example.tercerparcial_api_instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
     Button btnPerfil, btnMedia;
     TextView textView;
     EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView = findViewById(R.id.textView);
        btnPerfil = findViewById(R.id.button);
        btnMedia = findViewById(R.id.button3);
        editText = findViewById(R.id.editTextTextMultiLine3);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miPerfil();
            }
        });

        btnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miMedia();
            }
        });
    }

    public void miPerfil () {
        String url = "https://graph.instagram.com/me?fields=id,username&access_token=IGQVJYN0VfZAWJJcGdfMkNOTXNjODB5WEI2Mk8yci04SU1HaTF4MVQzMjh2eEhwUjFVUk1JTWQzanY2TU1HRWd2U1YtQmZAMZA1dFQmFBV29jNXR0ZAXNSSVc3ZAEtIVGhYZAm1DWHRrQnJyRHRiZAnlVMHU3MgZDZD";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    editText.setText("Id: "+ jsonObject.getString("id") + " username: "+ jsonObject.getString("username"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(getRequest);
    }

    public void miMedia () {
        String url = "https://graph.instagram.com/me/media?fields=id,caption&access_token=IGQVJYN0VfZAWJJcGdfMkNOTXNjODB5WEI2Mk8yci04SU1HaTF4MVQzMjh2eEhwUjFVUk1JTWQzanY2TU1HRWd2U1YtQmZAMZA1dFQmFBV29jNXR0ZAXNSSVc3ZAEtIVGhYZAm1DWHRrQnJyRHRiZAnlVMHU3MgZDZD";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");
                    String cadena = "";

                    for (int i = 0; i < array.length(); i++){
                        JSONObject data = (JSONObject)array.get(i);
                        if (data.has("caption")){
                            cadena = cadena+("Id: "+ data.getString("id")+ "\nCaption" + data.getString("caption"));
                            cadena = cadena +"\n";
                            cadena = cadena +"\n";
                        }else {{
                            cadena = cadena+("Id:"+ data.getString("id"));
                            cadena = cadena +"\n";
                            cadena = cadena +"\n";
                        }}


                    }
                    editText.setText(cadena);
                    //
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(getRequest);
    }
}
