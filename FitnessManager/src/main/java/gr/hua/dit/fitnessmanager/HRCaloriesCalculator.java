package gr.hua.dit.fitnessmanager;

public class HRCaloriesCalculator implements CaloriesCalculator {
    @Override
    public int calculate(Activity activity, UserProfile profile) {

        double h = activity.getAHR();
        double w = profile.getWeight();
        int a = profile.getAge();
        double t = activity.getTimeSeconds() / 60.0; // λεπτά

        if (profile.getGender().equalsIgnoreCase("male")) {
            return (int) (
                    (-55.0969 + (0.6309 * h) + (0.1966 * w) + (0.2017 * a))
                            * t / 4.184
            );
        } else {
            return (int) (
                    (-20.4022 + (0.4472 * h) + (0.1263 * w) + (0.074 * a))
                            * t / 4.184
            );
        }
    }
}