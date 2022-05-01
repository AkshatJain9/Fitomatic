package com.ajsmdllz.fitomatic;

import android.content.Context;
import android.os.StrictMode;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CloudMessenger {
    public final String Base_URL = "https://fcm.googleapis.com/fcm/send";
    public final String Server_Key = "AAAA63xkzkQ:APA91bFDuNoKJaAMjROdi8rkkf7vumf_LmgL3DMxSBO4QrRuFoxJ0mMZxLXl0P2fc3hIzirbjx7hbrx2TG3_StNr6YQy9BcVrpnftqiKqPLra7OzU0rXt8iQlas9MAqvdA-iluJn7Rz6";

    private static CloudMessenger instance = null;
    private CloudMessenger(){};

    public static CloudMessenger getInstance() {
        if (instance == null) {
            instance = new CloudMessenger();
        }
        return instance;
    }

    public void sendNotification(Context context, String token, String title, String body) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(context);

        try {
            JSONObject json = new JSONObject();
            json.put("to", token);
            JSONObject notif = new JSONObject();
            notif.put("title", title);
            notif.put("body", body);
            json.put("notification", notif);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Base_URL, json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("FCM " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorisation", Server_Key);
                    return params;
                }
            };
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
