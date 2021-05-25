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

public class UpdateCustomerInfoMenu {

	private final Customer customer;
	private CustomerController customerController;
	private JDialog dialog;
	private JTextField textFieldUpdateName;
	private JTextField textFieldUpdateStreetName;
	private JTextField textFieldUpdateStreetNumber;
	private JTextField textFieldUpdatePhoneNumber;
	private JTextField textFieldUpdateFloor;
	private JTextField textFieldUpdateCity;
	private JTextField textFieldUpdateEmail;
	private JTextField textFieldUpdatePostalCode;

	/**
	 * Create the application.
	 */
	public UpdateCustomerInfoMenu(Customer c) {
		this.customer = c;
		try {
			this.customerController = new CustomerController();
		} catch (DataAccessException e) {
			UIUtil.displayDBErrorMsg(e.getMessage());
		}
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dialog = new JDialog();
		dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateCustomerInfoMenu.class.getResource("/ui/img/icon.png")));
		dialog.setTitle("Update Your Info");
		dialog.setBounds(100, 100, 365, 241);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.getContentPane().setLayout(null);
		
		JLabel lblUpdateYourInfo = new JLabel("Update Your Info");
		lblUpdateYourInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUpdateYourInfo.setBounds(10, 11, 238, 20);
		dialog.getContentPane().add(lblUpdateYourInfo);
		
		JLabel lblPersonalInfo = new JLabel("Personal Info");
		lblPersonalInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPersonalInfo.setBounds(10, 42, 92, 20);
		dialog.getContentPane().add(lblPersonalInfo);
		
		JLabel lblAddressInfo = new JLabel("Address Info");
		lblAddressInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddressInfo.setBounds(156, 42, 92, 20);
		dialog.getContentPane().add(lblAddressInfo);
		
		textFieldUpdateName = new JTextField();
		textFieldUpdateName.setName("Name");
		textFieldUpdateName.setColumns(10);
		textFieldUpdateName.setBounds(10, 80, 86, 20);
		textFieldUpdateName.setText(customer.getName());
		dialog.getContentPane().add(textFieldUpdateName);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(10, 66, 46, 14);
		dialog.getContentPane().add(lblNewLabel_2);
		
		textFieldUpdateStreetName = new JTextField();
		textFieldUpdateStreetName.setName("Street");
		textFieldUpdateStreetName.setColumns(10);
		textFieldUpdateStreetName.setBounds(156, 80, 86, 20);
		textFieldUpdateStreetName.setText(customer.getAddress().getStreetName());
		dialog.getContentPane().add(textFieldUpdateStreetName);
		
		JLabel lblNewLabel_2_2 = new JLabel("Street");
		lblNewLabel_2_2.setBounds(156, 66, 46, 14);
		dialog.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Street Number");
		lblNewLabel_2_2_1.setBounds(252, 66, 86, 14);
		dialog.getContentPane().add(lblNewLabel_2_2_1);
		
		textFieldUpdateStreetNumber = new JTextField();
		textFieldUpdateStreetNumber.setName("Street Number");
		textFieldUpdateStreetNumber.setColumns(10);
		textFieldUpdateStreetNumber.setBounds(252, 80, 86, 20);
		textFieldUpdateStreetNumber.setText(customer.getAddress().getStreetNumber());
		dialog.getContentPane().add(textFieldUpdateStreetNumber);
		
		textFieldUpdatePhoneNumber = new JTextField();
		textFieldUpdatePhoneNumber.setName("Phone Number");
		textFieldUpdatePhoneNumber.setColumns(10);
		textFieldUpdatePhoneNumber.setBounds(10, 125, 86, 20);
		textFieldUpdatePhoneNumber.setText(customer.getPhoneNumber());
		dialog.getContentPane().add(textFieldUpdatePhoneNumber);
		
		JLabel lblNewLabel_2_1 = new JLabel("Phone Number");
		lblNewLabel_2_1.setBounds(10, 111, 86, 14);
		dialog.getContentPane().add(lblNewLabel_2_1);
		
		textFieldUpdateFloor = new JTextField();
		textFieldUpdateFloor.setName("Floor");
		textFieldUpdateFloor.setColumns(10);
		textFieldUpdateFloor.setBounds(156, 125, 86, 20);
		textFieldUpdateFloor.setText(customer.getAddress().getFloor());
		dialog.getContentPane().add(textFieldUpdateFloor);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Floor");
		lblNewLabel_2_2_1_1.setBounds(156, 111, 46, 14);
		dialog.getContentPane().add(lblNewLabel_2_2_1_1);
		
		textFieldUpdateCity = new JTextField();
		textFieldUpdateCity.setName("City");
		textFieldUpdateCity.setColumns(10);
		textFieldUpdateCity.setBounds(252, 125, 86, 20);
		textFieldUpdateCity.setText(customer.getAddress().getCity());
		dialog.getContentPane().add(textFieldUpdateCity);
		
		JLabel lblNewLabel_2_2_1_2 = new JLabel("City");
		lblNewLabel_2_2_1_2.setBounds(252, 111, 46, 14);
		dialog.getContentPane().add(lblNewLabel_2_2_1_2);
		
		textFieldUpdateEmail = new JTextField();
		textFieldUpdateEmail.setName("Email");
		textFieldUpdateEmail.setColumns(10);
		textFieldUpdateEmail.setBounds(10, 170, 86, 20);
		textFieldUpdateEmail.setText(customer.getEmail());
		dialog.getContentPane().add(textFieldUpdateEmail);
		
		JLabel lblNewLabel_2_3 = new JLabel("Email");
		lblNewLabel_2_3.setBounds(10, 156, 46, 14);
		dialog.getContentPane().add(lblNewLabel_2_3);
		
		textFieldUpdatePostalCode = new JTextField();
		textFieldUpdatePostalCode.setName("Postal Code");
		textFieldUpdatePostalCode.setColumns(10);
		textFieldUpdatePostalCode.setBounds(156, 170, 86, 20);
		textFieldUpdatePostalCode.setText(customer.getAddress().getPostalCode());
		dialog.getContentPane().add(textFieldUpdatePostalCode);
		
		JLabel lblNewLabel_2_2_1_3 = new JLabel("Postal Code");
		lblNewLabel_2_2_1_3.setBounds(156, 156, 86, 14);
		dialog.getContentPane().add(lblNewLabel_2_2_1_3);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<String> emptyFields = UIUtil.getEmptyTextFields(UIUtil.getTextFields(dialog.getContentPane()));
				if(emptyFields.size() > 0) {
					StringBuilder errorMessage = new StringBuilder("<html>Cannot complete update. The following fields are empty:<br><ul>");
					for(String field : emptyFields) {
						errorMessage.append("<li>").append(field).append("</li>");
					}

					errorMessage.append("</ul>");

					UIUtil.displayMessage(errorMessage.toString(), "Error updating info", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Address a = UIUtil.parseAddress(textFieldUpdateStreetName, textFieldUpdateStreetNumber, textFieldUpdateFloor, textFieldUpdateCity, textFieldUpdatePostalCode);
				Address lookup;
				try {
					lookup = customerController.findAddressByData(a);
					if(lookup == null) {
						customerController.createAddress(a);
					} else {
						a = lookup;
					}

					String name = textFieldUpdateName.getText();
					String phoneNumber = textFieldUpdatePhoneNumber.getText();
					String email = textFieldUpdateEmail.getText();

					int rows = customerController.updateCustomer(name, phoneNumber, email, a);
					if(rows > 0) {
						UIUtil.displayMessage("Successfully updated your info! Please re-enter your phone number.", "Success", JOptionPane.INFORMATION_MESSAGE);
						closeWindow();
					}
				} catch (DataAccessException dataAccessException) {
					UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
				}
			}
		});
		btnUpdate.setBounds(252, 169, 86, 23);
		dialog.getContentPane().add(btnUpdate);
	}
	
	public void showWindow() {
		EventQueue.invokeLater(() -> {
			try {
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void closeWindow() {
		EventQueue.invokeLater(() -> {
			try {
				dialog.setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
