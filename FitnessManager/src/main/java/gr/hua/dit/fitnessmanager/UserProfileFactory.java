package gr.hua.dit.fitnessmanager;

import java.util.Scanner;

public class UserProfileFactory {
    public static UserProfile fromConsole(UserProfile user) {

        Scanner sc = new Scanner(System.in);

        int g = user.getGender();
        int age = user.getAge();

        System.out.print("Please, enter your weight (kg): ");
        double weight = sc.nextDouble();

        String genderStr = (g == 1) ? "male" : "female";

        return new UserProfile(genderStr, age, weight);
    }
}
