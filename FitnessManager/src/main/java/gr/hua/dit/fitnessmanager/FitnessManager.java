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
                    if (i + 1 < args.length) {
                        try {
                            age = Integer.parseInt(args[++i]);
                            if (age > 0) {
                                u.setAge(age);
                            }
                        } catch (NumberFormatException e) {
                            u.setAge(0);
                        }
                    }
                }
                case "-g" -> {
                    if (i + 1 < args.length) {
                        gender = args[++i].toLowerCase().strip().charAt(0);
                        if (gender == 'm' || gender == 'f') {
                            u.setGender(gender);
                        }
                    }
                }
                case "-w" -> {
                    if (i + 1 < args.length) {
                        try {
                            weight = Double.parseDouble(args[++i]);
                            if (weight > 0) {
                                u.setWeight(weight);
                            }
                        } catch (NumberFormatException e) {
                            u.setWeight(0);
                        }
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
                        System.out.println("--------------------------------------------------------------------------------");
                        if (activity.getSport() != null) {
                            System.out.println("Activity: " + activity.getSport());
                        }
                        if (activity.getTimeSeconds() != 0) {
                            System.out.print("Total Time: ");
                            if ((int) activity.getTimeSeconds() / 3600 >= 0 && (int) activity.getTimeSeconds() / 3600 <= 9) {
                                System.out.print('0');
                            }
                            System.out.print((int) activity.getTimeSeconds() / 3600 + ":");
                            if ((int) activity.getTimeSeconds() % 3600 / 60 >= 0 && (int) activity.getTimeSeconds() % 3600 / 60 <= 9) {
                                System.out.print('0');
                            }
                            System.out.print((int) activity.getTimeSeconds() % 3600 / 60 + ":");
                            if ((int) activity.getTimeSeconds() % 60 >= 0 && (int) activity.getTimeSeconds() % 60 <= 9) {
                                System.out.print('0');
                            }
                            System.out.println((int) activity.getTimeSeconds() % 60);
                        }
                        if (activity.getDistanceMeters() != 0) {
                            System.out.printf("Total Distance: %.2f km\n", activity.getDistanceMeters() / 1000);
                        }
                        if (activity.getAverageSpeed() != 0) {
                            System.out.printf("Avg Speed: %.2f km/h\n", activity.getAverageSpeed());
                        }
                        if (activity.getAveragePace() != 0) {
                            System.out.printf("Avg Pace: %.2f min/km\n", activity.getAveragePace());
                        }
                        if (activity.getAHR() != 0) {
                            System.out.printf("Avg Heart Rate: " + activity.getAHR() + " bpm\n");
                        }
                        if (activity.getCal(activity, u) != 0) {
                            System.out.println("Total calories burned: " + activity.getCal(activity, u) + " cal");
                        }
                        System.out.println("--------------------------------------------------------------------------------");
                    }
                }
            }
        }
    }
}