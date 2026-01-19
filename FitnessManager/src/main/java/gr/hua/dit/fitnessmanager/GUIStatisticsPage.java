package gr.hua.dit.fitnessmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;

public class GUIStatisticsPage {
    private JPanel panel;
    private JProgressBar bar = new JProgressBar(0, GUI.inputHandler.getCaloriesGoal());

    public GUIStatisticsPage() {
        refreshStats();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void refreshStats() {

        //back button setup
        JButton backButton = new JButton("<--");
        backButton.setBounds(0, 0, 50, 20);
        backButton.addActionListener(e -> {
            GUI.setPanel(GUI.activitiesPage.getPanel());
        });


        //statistic and calorie setup
        Activity stats = GUI.inputHandler.allActivityStatistics();
        bar.setMaximum(GUI.inputHandler.getCaloriesGoal());
        bar.setValue(stats.getCal());
        bar.setBounds(100, 100, 300, 35);


        //time format
        int duration = (int) stats.getTimeSeconds();
        int hours = duration / (60 * 60);
        duration = duration % (60 * 60);
        int mins = duration / 60;
        duration = duration % 60;
        int secs = duration;


        //statistics for all the activities setup
        JLabel label1 = new JLabel("All of the activity statistics:");
        JLabel label2 = new JLabel("Total time: " + hours + ":" + mins + ":" + secs);
        JLabel label3 = new JLabel("Total distance: " + String.format("%.3f",stats.getDistanceMeters() / 1000) + " km");
        JLabel label4 = new JLabel("Average pace: " + String.format("%.2f", stats.getAveragePace()) + " min/km");
        JLabel label5 = new JLabel("Average speed: " + String.format("%.2f", stats.getAverageSpeed()) + " km/h");
        JLabel label6 = new JLabel("Average heart rate: " + stats.getAHR() + " bpm");
        JLabel label7 = new JLabel("Total calories burned: " + stats.getCal());


        String temp = (GUI.inputHandler.getCaloriesGoal() - stats.getCal()) + " calories left to reach daily goal!!!";
        JLabel calorieProgressLabel = new JLabel(temp);
        calorieProgressLabel.setBounds(150, 140, 300, 15);

        bar.setBounds(new Rectangle());
        bar.setStringPainted(true);
        bar.setFont(new Font("MV Boli", Font.BOLD, 25));
        bar.setForeground(new Color(192, 213, 232));


        String[] actNameStrings = GUI.inputHandler.getActivityNames().toArray(new String[0]);
        JComboBox nameList = new JComboBox(actNameStrings);

        Activity activity = GUI.inputHandler.getActivity(0);

        duration = (int) activity.getTimeSeconds();
        hours = duration / (60 * 60);
        duration = duration % (60 * 60);
        mins = duration / 60;
        duration = duration % 60;
        secs = duration;
        JLabel label8 = new JLabel("Total time: " + hours + ":" + mins + ":" + secs);
        JLabel label9 = new JLabel("Total distance: " + String.format("%.3f", activity.getDistanceMeters() / 1000) + " km");
        JLabel label10 = new JLabel("Average pace: " + String.format("%.2f",activity.getAveragePace()) + " min/km");
        JLabel label11 = new JLabel("Avg Speed: " + String.format("%.2f", activity.getAverageSpeed()) + " km/h");
        JLabel label12 = new JLabel("Average heart rate: " + activity.getAHR() + " bpm");
        JLabel label13 = new JLabel("Total calories burned: " + activity.getCal(GUI.inputHandler.getUserProfile()));

        JLabel disclaimerLabel1 = new JLabel("if you want to calculate calories, ");
        disclaimerLabel1.setBounds(100, 160, 300, 15);
        JLabel disclaimerLabel2 = new JLabel("make sure user profile has the necessary data");
        disclaimerLabel2.setBounds(100, 180, 300, 15);

        nameList.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String nameOfActivity = e.getItem().toString();
                int indexOfActivity = Arrays.asList(actNameStrings).indexOf(nameOfActivity);
                Activity act = GUI.inputHandler.getActivity(indexOfActivity);

                label8.setText("Total time: " + act.getTimeSeconds());
                label9.setText("Total distance: " + act.getDistanceMeters() + " km");
                label10.setText("Average pace: " + act.getAveragePace() + " min/km");
                label11.setText("Avg Speed: " + act.getAverageSpeed() + " km/h");
                label12.setText("Average heart rate: " + act.getAHR() + " bpm");
                label13.setText("Total calories burned: " + act.getCal(GUI.inputHandler.getUserProfile()));
            }
        });


        //upper panel setup and additions
        JPanel UPanel = new JPanel();
        UPanel.setLayout(null);
        UPanel.setBounds(0, 0, GUI.WINDOW_WIDTH, 30);
        UPanel.add(backButton);


        //left panel setup and additions
        JPanel LPanel = new JPanel();
        LPanel.setBounds(0, 30, GUI.WINDOW_WIDTH / 2, GUI.WINDOW_HEIGHT - 280);
        LPanel.setLayout(new GridLayout(7, 1));
        LPanel.add(label1);
        LPanel.add(label2);
        LPanel.add(label3);
        LPanel.add(label4);
        LPanel.add(label5);
        LPanel.add(label6);
        LPanel.add(label7);


        //right panel setup and additions
        JPanel RPanel = new JPanel();
        RPanel.setBounds(GUI.WINDOW_WIDTH / 2, 30, (GUI.WINDOW_WIDTH / 2) - 20, GUI.WINDOW_HEIGHT - 280);
        RPanel.setLayout(new GridLayout(7, 1));
        RPanel.add(nameList);
        RPanel.add(label8);
        RPanel.add(label9);
        RPanel.add(label10);
        RPanel.add(label11);
        RPanel.add(label12);
        RPanel.add(label13);


        //down right panel setup and additions
        JPanel DLPanel = new JPanel();
        DLPanel.setBounds(0, GUI.WINDOW_HEIGHT - 250, GUI.WINDOW_WIDTH / 2, 250);
        DLPanel.add(bar);
        DLPanel.add(calorieProgressLabel);
        DLPanel.setLayout(null);


        //down right panel setup and additions
        JPanel DRPanel = new JPanel();
        DRPanel.setBounds(GUI.WINDOW_WIDTH / 2, GUI.WINDOW_HEIGHT - 250, GUI.WINDOW_WIDTH / 2, 250);
        DRPanel.setLayout(null);
        DRPanel.add(disclaimerLabel1);
        DRPanel.add(disclaimerLabel2);


        //panel setup and component additions
        panel = new JPanel();
        panel.setLayout(null);
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(UPanel);
        panel.add(LPanel);
        panel.add(RPanel);
        panel.add(DLPanel);
        panel.add(DRPanel);
        panel.setSize(GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
        panel.setVisible(true);
    }
}