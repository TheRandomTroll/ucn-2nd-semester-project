package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class MainMenu {

	private JFrame frame;
	private JTextField userNameField;
	private static final String[] validPassword = {
			"25", "50", "1000"
	};
	private static final String[] validUsername = {
			"jozo", "jure", "boban"
	};
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.showWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void showWindow() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setBounds(180, 11, 107, 14);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Employee Menu");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = userNameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				if (Arrays.asList(validPassword).contains(password) && Arrays.asList(validUsername).contains(username)) {
					EmployeeMenu em = new EmployeeMenu();
					em.showWindow();
					frame.setVisible(false);
				}
				else System.out.println("Wrong username/password! " + username + password);
			}
		});
		btnNewButton.setBounds(265, 37, 129, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Customer Menu");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_1.setBounds(265, 72, 129, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		userNameField = new JTextField();
		userNameField.setBounds(85, 38, 86, 20);
		frame.getContentPane().add(userNameField);
		userNameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setBounds(10, 41, 65, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setBounds(10, 76, 65, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(85, 73, 86, 20);
		frame.getContentPane().add(passwordField);
		frame.setVisible(true);
	}
}
