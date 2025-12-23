package gr.hua.dit.fitnessmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FitnessManager {
    public static void main(String[] args) {

        int age = -1;
        char gender = ' ';
        double weight = -1;

        final List<Character> valid_genders= Arrays.asList('m','f','M','F');

        UserProfile u = new UserProfile();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-a")) {
                age = Integer.parseInt(args[++i]);
                // TODO: initialise attributes in user profile
                u.setAge(age);
                if (age < 0){
                    System.out.printf("Invalid age provided: `%d`",age);
                }
            } else if (args[i].equals("-g")) {
                gender =  args[++i].strip().charAt(0);
                u.setGender(gender);
                if (!valid_genders.contains(gender)){
                    System.out.printf("Invalid gender provided: `%c`",gender);
                }
            } else if (args[i].equals("-w")) {
                weight = Double.parseDouble(args[++i]);
                u.setWeight(weight);
                if (weight < 0){
                    System.out.printf("Invalid weight provided: `%d`",weight);
                }
            }
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].contains(".tcx")) {
                File tcx = new File(args[i]);
                TCXParser parser = new TCXParser(tcx);
                ArrayList<Activity> act = parser.parse();

                for (int j = 0; j < act.size(); j++) {
                    Activity activity = act.get(j);
                    System.out.println("Activity: " + activity.getSport());
                    System.out.println("Total Time: " + activity.getTimeSeconds() / 60 + ":" + activity.getTimeSeconds() % 60);
                    System.out.println("Total Distance: " + activity.getDistanceMeters() / 1000);
                    if (activity.getAverageSpeed() != 0) {System.out.println("Avg Speed: " + activity.getAverageSpeed() + " km/h");}
                    if (activity.getAveragePace() != 0) {System.out.println("Avg Pace: " + activity.getAveragePace() + " min/km");}
                    if (activity.getAHR() != 0) {System.out.println("Avg Heart Rate: " + activity.getAHR() + " bpm");}
                    // TODO: cal should only be calculated if -w is passed, fix getCal's bugs
                    if (activity.getCal(activity, u) != 0) {System.out.println("Total calories burned: " + activity.getCal(activity, u) + " cal");}
                }
            }
        }
    }
}