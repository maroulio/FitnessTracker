package gr.hua.dit.fitnessmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;

public class GUIStatisticsPage {
    private JPanel panel;
    private JProgressBar bar = new JProgressBar(0, 100000);

    public GUIStatisticsPage() {
        refreshStats();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void refreshStats() {
        panel = new JPanel();
        panel.setLayout(null);
        JButton backButton = new JButton("<--");
        backButton.setBounds(0, 0, 50, 20);
        backButton.addActionListener(e -> {
            GUI.setPanel(GUI.activitiesPage.getPanel());
        });

        Activity stats = GUI.inputHandler.allActivityStatistics();
        JLabel label1 = new JLabel("All of the activity statistics:");

        //TODO fix time format

        JLabel label2 = new JLabel("Total time: " + stats.getTimeSeconds());
        JLabel label3 = new JLabel("Total distance: " + stats.getDistanceMeters() / 1000 + "km");
        JLabel label4 = new JLabel("Average pace: " + stats.getAveragePace() + "min/km");
        JLabel label5 = new JLabel("Average speed: " + stats.getAverageSpeed() + "km/h");
        //TODO print doubles with %.2

        //TODO add average speed?
        JLabel label6 = new JLabel("Average heart rate: " + stats.getAHR() + "bpm");
        JLabel label7 = new JLabel("Total calories burned: "/* + act.getCal()*/);  //TODO fix this once getCal is fixed

        //TODO this needs refreshing or not? i think no
        String temp = (String.valueOf(GUI.inputHandler.getCaloriesLeft()) + " calories left to reach daily goal!!!");
        JLabel calorieProgressLabel = new JLabel(temp);

        bar.setValue(GUI.inputHandler.getCalPercentage());
        bar.setBounds(new Rectangle());  //maybe change it, there is one more set bounds method
        bar.setStringPainted(true);
        bar.setFont(new Font("MV Boli", Font.BOLD, 25));
        bar.setForeground(new Color(130, 62, 201));


        //a dropdown for choosing a tcx or activity to view statistics

        //TODO somehow make it work for each date

        String[] actNameStrings = GUI.inputHandler.getActivityNames().toArray(new String[0]);
        //noinspection rawtypes
        JComboBox nameList = new JComboBox(actNameStrings);

        //TODO somehow make dropdown work and get an activity


        Activity activity = GUI.inputHandler.getActivity(0);
        JLabel label8 = new JLabel("Total time: " + activity.getTimeSeconds());
        JLabel label9 = new JLabel("Total distance: " + activity.getDistanceMeters() + "km");
        JLabel label10 = new JLabel("Average pace: " + activity.getAveragePace() + "min/km");
        JLabel label11 = new JLabel("Average heart rate: " + activity.getAHR() + "bpm");
        JLabel label12 = new JLabel("Total calories burned: " + activity.getCal(activity, GUI.inputHandler.getUserProfile()));
        JLabel label13 = new JLabel("12  ");

        //TODO fix time format

        nameList.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String nameOfActivity = e.getItem().toString();
                int indexOfActivity = Arrays.asList(actNameStrings).indexOf(nameOfActivity);
                Activity act = GUI.inputHandler.getActivity(indexOfActivity);
                System.out.printf("Got item: %s\n", nameOfActivity);

                label8.setText("Total time: " + act.getTimeSeconds());
                label9.setText("Total distance: " + act.getDistanceMeters() + "km");
                label10.setText("Average pace: " + act.getAveragePace() + "min/km");
                label11.setText("Average heart rate: " + act.getAHR() + "bpm");
                label12.setText("Total calories burned: " + act.getCal(act, GUI.inputHandler.getUserProfile()));
            }
        });

        JPanel UPanel = new JPanel();
        UPanel.setLayout(null);
        UPanel.setBounds(0, 0, GUI.WINDOW_WIDTH, 30);

        UPanel.add(backButton);

        JPanel LPanel = new JPanel();
        JPanel RPanel = new JPanel();
        JPanel DPanel = new JPanel();
        JPanel MPanel = new JPanel();
        LPanel.setBackground(Color.red);
        RPanel.setBackground(Color.BLUE);
        DPanel.setBackground(Color.YELLOW);
        UPanel.setBackground(Color.ORANGE);

        LPanel.setBounds(0, 30, GUI.WINDOW_WIDTH / 2, GUI.WINDOW_HEIGHT - 60);
        RPanel.setBounds(GUI.WINDOW_WIDTH / 2, 30, GUI.WINDOW_WIDTH / 2, GUI.WINDOW_HEIGHT - 60);
        DPanel.setBounds(0, GUI.WINDOW_HEIGHT - 30, GUI.WINDOW_WIDTH, 30);
        MPanel.setBounds(0, 30, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT - 60);

        LPanel.setLayout(new GridLayout(7, 1));
        LPanel.add(label1);
        LPanel.add(label2);
        LPanel.add(label3);
        LPanel.add(label4);
        LPanel.add(label5);
        LPanel.add(label6);
        LPanel.add(label7);

        //progress bar
        bar.setBounds(150, 30, 150, 40);
        DPanel.add(bar);
        calorieProgressLabel.setBounds(150, 90, 150, 15);
        DPanel.add(calorieProgressLabel);
        DPanel.setLayout(null);

        panel.add(new JSeparator(SwingConstants.VERTICAL));

        RPanel.setLayout(new GridLayout(7, 1));

        RPanel.add(nameList);
        RPanel.add(label8);
        RPanel.add(label9);
        RPanel.add(label10);
        RPanel.add(label11);
        RPanel.add(label12);
        RPanel.add(label13);

        MPanel.add(LPanel);
        MPanel.add(RPanel);



        panel.add(UPanel);
        //panel.add(LPanel);
        //panel.add(RPanel);
        panel.add(DPanel);
        panel.add(MPanel);
        panel.setSize(GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
        panel.setVisible(true);
    }
}
