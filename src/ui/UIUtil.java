package ui;

import models.Address;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
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
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getPreferredWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows

                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth(preferredWidth);
        }
    }

    public static String[] getTableRowValues(TableModel model, int columns, int row) {
        String[] result = new String[columns];

        for (int i = 0; i < columns; i++) {
            result[i] = (String) model.getValueAt(row, i);
        }

        return result;
    }

    public static void updateRow(TableModel dtm, int row, String[] data) {
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.setValueAt(data[i], row, i);
        }
    }

    public static String[] getClassFields(Class c) {
        Field[] fields = c.getDeclaredFields();
        String[] names = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            names[i] = fields[i].getName();
        }

        return names;
    }
}
