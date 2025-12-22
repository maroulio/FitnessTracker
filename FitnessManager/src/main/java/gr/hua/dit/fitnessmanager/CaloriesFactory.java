package gr.hua.dit.fitnessmanager;

public class CaloriesFactory {
    public enum CalorieType {
        SIMPLE,
        HEART_RATE
    }

    public CaloriesFactory() {}

    public CaloriesCalculator createCalculator(CalorieType type) {
        switch (type) {
            case HEART_RATE:
                return new HRCaloriesCalculator();
            case SIMPLE:
            default:
                return new SimpleCaloriesCalculator();
        }
    }
}