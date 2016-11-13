package gtappathon.gatech.heartattack;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Mohammed on 11/12/2016.
 */

public interface FoodDataInterface {
    public List<Food> searchByName(String name);
    public void setListener(Response.Listener<JSONObject> listener);
}
