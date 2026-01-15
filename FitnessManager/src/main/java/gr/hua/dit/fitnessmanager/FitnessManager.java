package gr.hua.dit.fitnessmanager;

import java.io.File;
import java.util.ArrayList;

public class FitnessManager {
    public static void main(String[] args) {

        int age;
        char gender;
        double weight;

        UserProfile u = new UserProfile();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a" -> {
                    age = Integer.parseInt(args[++i]);
                    // TODO: initialise attributes in user profile
                    u.setAge(age);
                    if (age < 0) {
                        System.out.printf("Invalid age provided: `%d`", age);
                    }
                }
                case "-g" -> {
                    gender = args[++i].toLowerCase().strip().charAt(0);
                    u.setGender(gender);
                    if ((gender != 'm') && (gender != 'f')) {
                        System.out.printf("Invalid gender provided: `%c`", gender);
                    }
                }
                case "-w" -> {
                    weight = Double.parseDouble(args[++i]);
                    u.setWeight(weight);
                    if (weight < 0) {
                        System.out.printf("Invalid weight provided: `%f`", weight);
                    }
                }
            }
        }

        for (String arg : args) {
            if (arg.contains(".tcx")) {
                File tcx = new File(arg);
                TCXParser parser = new TCXParser(tcx);
                ArrayList<Activity> act = parser.parse();

                if (!act.isEmpty()) {
                    for (Activity activity : act) {
                        // if (activity.getSport() != null) {
                            System.out.println("Activity: " + activity.getSport());
                            System.out.println("Total Time: " + activity.getTimeSeconds() / 3600 + ":" + activity.getTimeSeconds() % 3600 / 60);
                            System.out.println("Total Distance: " + activity.getDistanceMeters() / 1000);
                            if (activity.getAverageSpeed() != 0) {System.out.println("Avg Speed: " + activity.getAverageSpeed() + " km/h");}
                            if (activity.getAveragePace() != 0) {System.out.println("Avg Pace: " + activity.getAveragePace() + " min/km");}
                            if (activity.getAHR() != 0) {System.out.println("Avg Heart Rate: " + activity.getAHR() + " bpm");}
                            // TODO: cal should only be calculated if -w is passed, fix getCal's bugs
                            if (activity.getCal(activity, u) != 0) {System.out.println("Total calories burned: " + activity.getCal(activity, u) + " cal");}
                        // }
                    }
                }
            }
        }
    }
}