package gr.hua.dit.fitnessmanager;
// Class
public class CaloriesFactory {
    public enum CalorieType {
        SIMPLE,
        HEART_RATE
    }

    public CaloriesFactory() {}

    public CaloriesCalculator createCalculator(CalorieType type) {
        return switch (type) {
            case HEART_RATE -> new HRCaloriesCalculator();
            default -> new SimpleCaloriesCalculator();
        };
    }
}