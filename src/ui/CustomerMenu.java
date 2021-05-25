package ui;

import models.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerMenu {
	private JFrame frmWelcome;
	private final Customer customer;

	/**
	 * Create the application.
	 */
	public CustomerMenu(Customer c) {
		this.customer = c;
		frmWelcome = new JFrame();
		frmWelcome.setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerMenu.class.getResource("/ui/img/icon.png")));
		frmWelcome.setTitle("Welcome!");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcome.setBounds(100, 100, 239, 171);
		frmWelcome.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmWelcome.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("<html>Welcome,<br>" + customer.getName() + "!");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWelcome.setBounds(10, 11, 165, 47);
		frmWelcome.getContentPane().add(lblWelcome);
		
		JButton btnUpdateData = new JButton("Update Personal Info");
		btnUpdateData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateCustomerInfoMenu ucim = new UpdateCustomerInfoMenu(customer);
				ucim.showWindow();
				closeWindow();
			}
		});
		btnUpdateData.setBounds(10, 69, 203, 23);
		frmWelcome.getContentPane().add(btnUpdateData);
		
		JButton btnCreateNewOrder = new JButton("Create New Order");
		btnCreateNewOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateOrderCustomerMenu cocm = new CreateOrderCustomerMenu(frmWelcome, customer);
				cocm.showWindow();
				closeWindow();
			}
		});
		btnCreateNewOrder.setBounds(10, 103, 203, 23);
		frmWelcome.getContentPane().add(btnCreateNewOrder);
	}

	private void closeWindow() {
		EventQueue.invokeLater(() -> {
			try {
				frmWelcome.setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void showWindow() {
		EventQueue.invokeLater(() -> {
			try {
				frmWelcome.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
