package gr.hua.dit.fitnessmanager;

public class UserProfile {

    private char gender; // "male" / "female"
    private int age;
    private double weight;

    public UserProfile() {
        this.gender = '\0';   // not set
        this.age = -1;        // not set
        this.weight = -1.0;   // not set
    }

    public char getGender() { return gender; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }

    public void setGender(char gender) {
        if (gender != 'M' && gender != 'F') {
            throw new IllegalArgumentException("Gender must be 'M' or 'F'");
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
        return (gender == 'M' || gender == 'F')
                && age > 0
                && weight > 0;
    }
}
}