package gr.hua.dit.fitnessmanager;

public class HRCaloriesCalculator extends CaloriesCalculator {

    @Override
    public int calculate(Activity activity, UserProfile profile) {

        if (!profile.isValidForCalories()) {
            throw new IllegalStateException("Incomplete user profile");
        }

        double h = activity.getAHR();              // avg heart rate
        double w = profile.getWeight();             // weight (kg)
        int a = profile.getAge();                   // age (years)
        double t = activity.getTimeSeconds() / 60.0; // time (minutes)

        double calories;

        if (profile.getGender() == 'M') {
            calories =
                    (-55.0969 + (0.6309 * h) + (0.1966 * w) + (0.2017 * a))
                            * t / 4.184;
        } else {
            calories =
                    (-20.4022 + (0.4472 * h) + (0.1263 * w) + (0.074 * a))
                            * t / 4.184;
        }

        return (int) Math.round(calories);
    }
}