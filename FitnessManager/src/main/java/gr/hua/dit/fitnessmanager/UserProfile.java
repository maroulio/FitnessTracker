package gr.hua.dit.fitnessmanager;

public class UserProfile {

    private char gender; // "male" / "female"
    private int age;
    private double weight;

    public UserProfile() {
        this.gender = ' ';   // not set
        this.age = 0;        // not set
        this.weight = 0;   // not set
    }

    public char getGender() { return gender; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }

    public void setGender(char gender) {
        if (gender != 'm' && gender != 'f') {
            throw new IllegalArgumentException("Gender must be 'm' or 'f'");
        }
        this.gender = gender;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
        this.age = age;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }

    /**
     * Checks if the profile contains all data needed
     * for calorie calculations.
     */
    public boolean isValidForCalories() {
        return (gender == 'm' || gender == 'f') && age > 0 && weight > 0;
    }
}