package gr.hua.dit.fitnessmanager;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static gr.hua.dit.fitnessmanager.CaloriesFactory.CalorieType.HEART_RATE;
import static gr.hua.dit.fitnessmanager.CaloriesFactory.CalorieType.SIMPLE;

public class GUIInputHandler {

    private HashMap<LocalDate, ArrayList<Activity>> activities = new HashMap<LocalDate, ArrayList<Activity>>();
    private TCXParser parser;
    private UserProfile u = new UserProfile();
    private int calGoal = 1;
    private int cal = 0;
    private String calorieCalculationType = "Simple";
    private LocalDate activeDay;


    public GUIInputHandler() {
    }

    public ArrayList<String> getActivityNames() {

        ArrayList<String> names = new ArrayList<>();
        if (activities.get(activeDay) == null) {
            return new ArrayList<>();
        }
        for (Activity activity : activities.get(activeDay)) {
            names.add(activity.getName());
        }
        return names;
    }

    public Activity allActivityStatistics() {
        double timeSeconds = 0;
        double distanceMeters = 0;
        double averageSpeed = 0;
        double averagePace = 0;
        int averageHR = 0;


        for (Activity activity : activities.get(activeDay)) {   //activity.getTimeSeconds();
            timeSeconds += activity.getTimeSeconds();
            distanceMeters += activity.getDistanceMeters();
            averageSpeed += activity.getAverageSpeed();
            averagePace += activity.getAveragePace();
            averageHR += activity.getAHR();
            cal += activity.getCal(activity, u);

            System.out.println("seconds, meters, avg speed, avg pace, avg heart rate:");
            System.out.println(activity.getTimeSeconds() + activity.getDistanceMeters() + activity.getAverageSpeed() + activity.getAveragePace() + activity.getAHR());
        }

        int actSize = activities.size();

        ActivityFactory factory = new ActivityFactory();
        Activity act = factory.createActivity("Other");
        act.setName("statistics");
        if (actSize != 0) {
            act.setTimeSeconds(timeSeconds /= actSize);
            act.setDistance(distanceMeters /= actSize);
            act.setAverageSpeed(averageSpeed /= actSize);
            act.setAveragePace(averagePace /= actSize);
            act.setAHR(averageHR /= actSize);
            act.setCal(cal /= actSize);
        }

        return act;
    }

    public void newActivity(File tcx, String name) {

        parser = new TCXParser(tcx);
        ArrayList<Activity> act = parser.parse();
        for (Activity activity : act) {
            activity.setName(name);
        }

        if (!activities.containsKey(activeDay)) {
            activities.put(activeDay, new ArrayList<>());
        }

        activities.get(activeDay).addAll(act);
    }

    public void newActivity(String name, String type, double duration, double distance, double avgPace, int avgHR, double avgSpeed) {

        ActivityFactory factory = new ActivityFactory();
        Activity act = factory.createActivity(type);
        act.setName(name);
        act.setTimeSeconds(duration);
        act.setDistance(distance);
        act.setAveragePace(avgPace);
        act.setAHR(avgHR);
        act.setAverageSpeed(avgSpeed);

        if (!activities.containsKey(activeDay)) {
            activities.put(activeDay, new ArrayList<>());
        }

        activities.get(activeDay).add(act);
    }

    public void setUserProfile(int age, char gender, double weight, int calorieGoal, String preferredType) {
        //TODO maybe check if input is correct and maybe parse input

        u.setAge(age);
        u.setGender(gender);
        u.setWeight(weight);

        calGoal = calorieGoal;

        //TODO make calorie factory work with part 2 - make sure nothing from part 1 is missing in part 2

        CaloriesFactory calFactory = new CaloriesFactory();

        calorieCalculationType = preferredType;
        //un comment it out when the method is not static anymore
        if (Objects.equals(preferredType, "Simple")) {
            calFactory.createCalculator(SIMPLE);
        } else {
            calFactory.createCalculator(HEART_RATE);
        }

    }

    public int getCalPercentage() {
        if (calGoal != 0) {
            return cal / calGoal;
        }
        return 0;
    }

    public int getCaloriesLeft() {
        return calGoal - cal;
    }
    public int getCaloriesGoal() {
        return calGoal;
    }

    public String getCalorieCalculationType() {
        return calorieCalculationType;
    }

    public ArrayList<Activity> getActivities() {
        return activities.get(activeDay);
    }

    public Activity getActivity(int i) {
        return activities.get(activeDay).get(i);
    }

    public UserProfile getUserProfile() {
        return u;
    }

    public LocalDate getActiveDay() {
        return activeDay;
    }

    public void setActiveDay(LocalDate activeDay) {
        this.activeDay = activeDay;
    }
}
