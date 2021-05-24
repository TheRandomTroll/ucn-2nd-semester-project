package ui;

import models.Address;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UIUtil {
    public static void displayDBErrorMsg(String exceptionMessage) {
        displayMessage("An error has occurred connecting to the database. Error message: " + exceptionMessage, "Error connecting to database", JOptionPane.ERROR_MESSAGE);
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

    public static List<JTextField> getTextFields(Container container) {
        return Stream.of(container.getComponents()).filter(x -> x instanceof JTextField).map(x -> (JTextField) x).collect(Collectors.toList());
    }
    public static List<String> getEmptyTextFields(List<JTextField> fields) {
        return fields.stream().filter(x -> x.getText().equals("")).map(Component::getName).collect(Collectors.toList());
    }

    public static void resizeColumnWidth(JTable table) {
        for (int column = 0; column < table.getColumnCount(); column++)
        {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getPreferredWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < table.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth(preferredWidth);
        }
    }
}
