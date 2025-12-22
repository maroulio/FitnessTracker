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
                    if (file.length > 1) {
                        int cal = 0;
                        boolean f = false;
                        for (int j = 0; j < args.length; j++) {
                            if (args[j].startsWith("-")) {
                                f = true;
                                break;
                            }
                        }
                        if (f) {
                            double weight = 0;
                            for (int j = 0; j < args.length; j++) {
                                if (args[j].equals("-w")) {
                                    if (j + 1 < args.length) {
                                        try {
                                            weight = Double.parseDouble(args[++j]);
                                        } catch (Exception e) { // It may be an InputMismatchException
                                            return;
                                        }
                                        break;
                                    }
                                }
                            }
                            f = false;
                            int age = 0;
                            for (int j = 0; j < args.length; j++) {
                                if (args[j].equals("-a")) {
                                    if (j + 1 < args.length) {
                                        try {
                                            age = Integer.parseInt(args[++j]);
                                        } catch (Exception e) {
                                            System.out.println("cal += activity.getCal(weight, activity.getTimeSeconds())");
                                            f = true;
                                        }
                                        break;
                                    }
                                }
                            }
                            if (!f) {
                                f = false;
                                char gender;
                                for (int j = 0; j < args.length; j++) {
                                    if (args[j].equals("-g")) {
                                        if (j + 1 < args.length) {
                                            gender = args[++j].toLowerCase().charAt(0);
                                            if (gender == 'm' || gender == 'f') {
                                                System.out.println("cal += activity.getCal(weight, age, gender, activity.getTimeSeconds())");
                                            } else {
                                                System.out.println("cal += activity.getCal(weight, activity.getTimeSeconds())");
                                            }
                                            f = true;
                                            break;
                                        }
                                    }
                                }
                                if (!f) {
                                    System.out.println("cal += activity.getCal(weight, activity.getTimeSeconds())");
                                }
                            } else {
                                System.out.println("cal += activity.getCal(weight, activity.getTimeSeconds())");
                            }
                        }
                        if (cal != 0) {
                            System.out.println("Total Calories: " + cal);
                        }
                    } else {
                        boolean f = false;
                        for (int j = 0; j < args.length; j++) {
                            if (args[j].startsWith("-")) {
                                f = true;
                                break;
                            }
                        }
                        if (f) {
                            double weight = 0;
                            for (int j = 0; j < args.length; j++) {
                                if (args[j].equals("-w")) {
                                    if (j + 1 < args.length) {
                                        try {
                                            weight = Double.parseDouble(args[++j]);
                                        } catch (Exception e) { // It may be an InputMismatchException
                                            return;
                                        }
                                        break;
                                    }
                                }
                            }
                            f = false;
                            int age = 0;
                            for (int j = 0; j < args.length; j++) {
                                if (args[j].equals("-a")) {
                                    if (j + 1 < args.length) {
                                        try {
                                            age = Integer.parseInt(args[++j]);
                                            f = true;
                                        } catch (Exception e) {
                                            System.out.println("Total Calories: + activity.getCal(weight, activity.getTimeSeconds())");
                                            return;
                                        }
                                        break;
                                    }
                                }
                            }
                            if (f) {
                                char gender = ' ';
                                for (int j = 0; j < args.length; j++) {
                                    if (args[j].equals("-g")) {
                                        if (j + 1 < args.length) {
                                            gender = args[++j].toLowerCase().charAt(0);
                                            if (gender == 'm' || gender == 'f') {
                                                System.out.println("Total Calories: + activity.getCal(weight, age, gender, activity.getTimeSeconds())");
                                            } else {
                                                System.out.println("Total Calories: + activity.getCal(weight, activity.getTimeSeconds())");
                                            }
                                            return;
                                        }
                                    }
                                }
                                System.out.println("Total Calories: + activity.getCal(weight, activity.getTimeSeconds())");
                                return;
                            }
                            System.out.println("Total Calories: + activity.getCal(weight, activity.getTimeSeconds())");
                        }
                    }
                }
            }
        }
    }
}