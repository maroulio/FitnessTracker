package gr.hua.dit.fitnessmanager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
    private int gender;
    private int age;
    private Scanner s = new Scanner(System.in);

    public User() {
        this.gender = gender;
        this.age = age;
        this.s = s;
    }

    public int getGender() {
        System.out.println("Please, enter your gender:\n1. Male\n2. Female\n");
        System.out.print("Select: ");
        do {
            try {
                gender = s.nextInt();
                s.nextLine();
            } catch (InputMismatchException e) {
                System.err.print("Invalid choice! Try again: ");
                s.nextLine();
                continue;
            }
            if (gender < 1 || gender > 2) {
                System.err.print("Invalid choice! Try again: ");
            } else {
                break;
            }
        } while (true);
        return gender;
    }

    public int getAge() {
        System.out.print("Please, enter your age: ");
        do {
            try {
                age = s.nextInt();
                s.nextLine();
            } catch (InputMismatchException e) {
                System.err.print("Invalid choice! Try again: ");
                s.nextLine();
                continue;
            }
            if (age < 1) {
                System.err.print("Invalid choice! Try again: ");
            } else {
                break;
            }
        } while (true);
        return age;
    }
}