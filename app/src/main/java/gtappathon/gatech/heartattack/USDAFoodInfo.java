package gtappathon.gatech.heartattack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mohammed on 11/12/2016.
 */

public class USDAFoodInfo implements FoodDataInterface, SingleFoodNutritionInfo {
    private Context context;
    private Response.Listener<JSONObject> listener;
    public USDAFoodInfo(Context context, Response.Listener<JSONObject> listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setListener(Response.Listener<JSONObject> listener) {
        this.listener = listener;
    }

    @Override
    public List<Food> searchByName(String name) {
        List<Food> foods = new ArrayList<>();
        if (name.equalsIgnoreCase("Chicken Waffle")) {
            foods.add(new Food("Chicken Waffle"));
            return foods;
        }
        if (name.equalsIgnoreCase("Takorea Tacos")) {
            foods.add(new Food("Takorea Tacos"));
            return foods;
        }
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

    @Override
    public Food getNutritionInfo(Food f) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=0yeFM5xBz15P2e14Z3QKF837ERjdLpYfvyAcJiwG&nutrients=205&nutrients=204&nutrients=208&nutrients=269&ndbno=";
        url += f.ndbno;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error, ndbno", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
        return null;
    }
}
