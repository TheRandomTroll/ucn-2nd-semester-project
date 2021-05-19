package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class EmployeeMenu {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private List<String> validPassword;
	private List<String> validUsername;
	private MainMenu mm;

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
		//showWindow();
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
		
		JButton btnNewButton = new JButton("Main Menu");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				mm.showWindow();
			}
		});
		btnNewButton.setBounds(10, 227, 109, 23);
		frame.getContentPane().add(btnNewButton);
		
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
		frame.setVisible(true);
	}
}
