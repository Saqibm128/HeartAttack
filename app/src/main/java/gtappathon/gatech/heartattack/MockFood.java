package gtappathon.gatech.heartattack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 11/12/2016.
 */

public class MockFood implements FoodDataInterface{
    public List<Food> searchByName(String name){
        ArrayList<Food> foods = new ArrayList<>();
        if (name.equalsIgnoreCase("Burger"))
            foods.add(new Food("Burger", 800));
        if (name.equalsIgnoreCase("Salad"))
            foods.add(new Food("Salad", 150));
        if (name.equalsIgnoreCase("Hotdog"))
            foods.add(new Food("Hotdog", 300));
        if (name.equalsIgnoreCase("Soda"))
            foods.add(new Food("Soda", 75));
        return foods;
    }
}
