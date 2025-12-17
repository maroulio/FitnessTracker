package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Activity implements Active {
    private List<Laps> laps = new ArrayList<>();
    private String name;
    private LocalDateTime starttime;
    private double timeseconds;
    private double distance;
    private double averagepace;
    private int ahr;
    private int mhr;
    private double averagespeed;
    private double cal;

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

    public List<Laps> getLaps() {
        return laps;
    }
}