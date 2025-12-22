package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Laps {

    private List<Tracks> tracks = new ArrayList<>();
    private Double totalTimeSeconds;   // μπορεί να είναι null
    private Double distanceMeters;
    private int avgHR;
    private int maxHR;
    private double averagespeed;
    private LocalDateTime StartTime;

    public void addTrack(Tracks track) {
        tracks.add(track);
    }

    public double getTimeSeconds() {
        if (totalTimeSeconds != null) {
            return totalTimeSeconds;
        }
        return tracks.stream().mapToDouble(Tracks::getTimeSeconds).sum();
    }




    public int getAHR() {
        if (avgHR != null) {
            return avgHR;
        }
        public LocalDateTime getStartTime () {
            if (tracks.isEmpty()) return null;
            return tracks.get(0).getStartTime();
        }



        public double getDistanceMeters () {
            return tracks.stream()
                    .mapToDouble(Tracks::getDistanceMeters)
                    .sum();
        }

        public double getAverageSpeed () {
            double t = getTimeSeconds();
            if (t == 0) return 0;
            return getDistanceMeters() / t;
        }


        public int getMHR () {
            return tracks.stream()
                    .mapToInt(Tracks::getMHR)
                    .max()
                    .orElse(0);
        }
        //SETTERS
        public void setTimeSeconds ( double t){
            this.totalTimeSeconds = t;
        }

        public void setAHR ( int hr){
            this.avgHR = hr;
        }


        public void setDistanceMeters(Double distanceMeters) {
            this.distanceMeters = distanceMeters;
        }

        public void setMaxHR(int maxHR) {
            this.maxHR = maxHR;
        }

        public void setAveragespeed(double averagespeed) {
            this.averagespeed = averagespeed;
        }

        public void setTracks(List<Tracks> tracks) {
            this.tracks = tracks;
        }
        public void setStartTime(LocalDateTime startTime) {
            StartTime = startTime;
        }

        public List<Tracks> getTracksList () {
            return tracks;
        }
    }


}