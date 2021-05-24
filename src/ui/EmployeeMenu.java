package ui;

import controllers.AddressController;
import controllers.ProductController;
import exceptions.DataAccessException;
import models.Address;
import models.Product;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.awt.Font;
import java.util.stream.Collectors;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class EmployeeMenu {

	private JFrame frmEmployeeMenu;
	private JTable tableOrders;

	private ProductController productController;
	private JTable tableProducts;

	/**
	 * Create the application.
	 */
	public EmployeeMenu() {
		try {
			this.productController = new ProductController();
		} catch (DataAccessException e) {
			UIUtil.displayDBErrorMsg(e.getMessage());
		}
		showWindow();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void showWindow() {
		frmEmployeeMenu = new JFrame();
		frmEmployeeMenu.setTitle("Employee Menu");
		frmEmployeeMenu.setBounds(100, 100, 577, 309);
		frmEmployeeMenu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmEmployeeMenu.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 561, 261);
		frmEmployeeMenu.getContentPane().add(tabbedPane);
		
		JPanel productsPanel = new JPanel();
		tabbedPane.addTab("Manage Products", null, productsPanel, null);
		productsPanel.setLayout(null);

		List<Product> products = null;
		try {
			products = this.productController.getProducts();
		} catch (DataAccessException e) {
			UIUtil.displayDBErrorMsg(e.getMessage());
		}

		assert products != null;
		String[][] productRows = new String[products.size()][];
		for(int i = 0; i < products.size(); i++) {
			productRows[i] = products.get(i).toStringArray();
		}

		String[] productColumns = {"Id", "Name", "Barcode", "Description", "Price", "MaxStock", "MinStock", "Quantity"};
		
		tableProducts = new JTable(new DefaultTableModel(productRows, productColumns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		});
		TableModel productsModel = tableProducts.getModel();
		productsModel.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				String[] updatedRow = getRowAt(row);

				int id = Integer.parseInt(updatedRow[0]);
				String name = updatedRow[1];
				int barcode = Integer.parseInt(updatedRow[2]);
				String description = updatedRow[3];
				float price = Float.parseFloat(updatedRow[4]);
				int maxStock = Integer.parseInt(updatedRow[5]);
				int minStock = Integer.parseInt(updatedRow[6]);
				int quantity = Integer.parseInt(updatedRow[7]);
				Product p = new Product(id, name, barcode, description, price, maxStock, minStock, quantity);

				try {
					productController.updateProduct(p);
					UIUtil.displayMessage("Successfully updated product with id " + id, "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (DataAccessException dataAccessException) {
					UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
				}
			}

			public String[] getRowAt(int row) {
				String[] result = new String[productColumns.length];

				for (int i = 0; i < productColumns.length; i++) {
					result[i] = (String) productsModel.getValueAt(row, i);
				}

				return result;
			}
		});

		tableProducts.setLocation(10, 15);
		tableProducts.setSize(350, 200);
		tableProducts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		UIUtil.resizeColumnWidth(tableProducts);

		JScrollPane scrollPane = new JScrollPane(tableProducts);
		scrollPane.setLocation(10, 11);
		scrollPane.setSize(350, 200);
		productsPanel.add(scrollPane);
		
		JButton btnNewProduct = new JButton("Create new product");
		btnNewProduct.setBounds(370, 15, 176, 23);
		productsPanel.add(btnNewProduct);
		
		JPanel ordersPanel = new JPanel();
		tabbedPane.addTab("Manage Orders", null, ordersPanel, null);
		ordersPanel.setLayout(null);
		
		tableOrders = new JTable();
		tableOrders.setBounds(10, 15, 400, 200);
		ordersPanel.add(tableOrders);
		
		JButton btnDispatchOrder = new JButton("Dispatch Order");
		btnDispatchOrder.setBounds(420, 15, 126, 23);
		ordersPanel.add(btnDispatchOrder);
		frmEmployeeMenu.setVisible(true);
	}
}
