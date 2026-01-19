package gr.hua.dit.fitnessmanager;
/// Class that uses the factory model to calculate calories
public class CaloriesFactory {
    /// Enumaratiion for calory types
    public enum CalorieType {
        SIMPLE,
        HEART_RATE
    }

    public CaloriesFactory() {}
/// If -w is pressed then HRC is used ,else the simple type is used
    public CaloriesCalculator createCalculator(CalorieType type) {
        return switch (type) {
            case HEART_RATE -> new HRCaloriesCalculator();
            default -> new SimpleCaloriesCalculator();
        };
    }
}