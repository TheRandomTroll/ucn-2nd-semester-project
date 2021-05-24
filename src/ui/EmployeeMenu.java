package ui;

import controllers.ProductController;
import exceptions.DataAccessException;
import models.Product;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					JTable source = (JTable)e.getSource();
					int row = source.rowAtPoint(e.getPoint());

					JPopupMenu popup = new JPopupMenu();
					JMenuItem menuItem = new JMenuItem("Delete product...");
					menuItem.addActionListener(e1 -> {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Delete product?", dialogButton);
						if(dialogResult == JOptionPane.YES_OPTION) {
							int productId = Integer.parseInt(UIUtil.getTableRowValues(tableProducts.getModel(), productColumns.length, row)[0]);
							try {
								productController.deleteProduct(productId);
								((DefaultTableModel)tableProducts.getModel()).removeRow(row);
								UIUtil.displayMessage("Successfully deleted product with id " + productId, "Success", JOptionPane.INFORMATION_MESSAGE);
								tableProducts.revalidate();
							} catch (DataAccessException dataAccessException) {
								UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
							}
						}
					});
					popup.add(menuItem);
					popup.show(productsPanel, e.getX(), e.getY());
				}
			}
		});
		tableProducts.getModel().addTableModelListener(e -> {
			if(e.getType() == TableModelEvent.UPDATE) {
				int row = e.getFirstRow();
				String[] updatedRow = UIUtil.getTableRowValues(tableProducts.getModel(), productColumns.length, row);

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
