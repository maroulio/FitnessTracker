package gr.hua.dit.fitnessmanager;
/// Class that uses an abstact calculate.
public abstract class CaloriesCalculator {
    public abstract int calculate(Activity activity, UserProfile profile);
}