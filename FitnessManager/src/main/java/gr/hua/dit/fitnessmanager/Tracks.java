package gr.hua.dit.fitnessmanager;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tracks {
    private List<Trackpoints> trackpoints = new ArrayList<>();

    public void addTrackpoint(Trackpoints tp) {
        trackpoints.add(tp);
    }

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

    public List<Trackpoints> getTrackpoints() {
        return trackpoints;
    }

}