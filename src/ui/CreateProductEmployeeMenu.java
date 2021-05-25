package ui;

import controllers.ProductController;
import exceptions.DataAccessException;
import interfaces.Observable;
import interfaces.Observer;
import models.Product;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Toolkit;

public class CreateProductEmployeeMenu implements Observable<Product> {
	private JDialog dialogCreateProduct;

	private ProductController productController;
	private Observer<Product> observer;

	/**
	 * Create the application.
	 */
	public CreateProductEmployeeMenu() {
		try {
			this.productController = new ProductController();
		} catch (DataAccessException e) {
			UIUtil.displayDBErrorMsg(e.getMessage());
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dialogCreateProduct = new JDialog();
		dialogCreateProduct.setIconImage(Toolkit.getDefaultToolkit().getImage(CreateProductEmployeeMenu.class.getResource("/ui/img/icon.png")));
		dialogCreateProduct.setTitle("Create Product");
		dialogCreateProduct.setBounds(100, 100, 226, 295);
		dialogCreateProduct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogCreateProduct.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Product");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 122, 14);
		dialogCreateProduct.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 36, 86, 14);
		dialogCreateProduct.getContentPane().add(lblNewLabel_1);

		JTextField textName = new JTextField();
		textName.setName("Name");
		textName.setBounds(10, 50, 86, 20);
		dialogCreateProduct.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Barcode");
		lblNewLabel_1_1.setBounds(10, 81, 86, 14);
		dialogCreateProduct.getContentPane().add(lblNewLabel_1_1);

		JTextField textBarcode = new JTextField();
		textBarcode.setName("Barcode");
		textBarcode.setColumns(10);
		textBarcode.setBounds(10, 95, 86, 20);
		dialogCreateProduct.getContentPane().add(textBarcode);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setBounds(10, 126, 86, 14);
		dialogCreateProduct.getContentPane().add(lblNewLabel_1_2);

		JTextField textPrice = new JTextField();
		textPrice.setName("Price");
		textPrice.setColumns(10);
		textPrice.setBounds(10, 140, 86, 20);
		dialogCreateProduct.getContentPane().add(textPrice);
		
		JLabel lblNewLabel_1_3 = new JLabel("Description");
		lblNewLabel_1_3.setBounds(106, 36, 86, 14);
		dialogCreateProduct.getContentPane().add(lblNewLabel_1_3);
		
		JTextArea textDescription = new JTextArea();
		textDescription.setBounds(106, 50, 86, 110);
		dialogCreateProduct.getContentPane().add(textDescription);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Minimal Stock");
		lblNewLabel_1_2_1.setBounds(10, 171, 86, 14);
		dialogCreateProduct.getContentPane().add(lblNewLabel_1_2_1);

		JTextField textMinStock = new JTextField();
		textMinStock.setName("Minimal Stock");
		textMinStock.setColumns(10);
		textMinStock.setBounds(10, 185, 86, 20);
		dialogCreateProduct.getContentPane().add(textMinStock);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Maximal Stock");
		lblNewLabel_1_2_3.setBounds(106, 171, 86, 14);
		dialogCreateProduct.getContentPane().add(lblNewLabel_1_2_3);

		JTextField textMaxStock = new JTextField();
		textMaxStock.setName("Maximal Stock");
		textMaxStock.setColumns(10);
		textMaxStock.setBounds(106, 185, 86, 20);
		dialogCreateProduct.getContentPane().add(textMaxStock);
		
		JButton btnNewButton = new JButton("Create Product");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<String> emptyFields = UIUtil.getEmptyTextFields(UIUtil.getTextFields(dialogCreateProduct.getContentPane()));
				if(emptyFields.size() > 0) {
					StringBuilder errorMessage = new StringBuilder("<html>Cannot insert new product. The following fields are empty:<br><ul>");
					for(String field : emptyFields) {
						errorMessage.append("<li>").append(field).append("</li>");
					}

					errorMessage.append("</ul>");

					UIUtil.displayMessage(errorMessage.toString(), "Error creating product", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					String name = textName.getText();
					int barcode = Integer.parseInt(textBarcode.getText());
					String description = textDescription.getText();
					float price = Float.parseFloat(textPrice.getText());
					int maxStock = Integer.parseInt(textMaxStock.getText());
					int minStock = Integer.parseInt(textMinStock.getText());

					Product p = new Product(name, barcode, description, price, maxStock, minStock, maxStock);
					productController.addProduct(p);
					observer.notifyUpdate(p);
					UIUtil.displayMessage("Product with id " + p.getId() + " created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					closeWindow();
				} catch (DataAccessException dae) {
					UIUtil.displayDBErrorMsg(dae.getMessage());
				} catch (NumberFormatException nfe) {
					UIUtil.displayMessage("Error parsing input data. Please check that the values are valid.", "Error parsing data", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(10, 222, 182, 23);
		dialogCreateProduct.getContentPane().add(btnNewButton);
	}

	public void showWindow() {
		EventQueue.invokeLater(() -> {
			try {
				dialogCreateProduct.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void closeWindow() {
		EventQueue.invokeLater(() -> {
			try {
				dialogCreateProduct.setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void addObserver(Observer<Product> observer) {
		this.observer = observer;
	}
}
