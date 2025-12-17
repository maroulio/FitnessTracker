package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Laps implements Active {
    private List<Tracks> tracks = new ArrayList<>();

    public Laps() {
        this.tracks = tracks;
    }

    public void addTrack(Tracks track) {
        tracks.add(track);
    }

    // ώρα πρώτου trackpoint του πρώτου track
    public LocalDateTime getStartTime() {
        if (tracks.isEmpty()) return null;
        return tracks.get(0).getStartTime();
    }

    public double getTimeSeconds() {
        return tracks.stream()
                .mapToDouble(Tracks::getTimeSeconds)
                .sum();
    }

    public double getDistanceMeters() {
        return tracks.stream()
                .mapToDouble(Tracks::getDistanceMeters)
                .sum();
    }

    public double getAverageSpeed() {
        double time = getTimeSeconds();
        if (time == 0) return 0;
        return getDistanceMeters() / time;
    }

    public int getAHR() {
        double sum = 0;
        int count = 0;

        for (Tracks t : tracks) {
            sum += t.getAHR() * t.getTrackpoints().size();
            count += t.getTrackpoints().size();
        }
        return count == 0 ? 0 : (int) (sum / count);
    }

    public int getMHR() {
        return tracks.stream()
                .mapToInt(Tracks::getMHR)
                .max()
                .orElse(0);
    }

    public List<Tracks> getTracksList() {
        return tracks;
    }

}