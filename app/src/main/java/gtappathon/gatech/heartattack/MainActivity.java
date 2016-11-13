package gtappathon.gatech.heartattack;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodResultsFragment.OnListFragmentInteractionListener {

    FoodDataInterface foodInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        };
        foodInfo = new USDAFoodInfo(this, listener);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SearchView searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String foodQuery) {
                if (foodQuery != null && foodQuery.length() > 0) {
                    List<Food> food = foodInfo.searchByName(foodQuery);
                    FoodResultsFragment foodResultsFragment = new FoodResultsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("foodList", (Serializable) food);
                    foodResultsFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_holder, foodResultsFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }

    @Override
    public void onListFragmentInteraction(Food item) {

    }
}
