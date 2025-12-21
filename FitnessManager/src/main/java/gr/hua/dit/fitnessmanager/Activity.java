package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Activity {
    private List<Laps> laps = new ArrayList<>();
    private String name;
    private LocalDateTime starttime;
    private double timeseconds;
    private double distance;
    private double averagepace;
    private int ahr;
    private int mhr;
    private double averagespeed;
    private int cal;
    protected String sport;

    public Activity() {
        this.laps = laps;
        this.name = name;
        this.starttime = starttime;
        this.timeseconds = timeseconds;
        this.distance = distance;
        this.averagepace = averagepace;
        this.ahr = ahr;
        this.mhr = mhr;
        this.averagespeed = averagespeed;
        this.cal = cal;
        this.sport = sport;
    }

    public List<Laps> getLaps() {
        return laps;
    }

    public String getName() {
        return name;
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
            s += lap.getTimeSeconds();
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
            sum += lap.getAHR();
        }
        return ahr = sum / laps.size();
    }

    public int getMHR() {
        if (laps.isEmpty()) {
            return 0;
        }
        int m = 0;
        for (Laps lap: laps) {
            if (lap.getMHR() > m) {
                m = lap.getMHR();
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
            s += lap.getAverageSpeed();
        }
        return averagespeed = s / laps.size();
    }

    public int getCal(double weight, int age, char gender, double timeseconds) {
        CaloriesFactory calories = new CaloriesFactory();
    }

    public int getCal(double weight, double timeseconds) {
        CaloriesFactory calories = new CaloriesFactory();
    }

    public String getSport() {
        return sport;
    }

    public void setLaps(List<Laps> laps) {
        this.laps = laps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public void setTimeseconds(double timeseconds) {
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

    public void setCal(double weight, int age, char gender, double timeseconds) {
        this.cal = cal;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}