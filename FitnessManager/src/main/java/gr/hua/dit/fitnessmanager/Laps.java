package gr.hua.dit.fitnessmanager;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Laps {

    private List<Tracks> tracks = new ArrayList<>();

    // optional XML values
    private Double totalTimeSeconds;
    private Double distanceMeters;
    private Integer avgHR;
    private Integer maxHR;
    private int calories;
     private LocalDateTime StartTime;
    // --------------------
 //   public void addTrack(Tracks track) {
//        tracks.add(track);
   // }

    // --------------------
    public LocalDateTime getStartTime() {
        if (tracks.isEmpty()) return null;
        return tracks.get(0).getStartTime();
    }

    public double gettotalTimeSeconds() {
        if (totalTimeSeconds != null) {
            return totalTimeSeconds;
        }
        return tracks.stream()
                .mapToDouble(Tracks::getTimeSeconds)
                .sum();
    }

    public double getDistanceMeters() {
        if (distanceMeters != null) {
            return distanceMeters;
        }
        return tracks.stream()
                .mapToDouble(Tracks::getDistanceMeters)
                .sum();
    }

    public double getAverageSpeed() {
        double t = getTimeSeconds();
        if (t == 0) return 0;
        return getDistanceMeters() / t;
    }

    public int getAvgHR() {


        double sum = 0;
        int count = 0;
        for (Tracks t : tracks) {
            int n = t.getTrackpoints().size();
            sum += t.getAHR() * n;
            count += n;
        }
        return count == 0 ? 0 : (int) (sum / count);
    }

    public int getMaxHR() {

        return tracks.stream()
                .mapToInt(Tracks::getMHR)
                .max()
                .orElse(0);
    }



    // --------------------
    // setters ONLY for XML
    // --------------------
     public int getCalories() {
            CaloriesFactory calories = new CaloriesFactory();;
            return calories;
       }
    public List<Tracks> getTracksList() {
        return tracks;
    }

    public void settotalTimeSeconds(double t) {
        this.totalTimeSeconds = t;
    }
    public void setDistanceMeters(double d) {
        this.distanceMeters = d;
    }
    public void setAvgHR(int hr) {
        this.avgHR = hr;
    }
    public void setMaxHR(int hr) {
        this.maxHR = hr;
    }
    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.StartTime = startTime;
    }

}
