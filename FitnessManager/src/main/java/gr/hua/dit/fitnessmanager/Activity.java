package gr.hua.dit.fitnessmanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Activity {
    /// List of laps that belong to this activity
    private List<Laps> laps = new ArrayList<>();
    private String sport;
    private String name;
    private LocalDateTime starttime;
    /// Cached values (calculated on demand)
    private double timeseconds;
    private double distance;
    private double averagepace;
    private int ahr;
    private int mhr;
    private double averagespeed;
    private int cal;

    public Activity() {}

    /**
     * Returns the sport type of the activity.
     *
     * @return sport name
     */
    public String getSport() {
        return sport;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return starttime;
    }

    /**
     * Calculates and returns the total duration of the activity in seconds.
     *
     * The total time is calculated as the sum of the durations
     * of all laps.
     *
     * @return total time in seconds or 0 if no laps exist
     */
    public double getTimeSeconds() {
        if (timeseconds != 0) {
            return timeseconds;
        } else if (laps.isEmpty()) {
            return 0;
        }
        double s = 0;
        for (Laps lap: laps) {
            s += lap.gettotalTimeSeconds();
        }
        return timeseconds = s;
    }

    /**
     * Calculates and returns the total distance of the activity in meters.
     *
     * The total distance is calculated as the sum of the distances
     * of all laps.
     *
     * @return total distance in meters or 0 if no laps exist
     */
    public double getDistanceMeters() {
        if (distance != 0) {
            return distance;
        } else if (laps.isEmpty()) {
            return 0;
        }
        double s = 0;
        for (Laps lap: laps) {
            s += lap.getDistanceMeters();
        }
        return distance = s;
    }

    /**
     * Calculates and returns the average pace of the activity in minutes per kilometer.
     *
     * It is computed using time in minutes and distance in kilometers as:
     *      time / distance
     *
     * @return average pace in minutes per kilometer or 0 if distance is zero
     */
    public double getAveragePace() {
        if (averagepace != 0) {
            return averagepace;
        } else if (distance == 0) {
            return 0;
        }
        return averagepace = timeseconds / 60 / (distance / 1000);
    }

    /**
     * Calculates and returns the average heart rate of the activity in beats per minute.
     *
     * It is computed using the sum of the average heart rates of all laps and the number of laps as:
     *      sum / number of laps
     *
     * @return average heart rate in beats per minute or 0 if no laps exist
     */
    public int getAHR() {
        if (ahr != 0) {
            return ahr;
        } else if (laps.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Laps lap: laps) {
            sum += lap.getAvgHR();
        }
        return ahr = sum / laps.size();
    }

    /**
     * Returns the maximum heart rate of the activity in beats per minute.
     *
     * The maximum heart rate among all laps is used.
     *
     * @return maximum heart rate in beats per minute or 0 if no laps exist
     */
    public int getMHR() {
        if (mhr != 0) {
            return mhr;
        } else if (laps.isEmpty()) {
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

    /**
     * Calculates and returns the average speed of the activity in meters per second.
     *
     * It is computed using the sum of the average speeds of all laps and the number of laps as:
     *      sum / number of laps
     *
     * @return average speed in meters per second or 0 if no laps exist
     */
    public double getAverageSpeed() {
        if (averagespeed != 0) {
            return averagespeed;
        } else if (laps.isEmpty()) {
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
            if (this.cal <= 0) {
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

    public int getCal() {
        return cal;
    }

    public void setLaps(List<Laps> laps) {
        this.laps = laps;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setName(String name) {
        this.name = name;
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