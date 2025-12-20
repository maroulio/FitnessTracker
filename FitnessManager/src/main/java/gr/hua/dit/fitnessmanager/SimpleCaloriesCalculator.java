package gr.hua.dit.fitnessmanager;

public class SimpleCaloriesCalculator implements CaloriesCalculator {
    @Override
    public int calculate(Activity activity, UserProfile profile) {

        double mu;

        switch (activity.getSport().toLowerCase()) {
            case "walking":  mu = 0.06; break;
            case "running":  mu = 0.12; break;
            case "cycling":  mu = 0.09; break;
            case "swimming": mu = 0.13; break;
            default:         mu = 0.10;
        }

        return (int) (mu * profile.getWeight()
                * activity.getTimeSeconds());
    }
}
