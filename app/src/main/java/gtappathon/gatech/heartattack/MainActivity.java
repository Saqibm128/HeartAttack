package gtappathon.gatech.heartattack;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Exchanger;

public class MainActivity extends AppCompatActivity implements FoodResultsFragment.OnListFragmentInteractionListener {

    FoodDataInterface foodInfo;
    FoodFragmentListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listener = new FoodFragmentListener();
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
                    FoodResultsFragment foodResultsFragment = new FoodResultsFragment();
                    Bundle bundle = new Bundle();
                    listener.fragment = foodResultsFragment;
                    foodInfo = new USDAFoodInfo(getApplicationContext(), listener);
                    List<Food> food = foodInfo.searchByName(foodQuery);
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

    class FoodFragmentListener implements Response.Listener<JSONObject> {
        FoodResultsFragment fragment;

    @Override
    public void onResponse(JSONObject response) {
        List<Food> foodList = new ArrayList<>();
        try {
            JSONArray jsonFoodItems = response.getJSONObject("list").getJSONArray("item");
            for(int i = 0; i < jsonFoodItems.length(); i++) {
                JSONObject foodItem = jsonFoodItems.getJSONObject(i);
                String name = foodItem.getString("name");
                int ndbno = Integer.parseInt(foodItem.getString("ndbno"));
                Food food = new Food(name);
                food.ndbno = ndbno;
                foodList.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((MyFoodResultsFragmentRecyclerViewAdapter)fragment.foodAdapter).foodList = foodList;
        fragment.foodAdapter.notifyDataSetChanged();
    }
}

    @Override
    public void onListFragmentInteraction(Food item) {

    }
}
