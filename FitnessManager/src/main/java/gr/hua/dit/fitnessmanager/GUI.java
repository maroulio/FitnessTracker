package gr.hua.dit.fitnessmanager;

//TODO:
// make sure that the calorie goal i just put 0 to on input handler does not cause any problems
// souloupoma
// readme
// last visual details for GUI

import javax.swing.*;

public class GUI {
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 650;


    private static JFrame frame;
    public static GUILandingPage landingPage;
    public static GUIUserProfilePage userProfilePage;
    public static GUIActivitiesPage activitiesPage;
    public static GUIStatisticsPage statisticsPage;

    public static GUIInputHandler inputHandler = new GUIInputHandler();


    public static void main(String[] args) {
        frame = new JFrame();
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Fitness Manager - User Profile");
        frame.setSize(GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);

        landingPage = new GUILandingPage();
        frame.setContentPane(landingPage.getPanel());
    }

    public static void setPanel(JPanel panel) {
        frame.setContentPane(panel);
    }

    public static JFrame getFrame(){
        return frame;
    }
}
