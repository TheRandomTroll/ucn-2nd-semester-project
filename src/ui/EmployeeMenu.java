package ui;

import controllers.AddressController;
import db.AddressDB;
import exceptions.DataAccessException;
import models.Address;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.border.BevelBorder;

public class EmployeeMenu {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private final List<String> validPassword;
	private final List<String> validUsername;
	private final MainMenu mm;
	private JTextField addressIDField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMenu window = new EmployeeMenu(null, null, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public EmployeeMenu(List<String> validPassword, MainMenu mm, List<String> validUsername) {
		showWindow();
		this.validPassword = validPassword;
		this.validUsername = validUsername;
		this.mm = mm;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void showWindow() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Menu");
		lblNewLabel.setBounds(187, 11, 91, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				mm.showWindow();
			}
		});
		btnMainMenu.setBounds(10, 227, 109, 23);
		frame.getContentPane().add(btnMainMenu);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				validUsername.add(username);
				validPassword.add(password);
			}
		});
		btnAddUser.setBounds(335, 36, 89, 23);
		frame.getContentPane().add(btnAddUser);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setBounds(10, 39, 65, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(85, 36, 86, 20);
		frame.getContentPane().add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(85, 71, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setBounds(10, 74, 65, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton btnPrintAddress = new JButton("Print Address");
		btnPrintAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					AddressController addressCont = new AddressController();
					int id = Integer.parseInt(addressIDField.getText());
					Address a = addressCont.findById(id);
					if (a == null) {
						JOptionPane.showMessageDialog(frame,
							    "Reenter the number!!!",
							    "Error in number format",
							    JOptionPane.WARNING_MESSAGE);
					}
					System.out.println(addressCont.findById(id));
				} catch (DataAccessException dataAccessException) {
					JOptionPane.showMessageDialog (null, "Error in number format: Reenter the number");
				}
			}
		});
		btnPrintAddress.setBounds(335, 70, 89, 23);
		frame.getContentPane().add(btnPrintAddress);
		
		addressIDField = new JTextField();
		addressIDField.setBounds(85, 102, 86, 20);
		frame.getContentPane().add(addressIDField);
		addressIDField.setColumns(10);
		
		JButton btnOrderMenu = new JButton("Order Menu");
		btnOrderMenu.setBounds(335, 104, 89, 23);
		frame.getContentPane().add(btnOrderMenu);
		frame.setVisible(true);
	}
}
