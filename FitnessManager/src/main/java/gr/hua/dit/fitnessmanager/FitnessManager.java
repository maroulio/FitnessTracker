package gr.hua.dit.fitnessmanager;

import java.io.File;
import java.util.ArrayList;

public class FitnessManager {
    public static void main(String[] args) {
        int s = 0;
        for (int i = 0; i < args.length; i++) {
            if (!args[i].startsWith("-")) {
                s++;
            }
        }
        File[] file = new File[s];
        int k = 0;
        for (int i = 0; i < args.length; i++) {
            if (!args[i].startsWith("-")) {
                file[k++] = new File(args[i]);
            }
        }
        UserProfile u = new UserProfile();
        for (int i = 0; i < file.length; i++) {
            if (file[i].exists() && file[i].isFile()) {
                //parser

                String tcx = file[i].getAbsolutePath(); //μεχρι να μου δινετε το αρχειο απο τα args
                TCXParser parser = new TCXParser(tcx);
                ArrayList<Activity> act = parser.parse();

                for (int k = 0; k < act.toArray().length; k++) {
                    ActivityFactory sport = new ActivityFactory();
                    Activity activity = sport.createActivity(null);
                    System.out.println("Activity: " + activity.getName());
                    System.out.println("Total Time: " + activity.getTimeSeconds() / 60 + ":" + activity.getTimeSeconds() % 60);
                    System.out.println("Total Distance: " + activity.getDistanceMeters() / 1000);
                    System.out.println("Avg Pace: " + activity.getAveragePace() + " min/km");
                    System.out.println("Avg Heart Rate: " + activity.getAHR() + " bpm");
                    System.out.println("Avg Speed: " + activity.getAverageSpeed() + " km/h");
                    boolean f = false;
                    for (int j = 0; j < args.length; j++) {
                        if (args[j].startsWith("-")) {
                            f = true;
                            break;
                        }
                    }
                    if (f) {
                        double weight = 0;
                        int age = 0;
                        char gender = ' ';
                        for (int j = 0; j < args.length; j++) {
                            if (args[j].equals("-w")) {
                                if (j + 1 < args.length) {
                                    try {
                                        weight = Double.parseDouble(args[++j]);
                                        u.setWeight(weight);
                                    } catch (Exception e) { // It may be an InputMismatchException
                                        return;
                                    }
                                }
                            }
                            if (args[j].equals("-a")) {
                                if (j + 1 < args.length) {
                                    try {
                                        age = Integer.parseInt(args[++j]);
                                        u.setAge(age);
                                    } catch (Exception e) {
                                        return;
                                    }
                                }
                            }
                            if (args[j].equals("-g")) {
                                if (j + 1 < args.length) {
                                    try {
                                        gender = args[++j].toLowerCase().charAt(0);
                                        u.setGender(gender);
                                    } catch (Exception e) {
                                        return;
                                    }
                                }
                            }
                        }
                        System.out.println("Total Calories: " + activity.getCal(activity, u));
                    }
                }
            }
        }
    }
}