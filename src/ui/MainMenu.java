package ui;

import exceptions.DataAccessException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private JFrame frame;
    private JTextField userNameField;
    private final List<String> validPassword = new ArrayList<>();
    private final List<String> validUsername = new ArrayList<>();
    private final MainMenu mm;

    // private static final String[] validUsername = {
    // "jozo", "jure", "boban"
    // };
    private JPasswordField passwordField;
    private Object key;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        setUIFont(new javax.swing.plaf.FontUIResource("Tahoma",Font.PLAIN,11));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
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
        frame = new JFrame();
        frame.setBounds(100, 100, 222, 232);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Main Menu");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setForeground(SystemColor.desktop);
        lblNewLabel.setBounds(10, 10, 146, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setBounds(10, 35, 146, 14);
        lblNewLabel_1.setForeground(SystemColor.desktop);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btnNewButton_1 = new JButton("Customer Menu");
        btnNewButton_1.setBounds(10, 155, 186, 23);
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CustomerLoginMenu cm = null;
                try {
                    cm = new CustomerLoginMenu();
                } catch (DataAccessException dataAccessException) {
                    dataAccessException.printStackTrace();
                }
                cm.showWindow();
            }
        });
        frame.getContentPane().add(btnNewButton_1);

        userNameField = new JTextField();
        userNameField.setToolTipText("Type in your Username!");
        userNameField.setBounds(10, 48, 186, 20);
        frame.getContentPane().add(userNameField);
        userNameField.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setBounds(10, 73, 146, 18);
        lblNewLabel_1_1.setForeground(SystemColor.desktop);
        frame.getContentPane().add(lblNewLabel_1_1);

        JButton btnNewButton = new JButton("Login to Employee Menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBackground(UIManager.getColor("Button.background"));
        btnNewButton.setBounds(10, 121, 186, 23);
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = userNameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if (validPassword.contains(password) && validUsername.contains(username)) {
                    EmployeeMenu em = new EmployeeMenu(validPassword, mm, validUsername);
                    em.showWindow();
                    frame.setVisible(false);
                } else
                    System.out.println("Wrong username/password! " + username + password + " ");
            }
        });

        passwordField = new JPasswordField();
        passwordField.setToolTipText("Type in your Password!");
        passwordField.setBounds(10, 90, 186, 20);
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
