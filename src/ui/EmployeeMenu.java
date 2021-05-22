package ui;

import controllers.AddressController;
import controllers.ProductController;
import db.AddressDB;
import exceptions.DataAccessException;
import models.Address;
import models.Product;

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
	private List<String> validPassword;
	private List<String> validUsername;
	private MainMenu mm;
	private JTextField addressIDField;
	private JTextField barcodeField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
		frame.setBounds(100, 100, 532, 462);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Menu");
		lblNewLabel.setBounds(212, 11, 91, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				mm.showWindow();
			}
		});
		btnMainMenu.setBounds(10, 389, 109, 23);
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
		btnAddUser.setBounds(319, 35, 120, 23);
		frame.getContentPane().add(btnAddUser);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setBounds(10, 39, 65, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(119, 36, 86, 20);
		frame.getContentPane().add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(119, 67, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setBounds(10, 74, 65, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton btnPrintAddress = new JButton("Address Info");
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
					JOptionPane.showMessageDialog(frame, addressCont.findById(id));
					System.out.println(addressCont.findById(id));
				} catch (DataAccessException dataAccessException) {
					JOptionPane.showMessageDialog (null, "Error in number format: Reenter the number");
				}
			}
		});
		btnPrintAddress.setBounds(319, 70, 120, 23);
		frame.getContentPane().add(btnPrintAddress);
		
		addressIDField = new JTextField();
		addressIDField.setBounds(119, 102, 86, 20);
		frame.getContentPane().add(addressIDField);
		addressIDField.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Address ID:");
		lblNewLabel_1_1_1.setBounds(10, 108, 65, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JButton btnPrintAddress_1 = new JButton("Remove Address");
		btnPrintAddress_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					AddressController addressCont = new AddressController();
					int id = Integer.parseInt(addressIDField.getText());
					addressCont.removeById1(id);
					//JOptionPane.showMessageDialog(frame, addressCont.removeById1(id));
				} catch (DataAccessException dataAccessException) {
					JOptionPane.showMessageDialog (null, "Error in number format: Reenter the number");
				}
			}
		});
		btnPrintAddress_1.setBounds(319, 104, 120, 23);
		frame.getContentPane().add(btnPrintAddress_1);

		JButton btnProductInfo = new JButton("Product Info");
		btnProductInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				ProductController prodCont = new ProductController();
				int barcode = Integer.parseInt(barcodeField.getText());
				Product p = prodCont.findByBarcode(barcode);

				if (p == null) {
					JOptionPane.showMessageDialog(frame,
							"Reenter the barcode!!!",
							"Wrong Barcode",
							JOptionPane.WARNING_MESSAGE);
				}
				else JOptionPane.showMessageDialog(frame, prodCont.findByBarcode(barcode));
			}catch (DataAccessException dataAccessException) {
					JOptionPane.showMessageDialog(null, "Error in number format: Reenter the number");
				}
		}
		});
		btnProductInfo.setBounds(319, 138, 120, 23);
		frame.getContentPane().add(btnProductInfo);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Product Barcode:");
		lblNewLabel_1_1_1_1.setBounds(10, 142, 109, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);

		barcodeField = new JTextField();
		barcodeField.setColumns(10);
		barcodeField.setBounds(119, 136, 86, 20);
		frame.getContentPane().add(barcodeField);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(119, 167, 86, 20);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 198, 86, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 229, 86, 20);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(119, 260, 86, 20);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(119, 291, 86, 20);
		frame.getContentPane().add(textField_4);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Street:");
		lblNewLabel_1_1_1_1_1.setBounds(10, 170, 109, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Street Number:");
		lblNewLabel_1_1_1_1_2.setBounds(10, 201, 109, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_3 = new JLabel("Floor:");
		lblNewLabel_1_1_1_1_3.setBounds(10, 232, 109, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_3);
		
		JLabel lblNewLabel_1_1_1_1_4 = new JLabel("City:");
		lblNewLabel_1_1_1_1_4.setBounds(10, 263, 109, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_4);
		
		JLabel lblNewLabel_1_1_1_1_5 = new JLabel("Postal Code:");
		lblNewLabel_1_1_1_1_5.setBounds(10, 294, 109, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_5);
		frame.setVisible(true);
	}
}
