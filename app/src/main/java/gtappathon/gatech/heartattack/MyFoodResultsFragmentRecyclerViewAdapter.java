package gtappathon.gatech.heartattack;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gtappathon.gatech.heartattack.FoodResultsFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a Food and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFoodResultsFragmentRecyclerViewAdapter extends RecyclerView.Adapter<MyFoodResultsFragmentRecyclerViewAdapter.ViewHolder> {

    List<Food> foodList;
    private final OnListFragmentInteractionListener mListener;
    private Context parentContext;

    public MyFoodResultsFragmentRecyclerViewAdapter(List<Food> items, OnListFragmentInteractionListener listener) {
        foodList = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_foodresultsfragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.foodItem = foodList.get(position);
        holder.foodName.setText(foodList.get(position).name);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food item = foodList.get(position);
                USDAFoodInfo foodInfo = new USDAFoodInfo(parentContext, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Nutrition Info", jsonObject.toString());
                        Food f = new Food("Unknown");
                        try {
                            JSONArray foodNutrientInfo = jsonObject.getJSONObject("report").getJSONArray("foods").getJSONObject(0).getJSONArray("nutrients");
                            JSONObject sugarInfo = foodNutrientInfo.getJSONObject(0);
                            JSONObject fatInfo = foodNutrientInfo.getJSONObject(1);
                            JSONObject calorieInfo = foodNutrientInfo.getJSONObject(3);
                            f.calories = Double.parseDouble(calorieInfo.getString("value"));
                            f.gramsFat = Double.parseDouble(fatInfo.getString("value"));
                            f.gramsSugar = Double.parseDouble(sugarInfo.getString("value"));
                            HeartAttackStatus.getInstance().addFood(f);
                            GifHolder gifHolder = new GifHolder();
                            FragmentManager fragmentManager = MainActivity.mainActivity.getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_holder, gifHolder);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        } catch(JSONException j) {
                            Log.e("JSON OBJECT", j.toString());
                        }
                    }
                });
                foodInfo.getNutritionInfo(item);
            }
        });

        holder.foodItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.foodItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View foodItemView;
        public final TextView foodName;
        public final ImageView image;
        public final Button button;
        public Food foodItem;

        public ViewHolder(View view) {
            super(view);
            foodItemView = view;
            foodName = (TextView) view.findViewById(R.id.id);
            image = (ImageView) view.findViewById(R.id.food_image);
            button = (Button) view.findViewById(R.id.eat_button);
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}
