package gtappathon.gatech.heartattack;

/**
 * Created by Mohammed on 11/12/2016.
 */

public class Food {
    String name;
    double calories;
    int ndbno;
    double gramsSugar;
    double gramsFat;

    public Food (String name, int calories){
        this.name = name;
        this.calories = calories;
    }

    public Food (String name) {
        this.name = name;
    }
}
