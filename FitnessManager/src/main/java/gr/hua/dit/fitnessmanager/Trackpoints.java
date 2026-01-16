package gr.hua.dit.fitnessmanager;

import java.time.LocalDateTime;

public class Trackpoints {
    private LocalDateTime timeStamp;
    private double latitudeDegrees;
    private double longitudeDegrees;
    private double altitudeMeters;
    private double distanceMeters;

    //running 2 does not have heart rate
    private int heartRate;
    private double speed;

    public Trackpoints() {}

    public Trackpoints(LocalDateTime timeStamp, double latitudeDegrees, double longitudeDegrees, double altitudeMeters, double distanceMeters, int heartRate, double speed) {
        this.timeStamp = timeStamp;
        this.latitudeDegrees = latitudeDegrees;
        this.longitudeDegrees = longitudeDegrees;
        this.altitudeMeters = altitudeMeters;
        this.distanceMeters = distanceMeters;
        this.heartRate = heartRate;
        this.speed = speed;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getLatitudeDegrees() {
        return latitudeDegrees;
    }

    public void setLatitudeDegrees(double latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    public double getLongitudeDegrees() {
        return longitudeDegrees;
    }

    public void setLongitudeDegrees(double longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    public double getAltitudeMeters() {
        return altitudeMeters;
    }

    public void setAltitudeMeters(double altitudeMeters) {
        this.altitudeMeters = altitudeMeters;
    }

    public double getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(double distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}