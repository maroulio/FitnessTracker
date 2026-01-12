package gr.hua.dit.fitnessmanager;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Activity {
    private List<Laps> laps = new ArrayList<>();
    protected String sport;
    private LocalDateTime starttime;
    private double timeseconds;
    private double distance;
    private double averagepace;
    private int ahr;
    private int mhr;
    private double averagespeed;
    private int cal;

    public Activity() {}

    public String getSport() {
        return sport;
    }

    public LocalDateTime getStartTime() {
        return starttime;
    }

    public double getTimeSeconds() {
        if (laps.isEmpty()) {
            return 0;
        }
        double s = 0;
        for (Laps lap: laps) {
            s += lap.gettotalTimeSeconds();
        }
        return timeseconds = s;
    }

    public double getDistanceMeters() {
        if (laps.isEmpty()) {
            return 0;
        }
        double s = 0;
        for (Laps lap: laps) {
            s += lap.getDistanceMeters();
        }
        return distance = s;
    }

    public double getAveragePace() {    // It may not be returned with 2 decimal digits
        if (distance == 0) {
            return 0;
        }
        return averagepace = timeseconds / 60 / (distance / 1000);
    }

    public int getAHR() {
        if (laps.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Laps lap: laps) {
            sum += lap.getAvgHR();
        }
        return ahr = sum / laps.size();
    }

    public int getMHR() {
        if (laps.isEmpty()) {
            return 0;
        }
        int m = 0;
        for (Laps lap: laps) {
            if (lap.getMaxHR() > m) {
                m = lap.getMaxHR();
            }
        }
        return mhr = m;
    }

    public double getAverageSpeed() {
        if (laps.isEmpty()) {
            return 0;
        }
        double s = 0;
        for (Laps lap: laps) {
            s += lap.getavgSpeed();
        }
        return averagespeed = s / laps.size();
    }

    public int getCal(Activity activity, UserProfile u) {
        CaloriesFactory calories = new CaloriesFactory();
        CaloriesCalculator c;
        if (u.getWeight() != 0) {
            if (u.getAge() != 0 && u.getGender() != ' ') {
                c = calories.createCalculator(CaloriesFactory.CalorieType.HEART_RATE);
            } else {
                c = calories.createCalculator(CaloriesFactory.CalorieType.SIMPLE);
            }
            return cal = c.calculate(activity, u);
        }
        return 0;
    }

    public void setLaps(List<Laps> laps) {
        this.laps = laps;
    }

}