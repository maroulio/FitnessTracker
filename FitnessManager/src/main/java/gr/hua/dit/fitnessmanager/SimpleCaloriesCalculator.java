package gr.hua.dit.fitnessmanager;
/// Class that calculates the calories if -w is typed but not -a and -g
public class SimpleCaloriesCalculator extends CaloriesCalculator {

    @Override
    public int calculate(Activity activity, UserProfile profile) {
/// mu  types
        double mu;

        switch (activity.getSport().toLowerCase()) {
            case "walking":  mu = 3.5; break;
            case "running":  mu = 9.8; break;
            case "cycling", "biking":  mu = 4.0; break;
            case "swimming": mu = 6.0; break;
            default:         mu = 1.0;
        }

        double t = activity.getTimeSeconds() / 3600.0;
        if (profile.getWeight() > 0) {
            return (int) Math.round(mu * profile.getWeight() * t);
        }
        return 0;
    }
}