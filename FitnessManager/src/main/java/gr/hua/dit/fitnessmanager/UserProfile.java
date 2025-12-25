package gr.hua.dit.fitnessmanager;

public class UserProfile {

    private char gender; // "male" / "female"
    private int age;
    private double weight;

    public UserProfile() {
        this.gender ='male';
        this.age = 20;
        this.weight = 73.5;
    }

    public UserProfile() {}
    public char getGender() { return gender; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public void setGender(char gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }
    public void setWeight(double weight) { this.weight = weight; }
}