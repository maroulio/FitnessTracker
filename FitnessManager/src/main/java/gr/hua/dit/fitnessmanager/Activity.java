package gr.hua.dit.fitnessmanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Activity {
    private List<Laps> laps = new ArrayList<>();
    protected String sport;
    private LocalDateTime starttime;
    // Cached values (calculated on demand)
    private double timeseconds;
    private double distance;
    private double averagepace;
    private int ahr;
    private int mhr;
    private double averagespeed;
    private int cal;

    public Activity() {}
    /** List of laps that belong to this activity */
    public String getSport() {
        return sport;
    }
    /**
     * Returns the sport type of the activity.
     *
     * @return sport name
     */
    public LocalDateTime getStartTime() {
        return starttime;
    }
    /**
     * Calculates and returns the total duration of the activity in seconds.
     *
     * The total time is calculated as the sum of the durations
     * of all laps.
     *
     * @return total time in seconds
     */
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
        if (u.getWeight() > 0) {
            CaloriesFactory calories = new CaloriesFactory();
            CaloriesCalculator c;
            if (u.getAge() > 0 && (u.getGender() == 'm' || u.getGender() == 'f')) {
                c = calories.createCalculator(CaloriesFactory.CalorieType.HEART_RATE);
            } else {
                c = calories.createCalculator(CaloriesFactory.CalorieType.SIMPLE);
            }
            this.cal = c.calculate(activity, u);
            if (this.cal == 0) {
                if (laps.isEmpty()) {
                    return 0;
                }
                int sum = 0;
                boolean f = false;
                for (Laps lap: laps) {
                    if (lap.getCalories() == null) {
                        f = true;
                        break;
                    } else {
                        sum += lap.getCalories();
                    }
                }
                if (!f) {
                    return sum;
                }
            } else {
                return cal;
            }
        }
        return 0;
    }

    public void setLaps(List<Laps> laps) {
        this.laps = laps;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setStartTime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public void setTimeSeconds(double timeseconds) {
        this.timeseconds = timeseconds;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setAveragePace(double averagepace) {
        this.averagepace = averagepace;
    }

    public void setAHR(int ahr) {
        this.ahr = ahr;
    }

    public void setMHR(int mhr) {
        this.mhr = mhr;
    }

    public void setAverageSpeed(double averagespeed) {
        this.averagespeed = averagespeed;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }
}