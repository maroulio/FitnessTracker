package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tracks {
    private List<Trackpoints> trackpoints = new ArrayList<>();

    public void addTrackpoint(Trackpoints tp) {
        trackpoints.add(tp);
    }

    public LocalDateTime startTime() {
        return trackpoints.isEmpty() ? null : trackpoints.get(0).getTime();
    }

    public double distanceMeters() {
        if (trackpoints.isEmpty()) return 0;
        return trackpoints.get(trackpoints.size() - 1).getDistanceMeters();
    }

    public double timeSeconds() {
        if (trackpoints.size() < 2) return 0;

        return Duration.between(
                trackpoints.get(0).getTime(),
                trackpoints.get(trackpoints.size() - 1).getTime()
        ).toSeconds();
    }

    public double averageSpeed() {
        double t = timeSeconds();
        if (t == 0) return 0;
        return distanceMeters() / t;  // m/s
    }

    public double AHR() {
        return trackpoints.stream()
                .mapToInt(Trackpoints::getHeartRate)
                .average()
                .orElse(0);
    }

    public int MHR() {
        return trackpoints.stream()
                .mapToInt(Trackpoints::getHeartRate)
                .max()
                .orElse(0);
    }

    public List<Trackpoints> getTrackpoints() {
        return trackpoints;
    }
}