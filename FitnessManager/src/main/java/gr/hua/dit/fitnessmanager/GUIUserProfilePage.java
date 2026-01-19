package gr.hua.dit.fitnessmanager;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class GUIUserProfilePage {
    private JPanel panel = new JPanel();
    private JFormattedTextField age;
    private JTextField weight;
    private JComboBox<Character> gender;
    private JFormattedTextField cal;
    private JComboBox typeList;

    public GUIUserProfilePage() {
        panel.setLayout(null);
        int starting_x = 20;


        //setup number formatter
        NumberFormatter numberFormatter = new NumberFormatter(NumberFormat.getInstance());
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0);
        numberFormatter.setMaximum(Integer.MAX_VALUE);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setCommitsOnValidEdit(true);


        //back button setup
        JButton backButton = new JButton("<--");
        backButton.setBounds(0, 0, 50, 20);
        backButton.addActionListener(e -> {

            GUI.setPanel(GUI.landingPage.getPanel());
            GUI.inputHandler.setUserProfile(
                    age.getText().trim().isBlank() ? 0 : Integer.parseInt(age.getText().trim().replaceAll(",", "")),
                    (Character) gender.getSelectedItem(),
                    weight.getText().trim().isBlank() ? 0 : Double.parseDouble(weight.getText().trim()),
                    cal.getText().trim().isBlank() ? 0 : Integer.parseInt(cal.getText().trim().replaceAll(",", "")),
                    (String) typeList.getSelectedItem());
        });


        //miscellaneous components of profile page setup
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(starting_x, 70, 100, 30);
        age = new JFormattedTextField(numberFormatter);
        age.setBounds(starting_x, 100, 100, 50);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(starting_x, 160, 100, 30);
        gender = new JComboBox<>(new Character[]{'m', 'f'});
        gender.setBounds(starting_x, 190, 100, 50);
        gender.setBackground(Color.white);

        JLabel weightLabel = new JLabel("Weight:");
        weightLabel.setBounds(starting_x, 250, 100, 30);
        weight = new JTextField();
        weight.setBounds(starting_x, 280, 100, 50);
        weight.addFocusListener(new DoubleParseFocusListener(weight));

        JLabel calorieGoalLabel = new JLabel("set daily calorie goal");
        calorieGoalLabel.setBounds(starting_x, 70, 150, 30);
        cal = new JFormattedTextField(numberFormatter);
        cal.setBounds(starting_x, 100, 100, 50);

        JLabel disclaimerLabel1 = new JLabel("simple calorie calculation requires weight input");
        disclaimerLabel1.setBounds(starting_x, 300, 400, 15);

        JLabel disclaimerLabel2 = new JLabel("advanced calorie calculation requires");
        disclaimerLabel2.setBounds(starting_x, 330, 400, 15);

        JLabel disclaimerLabel3 = new JLabel("weight, age, gender and heart rate input");
        disclaimerLabel3.setBounds(starting_x, 345, 400, 15);


        String[] typeStrings = {"Simple", "Advanced - more precise"};
        typeList = new JComboBox(typeStrings);
        typeList.setBackground(Color.white);
        typeList.setBounds(starting_x, 190, 250, 40);


        //left panel setup and component additions
        JPanel LPanel = new JPanel();
        LPanel.setBounds(0, 0, 450, 650);
        LPanel.setLayout(null);
        LPanel.add(backButton);
        LPanel.add(ageLabel);
        LPanel.add(age);
        LPanel.add(genderLabel);
        LPanel.add(gender);
        LPanel.add(weightLabel);
        LPanel.add(weight);


        //right panel setup and component additions
        JPanel RPanel = new JPanel();
        RPanel.setLayout(null);
        RPanel.setBounds(450, 0, 450, 650);
        RPanel.add(calorieGoalLabel);
        RPanel.add(cal);
        RPanel.add(typeList);
        RPanel.add(disclaimerLabel1);
        RPanel.add(disclaimerLabel2);
        RPanel.add(disclaimerLabel3);


        //panel setup and component additions
        panel.add(LPanel);
        panel.add(RPanel);
        panel.setSize(GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
        panel.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setProfile(UserProfile u, int calGoal, String calculationType) {
        if (u == null) {
            return;
        }

        age.setText(String.valueOf(u.getAge()));

        if (u.getGender() == 'M') {
            gender.setSelectedItem('M');
        } else {
            gender.setSelectedItem('F');
        }

        weight.setText(String.valueOf(u.getWeight()));

        cal.setText(String.valueOf(calGoal));

        typeList.setSelectedItem(calculationType);
    }
}