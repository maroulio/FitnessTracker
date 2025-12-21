package gr.hua.dit.fitnessmanager;

public class SimpleCaloriesCalculator implements CaloriesCalculator {
    @Override
    public int calculate(Activity activity, UserProfile profile) {

        double mu;

        switch (activity.getSport().toLowerCase()) {
            case "walking":  mu =3.5; break;
            case "running":  mu = 9.8; break;
            case "cycling":  mu = 4.0; break;
            case "swimming": mu = 6.0; break;
            default:         mu =  1.0;
        }

        return (int) (mu * profile.getWeight()
                * activity.getTimeSeconds());
    }
}