package gtappathon.gatech.heartattack;

import android.content.Context;
import android.graphics.Movie;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by Mohammed on 11/13/2016.
 */

public class HeartAttackStatus {
    int points;
    private static HeartAttackStatus heartAttackStatus;
    private Map<Integer, Movie> storedMovies;
//    private Thread countDown = new Thread() {
//        public void run() {
//            while (true) {
//                if (System.currentTimeMillis() % (5 * 1000) == 0 && points > 300)
//                    points -= 200;
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    };
    private HeartAttackStatus() {
        points = 0;
//        countDown.start();
    }
    public static HeartAttackStatus getInstance() {
        if (heartAttackStatus == null)
            heartAttackStatus = new HeartAttackStatus();
        return heartAttackStatus;
    }
    public void addFood(Food f) {
        points += (int) f.gramsFat * 5;
        points += (int) f.gramsSugar * 5;
        points += (int) f.calories / 10;
    }

    public String getLevel() {
        if (points > 1300 && points < 1671) {
            return "13";
        }
        else if (points >= 1671) {
            return "HEART ATTACK! FINALLY!";
        } else {
            return (new Integer(points/100)).toString();
        }
    }


}
