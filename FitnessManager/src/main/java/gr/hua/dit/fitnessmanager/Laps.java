package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Laps {
    private List<Tracks> tracks = new ArrayList<>();

    public void addTrack(Tracks track) {
        tracks.add(track);
    }

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
        double t = getTimeSeconds();
        if (t == 0) return 0;
        return getDistanceMeters() / t;
    }

    public int getAHR() {
        double sum = 0;
        int count = 0;

        for (Tracks t : tracks) {
            int n = t.getTrackpoints().size();
            sum += t.getAHR() * n;
            count += n;
        }
        return count == 0 ? 0 : (int) (sum / count);
    }

    public int getMHR() {
        return tracks.stream()
                .mapToInt(Tracks::getMHR)
                .max()
                .orElse(0);
    }

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }

    public List<Tracks> getTracksList() {
        return tracks;
    }

}