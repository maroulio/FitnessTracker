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

        JLabel dateLabel = new JLabel("Please insert a date to add activities (dd/mm/yyyy format)");
        JFormattedTextField dateInput = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));

        JButton nextButton = new JButton("NEXT");


        nextButton.addActionListener(e -> {
            Object val = dateInput.getValue();
            if (val == null) {
                JOptionPane.showConfirmDialog(GUI.getFrame(), "Please input a correct date", "Invalid date", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.printf("Got date: %s", val);
            LocalDate selectedDate = ((Date) val).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            GUI.inputHandler.setActiveDay(selectedDate);
            if (GUI.activitiesPage == null) {
                GUI.activitiesPage = new GUIActivitiesPage();
            }
            GUI.activitiesPage.refreshList();
            JPanel activitiesPagePanel = GUI.activitiesPage.getPanel();
            GUI.setPanel(activitiesPagePanel);
        });

        JEditorPane FMLabel = new JEditorPane("text/plain", "FM");
        Font customFont
                = new Font("Serif", Font.BOLD, 60);
        FMLabel.setFont(customFont);
        FMLabel.setBackground(new Color(238, 238, 238));
        JLabel fitnessManagerLabel = new JLabel("Fitness Manager");

        JPanel LPanel = new JPanel();
        LPanel.setLayout(null);
        LPanel.setBounds(0, 0, 250, GUI.WINDOW_HEIGHT - 200);
        JPanel MPanel = new JPanel();
        MPanel.setLayout(null);

        MPanel.setBounds(250, 0, 400, GUI.WINDOW_HEIGHT - 200);
        FMLabel.setBounds(145, 85, 110, 80);
        MPanel.add(FMLabel);
        fitnessManagerLabel.setBounds(147, 160, 150, 30);
        MPanel.add(fitnessManagerLabel);
        dateLabel.setBounds(starting_x, 280, 350, 15);
        MPanel.add(dateLabel);
        dateInput.setBounds(starting_x, 300, 400 - (2 * starting_x), 30);
        MPanel.add(dateInput);
        nextButton.setBounds(0, 380, 400, 70);
        MPanel.add(nextButton);


        JPanel RPanel = new JPanel();
        RPanel.setLayout(null);
        RPanel.setBounds(650, 0, 250, GUI.WINDOW_HEIGHT - 200);
        JButton userProfileButton = new JButton("User Profile");
        userProfileButton.addActionListener(e -> {
            if (GUI.userProfilePage == null) {
                GUI.userProfilePage = new GUIUserProfilePage();
            }
            GUI.userProfilePage.setProfile(GUI.inputHandler.getUserProfile(), GUI.inputHandler.getCaloriesGoal(), GUI.inputHandler.getCalorieCalculationType());
            JPanel userProfilePanel = GUI.userProfilePage.getPanel();
            GUI.setPanel(userProfilePanel);
        });

        JLabel caloriesTextLabel1 = new JLabel("For calorie calculation,");
        caloriesTextLabel1.setBounds(starting_x, 80, 350, 15);
        JLabel caloriesTextLabel2 = new JLabel("please enter your personal information on your profile:");
        caloriesTextLabel2.setBounds(starting_x, 95, 350, 15);
        userProfileButton.setBounds(starting_x, 115, 140, 30);
        JPanel DPanel = new JPanel(); //down panel
        DPanel.setLayout(null);
        DPanel.setBounds(0, GUI.WINDOW_HEIGHT - 200, GUI.WINDOW_WIDTH, 200);
        DPanel.add(caloriesTextLabel1);
        DPanel.add(caloriesTextLabel2);
        DPanel.add(userProfileButton);


        panel.add(LPanel);
        panel.add(MPanel);
        panel.add(RPanel);
        panel.add(DPanel);
        panel.setSize(GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
        panel.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }
}
