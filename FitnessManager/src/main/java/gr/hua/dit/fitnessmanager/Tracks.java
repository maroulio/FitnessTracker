package gr.hua.dit.fitnessmanager;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tracks {
   // public LocalDateTime StartTime;
    private List<Trackpoints> trackpoints = new ArrayList<>();

    public void addTrackpoint(Trackpoints tp) {
        trackpoints.add(tp);
    }

    public LocalDateTime getStartTime() {
        return trackpoints.isEmpty() ? null : trackpoints.get(0).getTime();
    }

    public double getDistanceMeters() {
        if (trackpoints.isEmpty()) return 0;
        return trackpoints.get(trackpoints.size() - 1).getDistanceMeters();
    }

    public double getTimeSeconds() {
        if (trackpoints.size() < 2) return 0;

        return Duration.between(
                trackpoints.get(0).getTimeStamp(),
                trackpoints.get(trackpoints.size() - 1).getTimeStamp()
        ).toSeconds();
    }

    public double getAverageSpeed() {
        double t = getTimeSeconds();
        if (t == 0) return 0;
        return getDistanceMeters() / t;  // m/s
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

    public List<Trackpoints> getTrackpoints() {
        return trackpoints;
    }
}