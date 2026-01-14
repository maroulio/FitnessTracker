package gr.hua.dit.fitnessmanager;

public class ActivityFactory {

    public ActivityFactory() {}

    public Activity createActivity(String sport) {
        if (sport == null || sport.trim().isEmpty()) {
            System.out.println("Sport is null. Using default Activity (Other).");
            return new Other();
        }
        sport = sport.trim().toLowerCase();
        return switch (sport) {
            case "running" -> new Running();
            case "walking" -> new Walking();
            case "biking", "cycling" -> new Cycling();
            case "swimming" -> new Swimming();
            default -> new Other();
        };
    }
}