package ui;

import models.Customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerMenu {

	private JFrame frame;
	private final Customer customer;

	/**
	 * Create the application.
	 */
	public CustomerMenu(Customer c) {
		this.customer = c;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 239, 171);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("<html>Welcome,<br>" + customer.getName() + "!");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWelcome.setBounds(10, 11, 165, 47);
		frame.getContentPane().add(lblWelcome);
		
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
		frame.getContentPane().add(btnUpdateData);
		
		JButton btnCreateNewOrder = new JButton("Create New Order");
		btnCreateNewOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateOrderCustomerMenu cocm = new CreateOrderCustomerMenu(customer);
				cocm.showWindow();
				closeWindow();
			}
		});
		btnCreateNewOrder.setBounds(10, 103, 203, 23);
		frame.getContentPane().add(btnCreateNewOrder);
	}

	private void closeWindow() {
		EventQueue.invokeLater(() -> {
			try {
				frame.setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void showWindow() {
		EventQueue.invokeLater(() -> {
			try {
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
