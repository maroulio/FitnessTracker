package gr.hua.dit.fitnessmanager;

//TODO:
// make sure even if there is no value put in by the user i can print 0 in the place
// elegxoi egkurwthtas & make text disappear when clicking user profile
// souloupoma
// make sure i check if it has tcx format
// last visual details for GUI

//TODO
// look if anything in part 1 is not working
// readme


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
