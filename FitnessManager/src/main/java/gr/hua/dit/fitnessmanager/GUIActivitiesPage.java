package gr.hua.dit.fitnessmanager;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;

public class GUIActivitiesPage {
    private JPanel panel = new JPanel();
    private JFileChooser fileChooser = new JFileChooser();
    private JList activitiesList;
    JTextField duration = new JTextField("0.0");
    JTextField distance = new JTextField("0.0");
    JTextField avgPace = new JTextField("0.0");
    JTextField avgSpeed = new JTextField("0.0");
    JFormattedTextField avgHR;


    public GUIActivitiesPage() {
        int starting_x = 20;


        //setup number formatter
        NumberFormatter numberFormatter = new NumberFormatter(NumberFormat.getInstance());
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0);
        numberFormatter.setMaximum(Integer.MAX_VALUE);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setCommitsOnValidEdit(true);
        avgHR = new JFormattedTextField(numberFormatter);
        avgHR.setText("0");


        //back button setup
        JButton backButton = new JButton("<--");
        backButton.setBounds(0, 0, 50, 20);
        backButton.addActionListener(e -> {
            GUI.setPanel(GUI.landingPage.getPanel());
        });
        activitiesList = new JList(GUI.inputHandler.getActivityNames().toArray());


        //tcx chooser button setup
        JButton tcxButton = new JButton("Choose tcx file");
        tcxButton.setBounds(starting_x, 40, 150, 50);
        tcxButton.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                GUI.inputHandler.newActivity(selectedFile, selectedFile.getName());
                refreshList();
            }
        });


        //miscellaneous components of activities page setup
        JLabel activityInputLabel = new JLabel("or manually input an activity:");
        activityInputLabel.setBounds(starting_x, 150, 350, 15);

        JLabel nameLabel = new JLabel("name this activity\n" +
                "(ex. run 1)");
        nameLabel.setBounds(starting_x, 180, 350, 15);

        JLabel typeLabel = new JLabel("choose activity type");
        typeLabel.setBounds(starting_x, 260, 350, 15);

        JLabel durationLabel = new JLabel("duration (in seconds)");
        durationLabel.setBounds(starting_x, 340, 350, 15);

        JLabel distanceLabel = new JLabel("distance (in meters)");
        distanceLabel.setBounds(starting_x, 420, 350, 15);

        JLabel avgPaceLabel = new JLabel("average pace");
        avgPaceLabel.setBounds(starting_x + 225, 180, 350, 15);

        JLabel avgSpeedLabel = new JLabel("average speed");
        avgSpeedLabel.setBounds(starting_x + 225, 260, 350, 15);

        JLabel avgHRLabel = new JLabel("average heart rate");
        avgHRLabel.setBounds(starting_x + 225, 340, 350, 15);




        JTextField name = new JTextField("name");

        name.setBounds(starting_x, 200, 150, 50);
        duration.setBounds(starting_x, 360, 150, 50);
        distance.setBounds(starting_x, 440, 150, 50);
        avgPace.setBounds(starting_x + 225, 200, 150, 50);
        avgSpeed.setBounds(starting_x + 225, 280, 150, 50);
        avgHR.setBounds(starting_x + 225, 360, 150, 50);
        activitiesList.setBounds(starting_x, 50, (GUI.WINDOW_WIDTH / 2) - 60, GUI.WINDOW_HEIGHT - 220);



        //activity type list setup
        String[] activityTypeStrings = {"Walking", "Running", "Swimming", "Cycling", "Other"};
        JComboBox typeList = new JComboBox(activityTypeStrings);
        typeList.setBackground(Color.white);
        typeList.setBounds(starting_x, 280, 150, 50);


        //save activity button setup
        JButton saveActButton = new JButton("Save Activity");
        saveActButton.setBounds(starting_x + 225, 520, 150, 30);
        saveActButton.addActionListener(e -> {

            GUI.inputHandler.newActivity(name.getText().trim(),
                    typeList.getSelectedItem().toString(),
                    Double.parseDouble(duration.getText().trim()),
                    Double.parseDouble(distance.getText().trim()),
                    Double.parseDouble(avgPace.getText().trim()),
                    Integer.parseInt(avgHR.getText().trim()),
                    Double.parseDouble(avgSpeed.getText().trim()));
            name.setText("name");
            duration.setText("0.0");
            distance.setText("0.0");
            avgPace.setText("0.0");
            avgHR.setText("0");
            avgSpeed.setText("0.0");
            refreshList();
        });


        //input check with action listeners
        duration.addFocusListener(new DoubleParseFocusListener(duration));
        distance.addFocusListener(new DoubleParseFocusListener(distance));
        avgPace.addFocusListener(new DoubleParseFocusListener(avgPace));
        avgSpeed.addFocusListener(new DoubleParseFocusListener(avgSpeed));


        //view statistics button setup
        JButton statisticsButton = new JButton("View Statistics");
        statisticsButton.setBounds(starting_x, GUI.WINDOW_HEIGHT - 120, (GUI.WINDOW_WIDTH / 2) - 60, 50);
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
            GUI.setPanel(statisticsPagePanel);
        });


        //left panel setup and component additions
        JPanel LPanel = new JPanel();
        LPanel.setLayout(null);
        LPanel.setBounds(0, 0, GUI.WINDOW_WIDTH / 2, GUI.WINDOW_HEIGHT);
        LPanel.add(backButton);
        LPanel.add(tcxButton);
        LPanel.add(activityInputLabel);
        LPanel.add(nameLabel);
        LPanel.add(name);
        LPanel.add(typeLabel);
        LPanel.add(typeList);
        LPanel.add(durationLabel);
        LPanel.add(duration);
        LPanel.add(distanceLabel);
        LPanel.add(distance);
        LPanel.add(avgPaceLabel);
        LPanel.add(avgPace);
        LPanel.add(avgSpeedLabel);
        LPanel.add(avgSpeed);
        LPanel.add(avgHRLabel);
        LPanel.add(avgHR);
        LPanel.add(saveActButton);


        //right panel setup and component additions
        JPanel RPanel = new JPanel();
        RPanel.setLayout(null);
        RPanel.setBounds(GUI.WINDOW_WIDTH / 2, 0, GUI.WINDOW_WIDTH / 2, GUI.WINDOW_HEIGHT);
        RPanel.add(activitiesList);
        RPanel.add(statisticsButton);


        //panel setup and component additions
        panel.setLayout(null);
        panel.add(LPanel);
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(RPanel);
        panel.setSize(GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
        panel.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void refreshList() {
        activitiesList.setListData(GUI.inputHandler.getActivityNames().toArray());
    }
}