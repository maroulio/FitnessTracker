package gr.hua.dit.fitnessmanager;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tracks {
    private List<Trackpoints> trackpoints = new ArrayList<>();


    public LocalDateTime getStartTime() {
        return trackpoints.isEmpty() ? null : trackpoints.get(0).getTimeStamp();
    }

    public double getTimeSeconds() {
        if (trackpoints.size() < 2) return 0;
        return Duration.between(
                trackpoints.get(0).getTimeStamp(),
                trackpoints.get(trackpoints.size() - 1).getTimeStamp()
        ).toSeconds();
    }

    public double getDistanceMeters() {
        if (trackpoints.size() < 2) return 0;

        double first = trackpoints.get(0).getDistanceMeters();
        double last  = trackpoints.get(trackpoints.size() - 1).getDistanceMeters();

        return Math.max(0, last - first);
    }

    public double getAverageSpeed() {

        // 1. Try to compute from trackpoint speeds
        double sum = 0;
        int count = 0;

        for (Trackpoints tp : trackpoints) {
            Double s = tp.getSpeed();
            if (s != null && s > 0) {
                sum += s;
                count++;
            }
        }

        if (count > 0) {
            return sum / count;   // m/s
        }

        // 2. Fallback: distance / time
        double t = getTimeSeconds();
        if (t == 0) return 0;

        return getDistanceMeters() / t;
    }

    public int getAHR() {
        return (int) trackpoints.stream()
                .mapToInt(Trackpoints::getHeartRate)
                .average()
                .orElse(0);
    }

    public int getMHR() {
        return trackpoints.stream()
                .mapToInt(Trackpoints::getHeartRate)
                .max()
                .orElse(0);
    }

    public void setTrackpoints(List<Trackpoints> trackpoints) {
        this.trackpoints = trackpoints;
    }

    public List<Trackpoints> getTrackpoints() {
        return trackpoints;
    }

}