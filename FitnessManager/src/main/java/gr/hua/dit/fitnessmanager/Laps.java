package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;

public class Laps implements Active {
    private Tracks tracks;

    public Laps(Tracks tracks) {
        this.tracks = tracks;
    }

    public LocalDateTime getStartTime() {
        return tracks.getStartTime();
    }
    public int getAHR() {
        return tracks.getAHR();
    }

    public int getMHR() {
        return tracks.getMHR();
    }
    public double getDistanceMeters() {
        return tracks.getDistanceMeters();
    }
    public double getTimeSeconds() {
        return tracks.getTimeSeconds();
    }
    public double getAverageSpeed() {
        return tracks.getAverageSpeed();
    }
    public Tracks getTracks() {
        return tracks;
    }
}