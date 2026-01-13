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
    private Double avgSpeed;

    // --------------------

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

    public double getavgSpeed() {
        if (avgSpeed != null) {
            return avgSpeed;
        }
        double t = gettotalTimeSeconds();
        if (t == 0) {return 0;}
        return getDistanceMeters() / t;
    }

    public int getAvgHR() {
        if (avgHR != null) {
            return avgHR;
        }
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
        if (maxHR != null) {
            return maxHR;
        }
        return tracks.stream()
                .mapToInt(Tracks::getMHR)
                .max()
                .orElse(0);
    }
  //  public int getCalories() {
      //  if (calories != null){return calories; }
    //    CaloriesFactory calories = new CaloriesFactory();
      //  return calories;
   // }


    // --------------------
    // setters ONLY for XML
    // --------------------

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
    public void setAvgSpeed(double speed) {this.avgSpeed = speed;}
}
