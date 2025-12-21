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
            f = false;
            double weight = 0;
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-w")) {
                    try {
                        weight = Double.parseDouble(args[++i]);
                        f = true;
                    } catch (Exception e) { // It may be an InputMismatchException
                        return;
                    }
                    break;
                }
            }
            if (f) {
                f = false;
                int age = 0;
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-a")) {
                        try {
                            age = Integer.parseInt(args[++i]);
                            f = true;
                        } catch (Exception e) {
                            System.out.println("Total Calories: " + activity.getCal(weight, activity.getTimeSeconds()));
                            return;
                        }
                        break;
                    }
                }
                if (f) {
                    f = false;
                    char gender = '';
                    for (int i = 0; i < args.length; i++) {
                        if (args[i].equals("-g")) {
                            gender = args[++i].toLowerCase().charAt(0);
                            if (gender == 'm' || gender == 'f') {
                                System.out.println("Total Calories: " + activity.getCal(weight, age, gender, activity.getTimeSeconds()));
                            } else {
                                System.out.println("Total Calories: " + activity.getCal(weight, activity.getTimeSeconds()));
                            }
                        }
                    }
                }
            }
        }
    }
}