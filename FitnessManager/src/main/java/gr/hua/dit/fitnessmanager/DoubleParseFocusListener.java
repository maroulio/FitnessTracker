package gr.hua.dit.fitnessmanager;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class DoubleParseFocusListener implements FocusListener {
    private JTextField textField;

    public DoubleParseFocusListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            Double.parseDouble(textField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showConfirmDialog(GUI.getFrame(), "Please input an fractional number", "Not a valid number", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }
}