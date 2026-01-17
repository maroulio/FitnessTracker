package gr.hua.dit.fitnessmanager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Tracks class represents a single track inside a lap.
 *
 * A track consists of multiple Trackpoints.
 * Each trackpoint contains time, distance, heart rate, speed, etc.
 *
 * Track-level statistics (time, distance, speed, heart rate)
 * are derived from its trackpoints.
 */

public class Tracks {
    /** List of trackpoints that belong to tracks */

    private List<Trackpoints> trackpoints = new ArrayList<>();

    public Tracks() {}
    /**
     * Returns the start time of the track.
     *
     * The start time is defined as the timestamp
     * of the first trackpoint.
     *
     * @return start time or null if no trackpoints exist
     */
    public LocalDateTime getStartTime() {
        return trackpoints.isEmpty() ? null : trackpoints.get(0).getTimeStamp();
    }

    /**
     * Calculates the total duration of the track in seconds.
     *
     * The duration is calculated as the difference
     * between the first and the last trackpoint timestamps.
     *
     * @return duration in seconds or 0 if not enough trackpoints
     */
    public double getTimeSeconds() {
        if (trackpoints.size() < 2) return 0;
        return Duration.between(
                trackpoints.get(0).getTimeStamp(),
                trackpoints.get(trackpoints.size() - 1).getTimeStamp()
        ).toSeconds();
    }
    /**
     * Calculates the total distance of the track in meters.
     *
     * The distance is computed as:
     *      last distance - first distance
     *
     * This assumes cumulative distance values in trackpoints.
     *
     * @return distance in meters or 0 if not enough data
     */
    public double getDistanceMeters() {
        if (trackpoints.size() < 2) return 0;

        double first = trackpoints.get(0).getDistanceMeters();
        double last  = trackpoints.get(trackpoints.size() - 1).getDistanceMeters();

        return Math.max(0, last - first);
    }
    /**
     * Calculates the average speed of the track.
     *
     * Priority:
     * 1. If speed values exist in trackpoints, compute their average.
     * 2. Otherwise, compute speed as distance / time.
     *
     * Speed is returned in meters per second (m/s).
     *
     * @return average speed or 0 if not available
     */
    public double getAverageSpeed() {

        // 1. Try to compute from trackpoint speeds
        double sum = 0;
        int count = 0;

        for (Trackpoints tp : trackpoints) {
            double s = tp.getSpeed();
            if (s > 0) {
                sum += s;
                count++;
            }
        }

        if (count > 0) {
            return sum / count;   // m/s
        }

        // 2. Fallback: distance / time
        double t = getTimeSeconds();
        if (t == 0) return 0;

        return getDistanceMeters() / t;
    }
    /**
     * Calculates the average heart rate of the track.
     *
     * Only positive heart rate values are considered.
     *
     * @return average heart rate in bpm or 0 if unavailable
     */
    public int getAHR() {
        int sum = 0;
        int count = 0;

        for (Trackpoints tp : trackpoints) {
            int hr = tp.getHeartRate();
            if (hr > 0) {
                sum += hr;
                count++;
            }
        }

        return count == 0 ? 0 : sum / count;
    }
    /**
     * Returns the maximum heart rate recorded in the track.
     *
     * Only positive heart rate values are considered.
     *
     * @return maximum heart rate in bpm or 0 if unavailable
     */
    public int getMHR() {
        int max = 0;

        for (Trackpoints tp : trackpoints) {
            int hr = tp.getHeartRate();
            if (hr > max) {
                max = hr;
            }
        }

        return max;
    }
    /**
     * Sets the list of trackpoints for this track.
     *
     * @param trackpoints list of trackpoints
     */
    public void setTrackpoints(List<Trackpoints> trackpoints) {
        this.trackpoints = trackpoints;
    }
    /**
     * Returns the list of trackpoints of this track.
     *
     * @return list of trackpoints
     */
    public List<Trackpoints> getTrackpoints() {
        return trackpoints;
    }

}