package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import exceptions.DataAccessException;
import java.awt.Toolkit;

public class MainMenu {
	private JFrame frmBiobio;
	private final String validPassword = "admin";
    private final String validUsername = "admin";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		setUIFont(new javax.swing.plaf.FontUIResource("Tahoma", Font.PLAIN,11));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
		EventQueue.invokeLater(() -> {
            try {
                MainMenu window = new MainMenu();
                window.frmBiobio.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBiobio = new JFrame();
		frmBiobio.setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/ui/img/icon.png")));
		frmBiobio.setTitle("Bio&Bio");
        frmBiobio.setBounds(100, 100, 222, 264);
        frmBiobio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmBiobio.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Main Menu");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setForeground(SystemColor.desktop);
        lblNewLabel.setBounds(10, 10, 146, 14);
        frmBiobio.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setBounds(10, 35, 146, 14);
        lblNewLabel_1.setForeground(SystemColor.desktop);
        frmBiobio.getContentPane().add(lblNewLabel_1);

        JButton btnNewButton_1 = new JButton("Customer Menu");
        btnNewButton_1.setBounds(10, 155, 186, 23);
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CustomerLoginMenu cm = null;
                try {
                    cm = new CustomerLoginMenu(frmBiobio);
                } catch (DataAccessException dataAccessException) {
                    dataAccessException.printStackTrace();
                }
                assert cm != null;
                cm.showWindow();
            }
        });
        frmBiobio.getContentPane().add(btnNewButton_1);

        JTextField userNameField = new JTextField();
        userNameField.setBounds(10, 48, 186, 20);
        frmBiobio.getContentPane().add(userNameField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(10, 90, 186, 20);
        frmBiobio.getContentPane().add(passwordField);

        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setBounds(10, 73, 146, 18);
        lblNewLabel_1_1.setForeground(SystemColor.desktop);
        frmBiobio.getContentPane().add(lblNewLabel_1_1);

        JButton btnNewButton = new JButton("Login to Employee Menu");
        btnNewButton.addActionListener(e -> {
        });
        btnNewButton.setBackground(UIManager.getColor("Button.background"));
        btnNewButton.setBounds(10, 121, 186, 23);
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = userNameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if (validPassword.equals(password) && validUsername.equals(username)) {
                    new EmployeeMenu();
                } else {
                    System.out.println("Wrong username/password! " + username + password + " ");
                }
            }
        });
        frmBiobio.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1_1 = new JButton("Open DB Status");
        btnNewButton_1_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	    DBConnectivityUI dbcui = new DBConnectivityUI();
        	    dbcui.showWindow();
        	}
        });
        btnNewButton_1_1.setBounds(10, 189, 186, 23);
        frmBiobio.getContentPane().add(btnNewButton_1_1);
        frmBiobio.setVisible(true);
	}
	
	private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
}
