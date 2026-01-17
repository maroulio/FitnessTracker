package gr.hua.dit.fitnessmanager;

public class HRCaloriesCalculator extends CaloriesCalculator {
///Class that calculates calories if all arguments are typed (-w -a -g )

    @Override
    public int calculate(Activity activity, UserProfile profile) {

        double h = 0;
        if (activity.getAHR() > 0) {
            h = activity.getAHR();
        }
        double w = 0;
        if (profile.getWeight() > 0) {
            w = profile.getWeight();
        }
        int a = 0;
        if (profile.getAge() > 0) {
            a = profile.getAge();
        }
        double t = 0;
        if (activity.getTimeSeconds() / 60.0 > 0) {
            t = activity.getTimeSeconds() / 60.0;
        }

        double calories = 0;
        if (profile.getGender() == 'm' || profile.getGender() == 'f') {
            if (profile.getGender() == 'm') {
                calories =
                        (-55.0969 + (0.6309 * h) + (0.1966 * w) + (0.2017 * a))
                                * t / 4.184;
            } else {
                calories =
                        (-20.4022 + (0.4472 * h) + (0.1263 * w) + (0.074 * a))
                                * t / 4.184;
            }
        }

        return (int) Math.round(calories);
    }
}