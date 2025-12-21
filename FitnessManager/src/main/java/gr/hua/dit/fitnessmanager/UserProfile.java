package gr.hua.dit.fitnessmanager;

public class UserProfile {

    private String gender; // "male" / "female"
    private int age;
    private double weight;

    public UserProfile(String gender, int age, double weight) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
    }

    public String getGender() { return gender; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
}