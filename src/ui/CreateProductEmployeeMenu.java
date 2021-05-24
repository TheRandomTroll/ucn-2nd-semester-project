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
import java.util.concurrent.Flow;

public class CreateProductEmployeeMenu implements Observable<Product> {
	private JFrame frmCreateProduct;
	private JTextField textName;
	private JTextField textBarcode;
	private JTextField textPrice;
	private JTextField textMinStock;
	private JTextField textMaxStock;

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
		frmCreateProduct = new JFrame();
		frmCreateProduct.setTitle("Create Product");
		frmCreateProduct.setBounds(100, 100, 226, 295);
		frmCreateProduct.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmCreateProduct.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Product");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 122, 14);
		frmCreateProduct.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 36, 86, 14);
		frmCreateProduct.getContentPane().add(lblNewLabel_1);
		
		textName = new JTextField();
		textName.setBounds(10, 50, 86, 20);
		frmCreateProduct.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Barcode");
		lblNewLabel_1_1.setBounds(10, 81, 86, 14);
		frmCreateProduct.getContentPane().add(lblNewLabel_1_1);
		
		textBarcode = new JTextField();
		textBarcode.setColumns(10);
		textBarcode.setBounds(10, 95, 86, 20);
		frmCreateProduct.getContentPane().add(textBarcode);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setBounds(10, 126, 86, 14);
		frmCreateProduct.getContentPane().add(lblNewLabel_1_2);
		
		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(10, 140, 86, 20);
		frmCreateProduct.getContentPane().add(textPrice);
		
		JLabel lblNewLabel_1_3 = new JLabel("Description");
		lblNewLabel_1_3.setBounds(106, 36, 86, 14);
		frmCreateProduct.getContentPane().add(lblNewLabel_1_3);
		
		JTextArea textDescription = new JTextArea();
		textDescription.setBounds(106, 50, 86, 110);
		frmCreateProduct.getContentPane().add(textDescription);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Minimal Stock");
		lblNewLabel_1_2_1.setBounds(10, 171, 86, 14);
		frmCreateProduct.getContentPane().add(lblNewLabel_1_2_1);
		
		textMinStock = new JTextField();
		textMinStock.setColumns(10);
		textMinStock.setBounds(10, 185, 86, 20);
		frmCreateProduct.getContentPane().add(textMinStock);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("Maximal Stock");
		lblNewLabel_1_2_3.setBounds(106, 171, 86, 14);
		frmCreateProduct.getContentPane().add(lblNewLabel_1_2_3);
		
		textMaxStock = new JTextField();
		textMaxStock.setColumns(10);
		textMaxStock.setBounds(106, 185, 86, 20);
		frmCreateProduct.getContentPane().add(textMaxStock);
		
		JButton btnNewButton = new JButton("Create Product");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = textName.getText();
				int barcode = Integer.parseInt(textBarcode.getText());
				String description = textDescription.getText();
				double price = Double.parseDouble(textPrice.getText());
				int maxStock = Integer.parseInt(textMaxStock.getText());
				int minStock = Integer.parseInt(textMinStock.getText());

				Product p = new Product(name, barcode, description, price, maxStock, minStock, maxStock);

				try {
					productController.addProduct(p);
					observer.notifyUpdate(p);
					UIUtil.displayMessage("Product with id " + p.getId() + " created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					closeWindow();
				} catch (DataAccessException ex) {
					UIUtil.displayDBErrorMsg(ex.getMessage());
				}
			}
		});
		btnNewButton.setBounds(10, 222, 182, 23);
		frmCreateProduct.getContentPane().add(btnNewButton);
	}

	public void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCreateProduct.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void closeWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCreateProduct.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void addObserver(Observer<Product> observer) {
		this.observer = observer;
	}
}
