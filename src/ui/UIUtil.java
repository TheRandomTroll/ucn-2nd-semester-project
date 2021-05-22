package ui;

import models.Address;

import javax.swing.*;

public class UIUtil {
    public static void displayDBErrorMsg(String exceptionMessage) {
        JOptionPane.showMessageDialog(null,
                "An error has occurred while connecting to the database. Message: " + exceptionMessage,
                "Error connecting to database",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayMessage(String message, String title, int level) {
        JOptionPane.showMessageDialog(null, message, title, level);
    }

    public static Address parseAddress(JTextField textFieldStreet, JTextField textFieldStreetNumber, JTextField textFieldFloor, JTextField textFieldCity, JTextField textFieldPostalCode) {
        String street = textFieldStreet.getText();
        String streetNumber = textFieldStreetNumber.getText();
        String floor = textFieldFloor.getText();
        String city = textFieldCity.getText();
        String postalCode = textFieldPostalCode.getText();

        return new Address(street, streetNumber, floor, city, postalCode);
    }
}
