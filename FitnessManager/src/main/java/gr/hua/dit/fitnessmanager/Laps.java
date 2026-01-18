package gr.hua.dit.fitnessmanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * The Laps class represents a single lap of an activity.
 * Each lap consists of one or more Tracks, which in turn
 * contain Trackpoints.
 *
 * Some values may be provided directly by the TCX file.
 * If not, they are calculated dynamically from the tracks.
 */
public class Laps {

    private List<Tracks> tracks = new ArrayList<>();

    /// optional XML values
    private Double totalTimeSeconds;
    private Double distanceMeters;
    private Integer avgHR;
    private Integer maxHR;
    private Integer calories;
    private LocalDateTime StartTime;
    private Double avgSpeed;

    public Laps() {}

    /**
     * Returns the start time of the lap.
     * The start time is considered to be the timestamp
     * of the first trackpoint of the first track.
     *
     * @return start time or null if no tracks exist
     */
    public LocalDateTime getStartTime() {
        if (tracks.isEmpty()) {
            return null;
        }
        return tracks.get(0).getStartTime();
    }

    /**
     * Returns the total duration of the lap in seconds.
     *
     * If the value exists in the XML file, it is returned directly.
     * Otherwise, it is calculated as the sum of the durations
     * of all tracks in the lap.
     *
     * @return total time in seconds
     */
    public double gettotalTimeSeconds() {
        if (totalTimeSeconds != null) {
            return totalTimeSeconds;
        }

        double sum = 0;
        for (Tracks t : tracks) {
            sum += t.getTimeSeconds();
        }
        return sum;
    }

    /**
     * Returns the total distance of the lap in meters.
     *
     * If the value exists in the XML file, it is returned directly.
     * Otherwise, it is calculated as the sum of distances
     * of all tracks in the lap.
     *
     * @return distance in meters
     */
    public double getDistanceMeters() {
        if (distanceMeters != null) {
            return distanceMeters;
        }
        double sum = 0;
        for (Tracks t : tracks) {
            sum += t.getDistanceMeters();
        }
        return sum;
    }

    /**
     * Calculates the average speed of the lap.
     *
     * If the value exists in the XML file, it is returned directly.
     * Otherwise, it is calculated using:
     *      distance / time
     *
     * @return average speed in meters per second or 0 if time is zero
     */
    public double getavgSpeed() {
        if (avgSpeed != null) {
            return avgSpeed;
        }
        double t = gettotalTimeSeconds();
        if (t == 0) {
            return 0;
        }
        return getDistanceMeters() / t;
    }

    /**
     * Returns the average heart rate of the lap.
     *
     * If the value exists in the XML file, it is returned directly.
     * Otherwise, a weighted average is calculated based on the
     * number of trackpoints in each track.
     *
     * @return average heart rate in beats per minute (bpm)
     */
    public int getAvgHR() {
        if (avgHR != null) {
            return avgHR;
        }
       int sum = 0;
        int count = 0;
        for (Tracks t : tracks) {
            int n = t.getTrackpoints().size();
            int ahr = t.getAHR();

            if (ahr > 0 && n > 0) {
                sum += ahr * n;
                count += n;
            }
        }
        return count == 0 ? 0: sum / count;
    }

    /**
     * Returns the maximum heart rate of the lap.
     *
     * If the value exists in the XML file, it is returned directly.
     * Otherwise, the maximum heart rate among all tracks is used.
     *
     * @return maximum heart rate in bpm
     */
    public int getMaxHR() {
        if (maxHR != null) {
            return maxHR;
        }

        int max = 0;
        for (Tracks t : tracks) {
            int hr = t.getMHR();
            if (hr > max) {
                max = hr;
            }
        }
        return max;
    }

    /**
     * Returns the calories burned in this lap if provided by the XML.
     *
     * @return calories burned or null if not available
     */
    public Integer getCalories() {
        if (calories != null) {
          return calories;
        }
        return null;
    }


    /// --------------------
    /// setters ONLY for XML
    /// --------------------

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