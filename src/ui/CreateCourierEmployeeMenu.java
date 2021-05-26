package ui;

import controllers.CourierController;
import exceptions.DataAccessException;
import interfaces.Observable;
import interfaces.Observer;
import models.Courier;
import models.enums.CourierStatus;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CreateCourierEmployeeMenu implements Observable<Courier> {
	private JDialog dialogCreateCourier;

	private CourierController courierController;
	private Observer<Courier> observer;

	public CreateCourierEmployeeMenu() {
		try {
			this.courierController = new CourierController();
		} catch (DataAccessException e) {
			UIUtil.displayDBErrorMsg(e.getMessage());
		}
		initialize();
	}

	private void initialize() {
		dialogCreateCourier = new JDialog();
		dialogCreateCourier.setBounds(100, 100, 217, 194);
		dialogCreateCourier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogCreateCourier.getContentPane().setLayout(null);

		JLabel lblCreateCourier = new JLabel("Create Courier");
		lblCreateCourier.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCreateCourier.setBounds(10, 11, 122, 14);
		dialogCreateCourier.getContentPane().add(lblCreateCourier);

		JLabel lblNewLabel_1_2_3 = new JLabel("Last Name");
		lblNewLabel_1_2_3.setBounds(106, 36, 86, 14);
		dialogCreateCourier.getContentPane().add(lblNewLabel_1_2_3);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Phone Number");
		lblNewLabel_1_2_1.setBounds(10, 81, 86, 14);
		dialogCreateCourier.getContentPane().add(lblNewLabel_1_2_1);

		JTextField textFieldFirstName = new JTextField();
		textFieldFirstName.setName("First Name");
		textFieldFirstName.setColumns(10);
		textFieldFirstName.setBounds(10, 50, 86, 20);
		dialogCreateCourier.getContentPane().add(textFieldFirstName);

		JTextField textFieldLastName = new JTextField();
		textFieldLastName.setName("Last Name");
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(106, 50, 86, 20);
		dialogCreateCourier.getContentPane().add(textFieldLastName);

		JTextField textFieldPhoneNumber = new JTextField();
		textFieldPhoneNumber.setName("Phone Number");
		textFieldPhoneNumber.setColumns(10);
		textFieldPhoneNumber.setBounds(10, 95, 86, 20);
		dialogCreateCourier.getContentPane().add(textFieldPhoneNumber);

		JLabel lblNewLabel_1_2 = new JLabel("First Name");
		lblNewLabel_1_2.setBounds(10, 36, 86, 14);
		dialogCreateCourier.getContentPane().add(lblNewLabel_1_2);
		
		JButton btnCreateCourier = new JButton("Create Courier");
		btnCreateCourier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<String> emptyFields = UIUtil.getEmptyTextFields(UIUtil.getTextFields(dialogCreateCourier.getContentPane()));
				if(emptyFields.size() > 0) {
					StringBuilder errorMessage = new StringBuilder("<html>Cannot insert new courier. The following fields are empty:<br><ul>");
					for(String field : emptyFields) {
						errorMessage.append("<li>").append(field).append("</li>");
					}

					errorMessage.append("</ul>");

					UIUtil.displayMessage(errorMessage.toString(), "Error creating courier", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					String firstName = textFieldFirstName.getText();
					String lastName = textFieldLastName.getText();
					String phoneNumber = textFieldPhoneNumber.getText();

					Courier c = new Courier(firstName, lastName, phoneNumber, CourierStatus.AVAILABLE);
					courierController.addCourier(c);
					observer.notifyUpdate(c);
					UIUtil.displayMessage("Courier with id " + c.getId() + " created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					closeWindow();
				} catch (DataAccessException dae) {
					UIUtil.displayDBErrorMsg(dae.getMessage());
				}
			}
		});
		btnCreateCourier.setBounds(10, 126, 182, 23);
		dialogCreateCourier.getContentPane().add(btnCreateCourier);
	}

	public void showWindow() {
		EventQueue.invokeLater(() -> {
			try {
				dialogCreateCourier.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void closeWindow() {
		EventQueue.invokeLater(() -> {
			try {
				dialogCreateCourier.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void addObserver(Observer<Courier> observer) {
		this.observer = observer;
	}
}
