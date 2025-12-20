package gr.hua.dit.fitnessmanager;

public class FitnessManager {
    public static void main(String[] args) {
        //parser

        String tcx = "walking_activity_1.tcx"; //μεχρι να μου δινετε το αρχειο απο τα args
        TCXParser parser = new TCXParser(tcx);
        parser.parse();


        ActivityFactory sport = new ActivityFactory();
        Activity activity = sport.createActivity(null);
        System.out.println("Activity: " + activity.getName());
        System.out.println("Total Time: " + activity.getTimeSeconds() / 60 + ":" + activity.getTimeSeconds() % 60);
        System.out.println("Total Distance: " + activity.getDistanceMeters() / 1000);
        System.out.println("Avg Pace: " + activity.getAveragePace() + " min/km");
        System.out.println("Avg Heart Rate: " + activity.getAHR() + " bpm");
        System.out.println("Avg Speed: " + activity.getAverageSpeed() + " km/h");
        boolean f = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                f = true;
                break;
            }
        }
        if (f) {
            User u = new User();
            if (u.getGender() == 1) {
                u.getAge();
            } else {
                u.getAge();
            }
            System.out.println("Total Calories: ");
        }
    }
}