package gr.hua.dit.fitnessmanager;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class GUILandingPage {
    private JPanel panel = new JPanel();

    public GUILandingPage() {
        panel.setLayout(null);

        int starting_x = 20;

        JLabel dateLabel = new JLabel("Please insert a date to add activities");
        //dateLabel.setBounds(50, 50, 300, 300);
        JFormattedTextField dateInput = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        //dateInput.setBounds(50, 50, 300, 300);

        JButton nextButton = new JButton("NEXT");

        //make one object for every date
        //and a new statisticsPage object for every day
        nextButton.addActionListener(e -> {
            Object val = dateInput.getValue();
            if (val == null) {
                JOptionPane.showConfirmDialog(GUI.getFrame(), "Please input a correct date", "Invalid date", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.printf("Got date: %s", val);
            //
            LocalDate selectedDate = ((Date) val).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            GUI.inputHandler.setActiveDay(selectedDate);
            if (GUI.activitiesPage == null) {
                GUI.activitiesPage = new GUIActivitiesPage();
            }
            GUI.activitiesPage.refreshList();
            JPanel activitiesPagePanel = GUI.activitiesPage.getPanel();
            //activitiesPagePanel.setVisible(true);
            GUI.setPanel(activitiesPagePanel);
        });

        JLabel FMLabel = new JLabel("FM");
        JLabel fitnessManagerLabel = new JLabel("Fitness Manager");
        // the panel with the button and text

        JPanel LPanel = new JPanel();
        LPanel.setLayout(null);
        LPanel.setBackground(Color.RED);
        LPanel.setBounds(0, 0, 250, GUI.WINDOW_HEIGHT - 200);
        JPanel MPanel = new JPanel();
        MPanel.setLayout(null);
        //panel.setBorder(BorderFactory.createEmptyBorder(300, 500, 300, 500));
        MPanel.setLayout(new GridLayout(5, 1));
        MPanel.setBounds(250, 0, 400, GUI.WINDOW_HEIGHT - 200);
        MPanel.add(FMLabel);
        MPanel.add(fitnessManagerLabel);
        //panel.add(dateTextField);
        MPanel.add(dateLabel);
        MPanel.add(dateInput);
        MPanel.add(nextButton);
        MPanel.setBackground(Color.GREEN);


        JPanel RPanel = new JPanel();
        RPanel.setLayout(null);
        RPanel.setBounds(650, 0, 250, GUI.WINDOW_HEIGHT - 200);
        RPanel.setBackground(Color.BLUE);

        JButton userProfileButton = new JButton("User Profile");
        userProfileButton.addActionListener(e -> {
            if (GUI.userProfilePage == null) {
                GUI.userProfilePage = new GUIUserProfilePage();
            }
            GUI.userProfilePage.setProfile(GUI.inputHandler.getUserProfile(), GUI.inputHandler.getCaloriesGoal(), GUI.inputHandler.getCalorieCalculationType());
            JPanel userProfilePanel = GUI.userProfilePage.getPanel();
            //userProfilePanel.setVisible(true);
            GUI.setPanel(userProfilePanel);
        });

        JLabel caloriesTextLabel1 = new JLabel("For more accurate results on calories,");
        caloriesTextLabel1.setBounds(starting_x, 80, 350, 15);
        JLabel caloriesTextLabel2 = new JLabel("please enter your personal information on your profile:");
        caloriesTextLabel2.setBounds(starting_x, 95, 350, 15);
        userProfileButton.setBounds(starting_x, 115, 140, 30);
        JPanel DPanel = new JPanel(); //down panel
        DPanel.setLayout(null);
        DPanel.setBounds(0, GUI.WINDOW_HEIGHT - 200, GUI.WINDOW_WIDTH, 200);
        //DPanel.setLayout(new SpringLayout());
        DPanel.add(caloriesTextLabel1);
        DPanel.add(caloriesTextLabel2);
        DPanel.add(userProfileButton);
        DPanel.setBackground(Color.orange);


        panel.add(LPanel);
        panel.add(MPanel);
        panel.add(RPanel);
        panel.add(DPanel);
        panel.setSize(GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
        //frame.pack();
        panel.setVisible(true);


            /*JPanel panel = new JPanel();
            //panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
            panel.setLayout(new GridLayout(0, 1));
            panel.add(button);

            frame.add(panel, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Fitness Manager");
            frame.setSize(950,650);
            frame.pack();
            frame.setVisible(true);*/
    }

    public JPanel getPanel() {
        return panel;
    }
}
