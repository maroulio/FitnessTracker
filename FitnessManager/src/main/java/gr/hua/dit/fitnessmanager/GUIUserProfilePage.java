package gr.hua.dit.fitnessmanager;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
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
        NumberFormatter numberFormatter = new NumberFormatter(NumberFormat.getInstance());
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(0);
        numberFormatter.setMaximum(Integer.MAX_VALUE);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setCommitsOnValidEdit(true);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(starting_x, 70, 100, 30);
        age = new JFormattedTextField(numberFormatter);
        age.setBounds(starting_x, 100, 100, 50);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(starting_x, 160, 100, 30);
        gender = new JComboBox<>(new Character[]{'m', 'f'});
        gender.setBounds(starting_x, 190, 100, 50);

        JLabel weightLabel = new JLabel("Weight:");
        weightLabel.setBounds(starting_x, 250, 100, 30);
        weight = new JTextField();
        weight.setBounds(starting_x, 280, 100, 50);

        JLabel calorieGoalLabel = new JLabel("set daily calorie goal");
        calorieGoalLabel.setBounds(starting_x, 70, 150, 30);
        cal = new JFormattedTextField(numberFormatter);
        cal.setBounds(starting_x, 100, 100, 50);

        JLabel disclaimerLabel1 = new JLabel("simple calorie calculation requires weight input");
        JLabel disclaimerLabel2 = new JLabel("advanced calorie calculation requires");
        JLabel disclaimerLabel3 = new JLabel("weight, age, gender and heart rate input");
        disclaimerLabel1.setBounds(starting_x, 250, 400, 15);
        disclaimerLabel2.setBounds(starting_x, 280, 400, 15);
        disclaimerLabel3.setBounds(starting_x, 295, 400, 15);


        String[] typeStrings = {"Simple", "Advanced - more precise"};
        typeList = new JComboBox(typeStrings);
        typeList.setBounds(starting_x, 190, 250, 40);

        JButton backButton = new JButton("<--");
        backButton.setBounds(0, 0, 50, 20);
        backButton.addActionListener(e -> {

            GUI.setPanel(GUI.landingPage.getPanel());
            GUI.inputHandler.setUserProfile(
                    age.getText().trim().isBlank() ? 0 : Integer.parseInt(age.getText().trim()),
                    (Character) gender.getSelectedItem(),
                    weight.getText().trim().isBlank() ? 0 : Double.parseDouble(weight.getText().trim()),
                    cal.getText().trim().isBlank() ? 0 : Integer.parseInt(cal.getText().trim()),
                    (String) typeList.getSelectedItem());
        });

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

        JPanel RPanel = new JPanel();
        RPanel.setLayout(null);
        RPanel.setBounds(450, 0, 450, 650);
        RPanel.add(calorieGoalLabel);
        RPanel.add(cal);

        RPanel.add(typeList);
        RPanel.add(disclaimerLabel1);
        RPanel.add(disclaimerLabel2);
        RPanel.add(disclaimerLabel3);



        panel.add(LPanel);
        panel.add(RPanel);
        panel.setSize(GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
        panel.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setProfile(UserProfile u,int calGoal,String calculationType) {
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
