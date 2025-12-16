package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Laps {
    private List<Tracks> tracks;

    public Laps() {
        this.tracks = new ArrayList<>();
    }

    public void addTracks(Tracks tracks) {
        tracks.add(tracks);
    }

    // ώρα πρώτου trackpoint του πρώτου track
    public LocalDateTime getStartTime() {
        if (tracks.isEmpty()) return null;
        return tracks.get(0).startTime();
    }

    public double getTimeSeconds() {
        return tracks.stream()
                .mapToDouble(Tracks::timeSeconds)
                .sum();
    }

    public double getDistanceMeters() {
        return tracks.stream()
                .mapToDouble(Tracks::distanceMeters)
                .sum();
    }

    public double getAverageSpeed() {
        double time = getTimeSeconds();
        if (time == 0) return 0;
        return getDistanceMeters() / time;
    }

    public double getAHR() {
        double sum = 0;
        int count = 0;

        for (Tracks t : tracks) {
            sum += t.AHR() * t.getTrackpoints().size();
            count += t.getTrackpoints().size();
        }
        return count == 0 ? 0 : sum / count;
    }

    public int getMHR() {
        return tracks.stream()
                .mapToInt(Tracks::MHR)
                .max()
                .orElse(0);
    }

    public List<Tracks> getTracksList() {
        return tracks;
    }

}