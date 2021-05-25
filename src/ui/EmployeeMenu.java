package ui;

import controllers.OrderController;
import controllers.ProductController;
import exceptions.DataAccessException;
import interfaces.Observer;
import models.Courier;
import models.Order;
import models.Product;
import models.enums.OrderStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.TableModel;

public class EmployeeMenu implements Observer<Product> {
    private ProductController productController;
    private OrderController orderController;

    private JFrame frmEmployeeMenu;
    private JTable tableOrders;
    private JTable tableProducts;
    private JTable tableCouriers;

    /**
     * Create the application.
     */
    public EmployeeMenu() {
        try {
            this.productController = new ProductController();
            this.orderController = new OrderController();
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

        BufferedImage addImage = null;
        BufferedImage dispatchImage = null;
        try {
            addImage = ImageIO.read(EmployeeMenu.class.getResource("/ui/img/add.png"));
            dispatchImage = ImageIO.read(EmployeeMenu.class.getResource("/ui/img/dispatch.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon addButtonIcon = new ImageIcon(fitImage(addImage, 15, 15));
        ImageIcon dispatchButtonIcon = new ImageIcon(fitImage(dispatchImage, 30, 30));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 561, 261);
        frmEmployeeMenu.getContentPane().add(tabbedPane);

        JPanel manageProductsPanel = new JPanel();
        tabbedPane.addTab("Manage Products", null, manageProductsPanel, null);
        manageProductsPanel.setLayout(null);

        List<Product> products = null;
        try {
            products = this.productController.getProducts();
        } catch (DataAccessException e) {
            UIUtil.displayDBErrorMsg(e.getMessage());
        }

        assert products != null;
        String[][] productRows = new String[products.size()][];
        for (int i = 0; i < products.size(); i++) {
            productRows[i] = products.get(i).toStringArray();
        }

        String[] productColumns = UIUtil.getClassFields(Product.class);

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
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());

                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem("Delete product...");
                    menuItem.addActionListener(e1 -> {
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Delete product?", dialogButton);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            int productId = Integer.parseInt(UIUtil.getTableRowValues(tableProducts.getModel(), productColumns.length, row)[0]);
                            try {
                                productController.deleteProduct(productId);
                                ((DefaultTableModel) tableProducts.getModel()).removeRow(row);
                                UIUtil.displayMessage("Successfully deleted product with id " + productId, "Success", JOptionPane.INFORMATION_MESSAGE);
                                tableProducts.revalidate();
                            } catch (DataAccessException dataAccessException) {
                                UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
                            }
                        }
                    });
                    popup.add(menuItem);
                    popup.show(manageProductsPanel, e.getX(), e.getY());
                }
            }
        });
        tableProducts.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                String[] updatedRow = UIUtil.getTableRowValues(tableProducts.getModel(), productColumns.length, row);

                int id = Integer.parseInt(updatedRow[0]);
                String name = updatedRow[1];
                int barcode = Integer.parseInt(updatedRow[2]);
                String description = updatedRow[3];
                float price = new BigDecimal(updatedRow[4]).floatValue();
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
        scrollPane.setSize(496, 200);
        manageProductsPanel.add(scrollPane);

        JButton btnNewProduct = new JButton(addButtonIcon);
        btnNewProduct.setToolTipText("Add new product");
        btnNewProduct.setBorder(BorderFactory.createEmptyBorder());
        btnNewProduct.setContentAreaFilled(false);
        btnNewProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CreateProductEmployeeMenu cpem = new CreateProductEmployeeMenu();
                cpem.addObserver(getEmployeeMenu());
                cpem.showWindow();
            }
        });
        btnNewProduct.setBounds(516, 11, 30, 30);
        manageProductsPanel.add(btnNewProduct);

        JPanel manageOrdersPanel = new JPanel();
        tabbedPane.addTab("Manage Orders", null, manageOrdersPanel, null);
        manageOrdersPanel.setLayout(null);

        String[] orderColumns = UIUtil.getClassFields(Order.class);
        String[][] orderRows = null;
        try {
            orderRows = this.orderController.getFinishedOrders().stream().map(Order::toStringArray).toArray(String[][]::new);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        tableOrders = new JTable(new DefaultTableModel(orderRows, Arrays.copyOf(orderColumns, orderColumns.length - 2)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tableOrders.setBounds(10, 15, 400, 200);
        tableOrders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        UIUtil.resizeColumnWidth(tableOrders);

        JScrollPane scrollPane2 = new JScrollPane(tableOrders);
        scrollPane2.setLocation(10, 11);
        scrollPane2.setSize(496, 200);
        manageOrdersPanel.add(scrollPane2);

        JButton btnDispatchOrder = new JButton(dispatchButtonIcon);
        btnDispatchOrder.setToolTipText("Dispatch selected order");
        btnDispatchOrder.setBorder(BorderFactory.createEmptyBorder());
        btnDispatchOrder.setContentAreaFilled(false);

        String[][] orderRowsCopy = orderRows;
        btnDispatchOrder.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	    int row = tableOrders.getSelectedRow();

        	    if(row < 0) {
        	        UIUtil.displayMessage("Make sure a row is selected.", "No row selected", JOptionPane.ERROR_MESSAGE);
                }

        	    int id = Integer.parseInt(tableOrders.getModel().getValueAt(row, 0).toString());
        	    String orderNumber = tableOrders.getModel().getValueAt(row, 1).toString();
                OrderStatus status = OrderStatus.valueOf(tableOrders.getModel().getValueAt(row, 4).toString());

                if(status != OrderStatus.ALLOCATED) {
                    UIUtil.displayMessage("<html>Orders must be <b>allocated</b> so that they can be dispatched.", "Cannot dispatch order", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Courier c = orderController.dispatchOrder(id);
                    orderController.updateOrderStatus(id, OrderStatus.SHIPPED);
                    UIUtil.displayMessage(c.getFirstName() + " " + c.getLastName() + " has been assigned to deliver order " + orderNumber, "Order dispatched", JOptionPane.INFORMATION_MESSAGE);

                    orderRowsCopy[row][4] = OrderStatus.SHIPPED.toString();
                    UIUtil.updateRow(tableOrders.getModel(), row, orderRowsCopy[row]);
                } catch (DataAccessException dataAccessException) {
                    UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
                }
            }
        });
        btnDispatchOrder.setBounds(516, 11, 30, 30);
        manageOrdersPanel.add(btnDispatchOrder);
        
        JPanel manageCouriersPanel = new JPanel();
        tabbedPane.addTab("Manage Couriers", null, manageCouriersPanel, null);
        manageCouriersPanel.setLayout(null);
        
        tableCouriers = new JTable(null);
        tableCouriers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableCouriers.setBounds(-171, 39, 525, 0);
        manageCouriersPanel.add(tableCouriers);
        
        JScrollPane scrollPane3 = new JScrollPane(tableCouriers);
        scrollPane3.setBounds(10, 11, 350, 200);
        manageCouriersPanel.add(scrollPane3);
        frmEmployeeMenu.setVisible(true);
    }

    private EmployeeMenu getEmployeeMenu() {
        return EmployeeMenu.this;
    }

    @Override
    public void notifyUpdate(Product item) {
        ((DefaultTableModel) tableProducts.getModel()).addRow(item.toStringArray());
        tableProducts.revalidate();
    }

    private Image fitImage(Image img , int w , int h)
    {
        BufferedImage resizedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0,w,h,null);
        g2.dispose();
        return resizedImage;
    }
}
