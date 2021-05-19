package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.Insets;
import java.awt.Color;

public class MainMenu {

	private JFrame frame;
	private JTextField userNameField;
	private List<String> validPassword = new ArrayList<>();
	private List<String> validUsername = new ArrayList<>();
	private MainMenu mm;

	// private static final String[] validUsername = {
	// "jozo", "jure", "boban"
	// };
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		File fontFile = new File("AVGARDN.TTF");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12.0f);
			setUIFont(new javax.swing.plaf.FontUIResource(font));
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
		validPassword.add("25");
		validUsername.add("jozo");
		validUsername.add("jure");
		validUsername.add("boban");
		mm = this;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void showWindow() {
		Color customColor = new Color(98, 167, 15);
		frame = new JFrame();
		frame.getContentPane().setBackground(customColor);
		frame.setBounds(100, 100, 561, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
												frame.getContentPane().setLayout(null);
										
												JLabel lblNewLabel = new JLabel("Main Menu");
												lblNewLabel.setBounds(151, 0, 109, 14);
												frame.getContentPane().add(lblNewLabel);
								
										JLabel lblNewLabel_1 = new JLabel("Username");
										lblNewLabel_1.setBounds(10, 35, 146, 14);
										lblNewLabel_1.setForeground(Color.WHITE);
										frame.getContentPane().add(lblNewLabel_1);
						
								JButton btnNewButton_1 = new JButton("Customer Menu");
								btnNewButton_1.setBounds(326, 329, 209, 23);
								btnNewButton_1.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
									}
								});
								frame.getContentPane().add(btnNewButton_1);
						
								userNameField = new JTextField();
								userNameField.setBounds(10, 48, 146, 20);
								frame.getContentPane().add(userNameField);
								userNameField.setColumns(10);
				
						JLabel lblNewLabel_1_1 = new JLabel("Password");
						lblNewLabel_1_1.setBounds(10, 73, 146, 18);
						lblNewLabel_1_1.setForeground(Color.WHITE);
						frame.getContentPane().add(lblNewLabel_1_1);
								
										JButton btnNewButton = new JButton("Log in");
										btnNewButton.setBackground(Color.ORANGE);
										btnNewButton.setBounds(10, 121, 146, 23);
										btnNewButton.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent e) {
												String username = userNameField.getText();
												String password = String.valueOf(passwordField.getPassword());
												// String[] passwords = validPassword.toArray(new String[validPassword.size()]);
												// System.out.println(validPassword.toString() + passwords);

												if (validPassword.contains(password) && validUsername.contains(username)) {
													EmployeeMenu em = new EmployeeMenu(validPassword, mm, validUsername);
													em.showWindow();
													frame.setVisible(false);
													// (Arrays.asList(validPassword).contains(password) &&
													// Arrays.asList(validUsername).contains(username))
												} else
													System.out.println("Wrong username/password! " + username + password + " ");
											}
										});
										
												passwordField = new JPasswordField();
												passwordField.setBounds(10, 90, 146, 20);
												frame.getContentPane().add(passwordField);
										frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}

	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put(key, f);
		}
	}
}
