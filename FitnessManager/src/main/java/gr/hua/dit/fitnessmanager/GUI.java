package gr.hua.dit.fitnessmanager;

//TODO:
// make GUI windows actually readable
// make female and male choices lowercase
// make a disclaimer for this - make the types that require certain user profile information that is ot given by the user, unclickable - if an activity does not have heart rate the print that it was not possible to use the complex type
// elegxoi egkurwthtas
// souloupoma
// make sure i check if it has tcx format

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

        //GUIUserProfilePage userProfilePage = new GUIUserProfilePage();
        //GUIStatisticsPage statisticsPage = new GUIStatisticsPage();
        //GUIActivitiesPage activitiesPage = new GUIActivitiesPage();
    }

    public static void setPanel(JPanel panel) {
        frame.setContentPane(panel);
    }

    public static JFrame getFrame(){
        return frame;
    }
}
