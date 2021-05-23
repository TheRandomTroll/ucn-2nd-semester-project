package ui;

import controllers.CustomerController;
import exceptions.DataAccessException;
import models.Address;
import models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerLoginMenu {

    private JFrame frmCustomerMenu;
    private JTextField textFieldLoginPhoneNumber;
    private JTextField textFieldRegisterName;
    private JTextField textFieldRegisterPhoneNumber;
    private JTextField textFieldRegisterStreet;
    private JTextField textFieldRegisterStreetNumber;
    private JTextField textFieldRegisterFloor;
    private JTextField textFieldRegisterCity;
    private JTextField textFieldRegisterPostalCode;

    private final CustomerController customerController;
    private JTextField textFieldRegisterEmail;

    /**
     * Create the application.
     */
    public CustomerLoginMenu() throws DataAccessException {
        this.customerController = new CustomerController();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmCustomerMenu = new JFrame();
        frmCustomerMenu.setTitle("Customer Menu");
        frmCustomerMenu.setBounds(100, 100, 380, 265);
        frmCustomerMenu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frmCustomerMenu.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 434, 261);
        frmCustomerMenu.getContentPane().add(tabbedPane);

        JPanel panelLogin = new JPanel();
        tabbedPane.addTab("Login", null, panelLogin, null);
        panelLogin.setLayout(null);

        JLabel lblNewLabel = new JLabel("Already a user?");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(10, 11, 121, 27);
        panelLogin.add(lblNewLabel);

        textFieldLoginPhoneNumber = new JTextField();
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
                    JOptionPane.showMessageDialog(frmCustomerMenu,
                        "An error has occurred while connecting to the database. Message: " + dataAccessException.getMessage(),
                        "Error connecting to database",
                        JOptionPane.ERROR_MESSAGE);
                }

                if(c == null) {
                    JOptionPane.showMessageDialog(frmCustomerMenu,
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

        textFieldRegisterName = new JTextField();
        textFieldRegisterName.setBounds(10, 80, 86, 20);
        panelRegister.add(textFieldRegisterName);
        textFieldRegisterName.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Name");
        lblNewLabel_2.setBounds(10, 66, 46, 14);
        panelRegister.add(lblNewLabel_2);

        textFieldRegisterPhoneNumber = new JTextField();
        textFieldRegisterPhoneNumber.setColumns(10);
        textFieldRegisterPhoneNumber.setBounds(10, 125, 86, 20);
        panelRegister.add(textFieldRegisterPhoneNumber);

        JLabel lblNewLabel_2_1 = new JLabel("Phone Number");
        lblNewLabel_2_1.setBounds(10, 111, 86, 14);
        panelRegister.add(lblNewLabel_2_1);

        textFieldRegisterStreet = new JTextField();
        textFieldRegisterStreet.setColumns(10);
        textFieldRegisterStreet.setBounds(156, 80, 86, 20);
        panelRegister.add(textFieldRegisterStreet);

        JLabel lblNewLabel_2_2 = new JLabel("Street");
        lblNewLabel_2_2.setBounds(156, 66, 46, 14);
        panelRegister.add(lblNewLabel_2_2);

        textFieldRegisterStreetNumber = new JTextField();
        textFieldRegisterStreetNumber.setColumns(10);
        textFieldRegisterStreetNumber.setBounds(252, 80, 86, 20);
        panelRegister.add(textFieldRegisterStreetNumber);

        JLabel lblNewLabel_2_2_1 = new JLabel("Street Number");
        lblNewLabel_2_2_1.setBounds(252, 66, 86, 14);
        panelRegister.add(lblNewLabel_2_2_1);

        textFieldRegisterFloor = new JTextField();
        textFieldRegisterFloor.setColumns(10);
        textFieldRegisterFloor.setBounds(156, 125, 86, 20);
        panelRegister.add(textFieldRegisterFloor);

        JLabel lblNewLabel_2_2_1_1 = new JLabel("Floor");
        lblNewLabel_2_2_1_1.setBounds(156, 111, 46, 14);
        panelRegister.add(lblNewLabel_2_2_1_1);

        textFieldRegisterCity = new JTextField();
        textFieldRegisterCity.setColumns(10);
        textFieldRegisterCity.setBounds(252, 125, 86, 20);
        panelRegister.add(textFieldRegisterCity);

        JLabel lblNewLabel_2_2_1_2 = new JLabel("City");
        lblNewLabel_2_2_1_2.setBounds(252, 111, 46, 14);
        panelRegister.add(lblNewLabel_2_2_1_2);

        textFieldRegisterPostalCode = new JTextField();
        textFieldRegisterPostalCode.setColumns(10);
        textFieldRegisterPostalCode.setBounds(156, 170, 86, 20);
        panelRegister.add(textFieldRegisterPostalCode);

        JLabel lblNewLabel_2_2_1_3 = new JLabel("Postal Code");
        lblNewLabel_2_2_1_3.setBounds(156, 156, 86, 14);
        panelRegister.add(lblNewLabel_2_2_1_3);

        JButton btnRegister = new JButton("Register");
        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
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

                    int rows = customerController.createCustomer(name, phoneNumber, email, a);
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
        
        JLabel lblPersonalInfo = new JLabel("Personal Info");
        lblPersonalInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPersonalInfo.setBounds(10, 42, 92, 20);
        panelRegister.add(lblPersonalInfo);
        
        JLabel lblAddressInfo = new JLabel("Address Info");
        lblAddressInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAddressInfo.setBounds(156, 42, 92, 20);
        panelRegister.add(lblAddressInfo);
        
        textFieldRegisterEmail = new JTextField();
        textFieldRegisterEmail.setColumns(10);
        textFieldRegisterEmail.setBounds(10, 170, 86, 20);
        panelRegister.add(textFieldRegisterEmail);
        
        JLabel lblNewLabel_2_3 = new JLabel("Email");
        lblNewLabel_2_3.setBounds(10, 156, 46, 14);
        panelRegister.add(lblNewLabel_2_3);
    }



    private void closeWindow() {
        EventQueue.invokeLater(() -> {
            try {
                frmCustomerMenu.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void showWindow() {
        EventQueue.invokeLater(() -> {
            try {
                frmCustomerMenu.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
