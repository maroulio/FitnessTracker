package gr.hua.dit.fitnessmanager;
import java.util.ArrayList;
import java.util.List;

public abstract class Activity {
    private List<Laps> laps = new ArrayList<>();

    public double getTimeSeconds() {
        double s = 0;
        for (Laps lap: laps) {
            s += lap.getTimeSeconds();
        }
        return s;
    }

    public double getDistanceMeters() {
        double s = 0;
        for (Laps lap: laps) {
            s += lap.getDistanceMeters();
        }
        return s;
    }

    public double getAveragePace() {
        return getTimeSeconds() / 60 / (getDistanceMeters() / 1000);
    }

    public double getAHR() {
        int sum = 0;
        for (Laps lap: laps) {
            sum += (int) lap.getAHR();      }
        return (double) sum / laps.size();
    }

    public double getAverageSpeed() {
        double s = 0;
        for (Laps lap: laps) {
            s += lap.getAverageSpeed();
        }
        return s / laps.size();
    }


    public List<Laps> getLaps() {
        return laps;
    }
}