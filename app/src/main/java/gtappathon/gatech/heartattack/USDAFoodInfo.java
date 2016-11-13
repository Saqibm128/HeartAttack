package gtappathon.gatech.heartattack;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mohammed on 11/12/2016.
 */

public class USDAFoodInfo implements FoodDataInterface {
    private Context context;
    private Response.Listener<JSONObject> listener;
    public USDAFoodInfo(Context context, Response.Listener<JSONObject> listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public List<Food> searchByName(String name) {
        List<Food> foods = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://api.nal.usda.gov/ndb/search/?format=json&max=25&offset=0&api_key=0yeFM5xBz15P2e14Z3QKF837ERjdLpYfvyAcJiwG&q=";
        url += name;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response success", response.toString());
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("response fail", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
        return foods;
    }
}
