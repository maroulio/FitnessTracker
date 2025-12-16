package gr.hua.dit.fitnessmanager;
import java.time.LocalDateTime;

public interface Active {
    public LocalDateTime getStartTime();
    public double getTimeSeconds();
    public double getDistanceMeters();
    public int getAHR();
    public int getMHR();
}