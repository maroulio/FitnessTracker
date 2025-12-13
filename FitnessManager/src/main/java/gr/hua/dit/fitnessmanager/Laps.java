package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;

public class Laps {
    private Tracks tracks;

    public Laps(Tracks tracks) {
        this.tracks = tracks;
    }

    public LocalDateTime getStartTime() {
        return tracks.StartTime();
    }
    public int getAHR() {
        return tracks.AHR();
    }

    public int getMHR() {
        return tracks.MHR();
    }
    public double getDistanceMeters() {
        return tracks.distanceMeters();
    }
    public double getTimeSeconds() {
        return tracks.timeSeconds();
    }
    public double getAverageSpeed() {
        return tracks.averageSpeed();
    }
    public Tracks getTracks() {
        return tracks;
    }
}