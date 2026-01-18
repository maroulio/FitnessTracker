package gr.hua.dit.fitnessmanager;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIActivitiesPage {
    private JPanel panel = new JPanel();
    private JFileChooser fileChooser = new JFileChooser();
    private JList activitiesList;


    public GUIActivitiesPage() {
        panel.setLayout(null);
        int starting_x = 20;
        JButton backButton = new JButton("<--");
        backButton.setBounds(0, 0, 50, 20);
        backButton.addActionListener(e -> {
            GUI.setPanel(GUI.landingPage.getPanel());
        });
        activitiesList = new JList(GUI.inputHandler.getActivityNames().toArray());

        JButton tcxButton = new JButton("Choose tcx file");
        tcxButton.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                GUI.inputHandler.newActivity(selectedFile, selectedFile.getName());
                refreshList();
            }
        });
        JLabel activityInputLabel = new JLabel("or manually input an activity:");
        //text ether here or inside the button and disappearing somehow when typing
        JLabel nameLabel = new JLabel("name this activity\n" +
                "(ex. run 1)");
        JLabel typeLabel = new JLabel("choose activity type");
        JLabel durationLabel = new JLabel("duration (in seconds)");
        JLabel distanceLabel = new JLabel("distance (in meters)");
        JLabel avgPaceLabel = new JLabel("average pace???");
        JLabel avgHRLabel = new JLabel("average heart rate");
        JLabel maxHRLabel = new JLabel("maximum heart rate");
        JLabel avgSpeedLabel = new JLabel("average speed???");

        JTextField name = new JTextField();

        String[] activityTypeStrings = {"Walking", "Running", "Swimming", "Cycling", "Other"};
        //noinspection rawtypes,unchecked
        JComboBox typeList = new JComboBox(activityTypeStrings);
        typeList.setBackground(Color.white);


        JTextField duration = new JTextField();
        JTextField distance = new JTextField();
        JTextField avgPace = new JTextField();
        JTextField avgHR = new JTextField();
        JTextField maxHR = new JTextField();
        JTextField avgSpeed = new JTextField();

        JButton saveActButton = new JButton("Save Activity");
        saveActButton.addActionListener(e -> {

            //if the fields for an activity are not empty
            GUI.inputHandler.newActivity(name.getText().trim(),
                    typeList.getSelectedItem().toString(),
                    Double.parseDouble(duration.getText().trim()),
                    Double.parseDouble(distance.getText().trim()),
                    Double.parseDouble(avgPace.getText().trim()),
                    Integer.parseInt(avgHR.getText().trim()),
                    Integer.parseInt(maxHR.getText().trim()),
                    Double.parseDouble(avgSpeed.getText().trim()));


//            System.out.println(Double.parseDouble(duration.getText()) + "jbcjbkdcb"  + Double.parseDouble(distance.getText()) + "h khsbk" + Double.parseDouble(avgSpeed.getText()) + "ljsfjbf" + Double.parseDouble(avgPace.getText()) + "sjbcjsbdc" + Integer.parseInt(avgHR.getText()));
//            System.out.println("Activity page seconds, meters, avg speed, avg pace, avg heart rate:");

            //TODO empty text fields here
            name.setText(" ");
            duration.setText(" ");
            distance.setText(" ");
            avgPace.setText(" ");
            avgHR.setText(" ");
            maxHR.setText(" ");
            avgSpeed.setText(" ");
            refreshList();
        });


        JButton statisticsButton = new JButton("View Statistics");
        statisticsButton.addActionListener(e -> {
            if (GUI.inputHandler.getActivityNames().isEmpty()) {
                JOptionPane.showConfirmDialog(GUI.getFrame(), "Please input an activity first", "No activities", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (GUI.statisticsPage == null) {
                GUI.statisticsPage = new GUIStatisticsPage();
            }
            GUI.statisticsPage.refreshStats();
            JPanel statisticsPagePanel = GUI.statisticsPage.getPanel();
            //statisticsPagePanel.setVisible(true);
            GUI.setPanel(statisticsPagePanel);
        });


        JPanel LPanel = new JPanel();
        LPanel.setLayout(null);
        JPanel RPanel = new JPanel();
        RPanel.setLayout(null);
        //panel.setBorder(BorderFactory.createEmptyBorder(300, 500, 300, 500));
        RPanel.setBounds(GUI.WINDOW_WIDTH / 2, 0, GUI.WINDOW_WIDTH / 2, GUI.WINDOW_HEIGHT);
        LPanel.setBounds(0, 0, GUI.WINDOW_WIDTH / 2, GUI.WINDOW_HEIGHT);

        LPanel.add(backButton);

        tcxButton.setBounds(starting_x, 40, 150, 50);
        LPanel.add(tcxButton);

        activityInputLabel.setBounds(starting_x, 150, 350, 15);
        LPanel.add(activityInputLabel);
        nameLabel.setBounds(starting_x, 180, 350, 15);
        LPanel.add(nameLabel);
        name.setBounds(starting_x, 200, 150, 50);
        LPanel.add(name);
        typeLabel.setBounds(starting_x, 260, 350, 15);
        LPanel.add(typeLabel);
        typeList.setBounds(starting_x, 280, 150, 50);
        LPanel.add(typeList);
        durationLabel.setBounds(starting_x, 340, 350, 15);
        LPanel.add(durationLabel);
        duration.setBounds(starting_x, 360, 150, 50);
        LPanel.add(duration);
        distanceLabel.setBounds(starting_x, 420, 350, 15);
        LPanel.add(distanceLabel);
        distance.setBounds(starting_x, 440, 150, 50);
        LPanel.add(distance);


        avgPaceLabel.setBounds(starting_x + 225, 180, 350, 15);
        LPanel.add(avgPaceLabel);
        avgPace.setBounds(starting_x + 225, 200, 150, 50);
        LPanel.add(avgPace);
        avgHRLabel.setBounds(starting_x + 225, 260, 350, 15);
        LPanel.add(avgHRLabel);
        avgHR.setBounds(starting_x + 225, 280, 150, 50);
        LPanel.add(avgHR);
        maxHRLabel.setBounds(starting_x + 225, 340, 350, 15);
        LPanel.add(maxHRLabel);
        maxHR.setBounds(starting_x + 225, 360, 150, 50);
        LPanel.add(maxHR);
        avgSpeedLabel.setBounds(starting_x + 225, 420, 350, 15);
        LPanel.add(avgSpeedLabel);
        avgSpeed.setBounds(starting_x + 225, 440, 150, 50);
        LPanel.add(avgSpeed);

        saveActButton.setBounds(starting_x + 225, 520, 150, 30);
        LPanel.add(saveActButton);


        panel.add(new JSeparator(SwingConstants.VERTICAL));

        //showing files already put for that date
        RPanel.setLayout(null);

        activitiesList.setBounds(starting_x, 50, (GUI.WINDOW_WIDTH / 2) - 60, GUI.WINDOW_HEIGHT - 220);
        RPanel.add(activitiesList);
        statisticsButton.setBounds(starting_x, GUI.WINDOW_HEIGHT - 120, (GUI.WINDOW_WIDTH / 2) - 60, 50);
        RPanel.add(statisticsButton);


        //button.setBorder(BorderFactory.);
        //button.setMaximumSize(40, 90);


        panel.add(LPanel);
        panel.add(RPanel);
        panel.setSize(GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
        panel.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void refreshList(){
        activitiesList.setListData(GUI.inputHandler.getActivityNames().toArray());
    }
}
