package gr.hua.dit.fitnessmanager;

public class ActivityFactory {

    public static Activity createActivity(String sport) {

        if (sport == null) {
            System.out.println("Sport is null. Using default Activity (Running).");
            return new Running();
        }

        sport = sport.trim().toLowerCase();

        switch (sport) {
            case "running":
                return new Running();

            case "walking":
                return new Walking();

            case "biking":
            case "cycling":
                return new Cycling();

            case "swimming":
                return new Swimming();

            default:
                System.out.println("Unknown Activity: " + sport +
                        ". Using default Activity (Running).");
                return new Running();
        }
    }
}