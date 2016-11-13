package gtappathon.gatech.heartattack;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodResultsFragment.OnListFragmentInteractionListener {

    MockFood foodInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        foodInfo = new MockFood();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void search(View view){
        SearchView searchView = (SearchView) findViewById(R.id.search_bar);
        String foodQuery = searchView.getQuery().toString();
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

    @Override
    public void onListFragmentInteraction(Food item) {

    }
}
