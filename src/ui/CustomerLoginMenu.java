package ui;

import controllers.CustomerController;
import exceptions.DataAccessException;
import models.Address;
import models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomerLoginMenu {
    private final JDialog dialogCustomerMenu;
    private final CustomerController customerController;

    /**
     * Create the application.
     */
    public CustomerLoginMenu(JFrame frame) throws DataAccessException {
        this.customerController = new CustomerController();
        this.dialogCustomerMenu = new JDialog(frame);
        dialogCustomerMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerLoginMenu.class.getResource("/ui/img/icon.png")));
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        dialogCustomerMenu.setTitle("Customer Menu");
        dialogCustomerMenu.setBounds(100, 100, 380, 265);
        dialogCustomerMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogCustomerMenu.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 364, 226);
        dialogCustomerMenu.getContentPane().add(tabbedPane);

        JPanel panelLogin = new JPanel();
        tabbedPane.addTab("Login", null, panelLogin, null);
        panelLogin.setLayout(null);

        JLabel lblNewLabel = new JLabel("Already a user?");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(10, 11, 121, 27);
        panelLogin.add(lblNewLabel);

        JTextField textFieldLoginPhoneNumber = new JTextField();
        textFieldLoginPhoneNumber.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String phoneNumber = textFieldLoginPhoneNumber.getText();

                    Customer c = null;
                    try {
                        c = customerController.findByPhoneNo(phoneNumber);
                    } catch (DataAccessException dataAccessException) {
                        JOptionPane.showMessageDialog(dialogCustomerMenu,
                                "An error has occurred while connecting to the database. Message: " + dataAccessException.getMessage(),
                                "Error connecting to database",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    if (c == null) {
                        JOptionPane.showMessageDialog(dialogCustomerMenu,
                                "This phone number is invalid!",
                                "Invalid phone number",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        CustomerMenu cm = new CustomerMenu(c);
                        cm.showWindow();
                        closeWindow();
                    }
                }
            }
        });
        textFieldLoginPhoneNumber.setBounds(10, 49, 121, 20);
        panelLogin.add(textFieldLoginPhoneNumber);
        textFieldLoginPhoneNumber.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Phone Number");
        lblNewLabel_1.setBounds(10, 36, 121, 14);
        panelLogin.add(lblNewLabel_1);

        JButton btnLogin = new JButton("Login");
        btnLogin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String phoneNumber = textFieldLoginPhoneNumber.getText();

                Customer c = null;
                try {
                    c = customerController.findByPhoneNo(phoneNumber);
                } catch (DataAccessException dataAccessException) {
                    JOptionPane.showMessageDialog(dialogCustomerMenu,
                        "An error has occurred while connecting to the database. Message: " + dataAccessException.getMessage(),
                        "Error connecting to database",
                        JOptionPane.ERROR_MESSAGE);
                }

                if(c == null) {
                    JOptionPane.showMessageDialog(dialogCustomerMenu,
                            "This phone number is invalid!",
                            "Invalid phone number",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                	CustomerMenu cm = new CustomerMenu(c);
                	cm.showWindow();
                    closeWindow();
                }
        	}
        });
        btnLogin.setBounds(10, 80, 121, 23);
        panelLogin.add(btnLogin);

        JPanel panelRegister = new JPanel();
        tabbedPane.addTab("Register", null, panelRegister, null);
        panelRegister.setLayout(null);

        JLabel lblNewHere = new JLabel("New here?");
        lblNewHere.setBounds(10, 11, 75, 20);
        lblNewHere.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panelRegister.add(lblNewHere);

        JTextField textFieldRegisterName = new JTextField();
        textFieldRegisterName.setName("Name");
        textFieldRegisterName.setBounds(10, 80, 86, 20);
        panelRegister.add(textFieldRegisterName);
        textFieldRegisterName.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Name");
        lblNewLabel_2.setBounds(10, 66, 46, 14);
        panelRegister.add(lblNewLabel_2);

        JTextField textFieldRegisterPhoneNumber = new JTextField();
        textFieldRegisterPhoneNumber.setName("Phone Number");
        textFieldRegisterPhoneNumber.setColumns(10);
        textFieldRegisterPhoneNumber.setBounds(10, 125, 86, 20);
        panelRegister.add(textFieldRegisterPhoneNumber);

        JLabel lblNewLabel_2_1 = new JLabel("Phone Number");
        lblNewLabel_2_1.setBounds(10, 111, 86, 14);
        panelRegister.add(lblNewLabel_2_1);

        JTextField textFieldRegisterStreet = new JTextField();
        textFieldRegisterStreet.setName("Street Name");
        textFieldRegisterStreet.setColumns(10);
        textFieldRegisterStreet.setBounds(156, 80, 86, 20);
        panelRegister.add(textFieldRegisterStreet);

        JLabel lblNewLabel_2_2 = new JLabel("Street Name");
        lblNewLabel_2_2.setBounds(156, 66, 46, 14);
        panelRegister.add(lblNewLabel_2_2);

        JTextField textFieldRegisterStreetNumber = new JTextField();
        textFieldRegisterStreetNumber.setName("Street Number");
        textFieldRegisterStreetNumber.setColumns(10);
        textFieldRegisterStreetNumber.setBounds(252, 80, 86, 20);
        panelRegister.add(textFieldRegisterStreetNumber);

        JLabel lblNewLabel_2_2_1 = new JLabel("Street Number");
        lblNewLabel_2_2_1.setBounds(252, 66, 86, 14);
        panelRegister.add(lblNewLabel_2_2_1);

        JTextField textFieldRegisterFloor = new JTextField();
        textFieldRegisterFloor.setName("Floor");
        textFieldRegisterFloor.setColumns(10);
        textFieldRegisterFloor.setBounds(156, 125, 86, 20);
        panelRegister.add(textFieldRegisterFloor);

        JLabel lblNewLabel_2_2_1_1 = new JLabel("Floor");
        lblNewLabel_2_2_1_1.setBounds(156, 111, 46, 14);
        panelRegister.add(lblNewLabel_2_2_1_1);

        JTextField textFieldRegisterCity = new JTextField();
        textFieldRegisterCity.setName("City");
        textFieldRegisterCity.setColumns(10);
        textFieldRegisterCity.setBounds(252, 125, 86, 20);
        panelRegister.add(textFieldRegisterCity);

        JLabel lblNewLabel_2_2_1_2 = new JLabel("City");
        lblNewLabel_2_2_1_2.setBounds(252, 111, 46, 14);
        panelRegister.add(lblNewLabel_2_2_1_2);

        JTextField textFieldRegisterPostalCode = new JTextField();
        textFieldRegisterPostalCode.setName("Postal Code");
        textFieldRegisterPostalCode.setColumns(10);
        textFieldRegisterPostalCode.setBounds(156, 170, 86, 20);
        panelRegister.add(textFieldRegisterPostalCode);

        JLabel lblNewLabel_2_2_1_3 = new JLabel("Postal Code");
        lblNewLabel_2_2_1_3.setBounds(156, 156, 86, 14);
        panelRegister.add(lblNewLabel_2_2_1_3);

        JLabel lblPersonalInfo = new JLabel("Personal Info");
        lblPersonalInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPersonalInfo.setBounds(10, 42, 92, 20);
        panelRegister.add(lblPersonalInfo);
        
        JLabel lblAddressInfo = new JLabel("Address Info");
        lblAddressInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAddressInfo.setBounds(156, 42, 92, 20);
        panelRegister.add(lblAddressInfo);

        JTextField textFieldRegisterEmail = new JTextField();
        textFieldRegisterEmail.setName("Email");
        textFieldRegisterEmail.setColumns(10);
        textFieldRegisterEmail.setBounds(10, 170, 86, 20);
        panelRegister.add(textFieldRegisterEmail);
        
        JLabel lblNewLabel_2_3 = new JLabel("Email");
        lblNewLabel_2_3.setBounds(10, 156, 46, 14);
        panelRegister.add(lblNewLabel_2_3);

        JButton btnRegister = new JButton("Register");

        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    List<String> emptyFields = UIUtil.getEmptyTextFields(UIUtil.getTextFields(panelRegister));
                    if(emptyFields.size() > 0) {
                        StringBuilder errorMessage = new StringBuilder("<html>Cannot create new account. The following fields are empty:<br><ul>");
                        for(String field : emptyFields) {
                            errorMessage.append("<li>").append(field).append("</li>");
                        }

                        errorMessage.append("</ul>");

                        UIUtil.displayMessage(errorMessage.toString(), "Error creating account", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Address a = UIUtil.parseAddress(textFieldRegisterStreet, textFieldRegisterStreetNumber, textFieldRegisterFloor, textFieldRegisterCity, textFieldRegisterPostalCode);


                    Address lookup = customerController.findAddressByData(a);
                    if(lookup == null) {
                        customerController.createAddress(a);
                    } else {
                        a = lookup;
                    }

                    String name = textFieldRegisterName.getText();
                    String phoneNumber = textFieldRegisterPhoneNumber.getText();
                    String email = textFieldRegisterEmail.getText();

                    Customer c = new Customer(name, phoneNumber, email, a);

                    int rows = customerController.createCustomer(c);
                    if(rows > 0) {
                        UIUtil.displayMessage("Successfully registered as a new customer!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (DataAccessException dataAccessException) {
                    dataAccessException.printStackTrace();
                    UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
                }
            }
        });
        btnRegister.setBounds(252, 169, 86, 23);
        panelRegister.add(btnRegister);

    }



    private void closeWindow() {
        EventQueue.invokeLater(() -> {
            try {
                dialogCustomerMenu.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void showWindow() {
        EventQueue.invokeLater(() -> {
            try {
                dialogCustomerMenu.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
