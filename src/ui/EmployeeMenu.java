package ui;

import controllers.CourierController;
import controllers.OrderController;
import controllers.ProductController;
import exceptions.DataAccessException;
import interfaces.Observer;
import models.Courier;
import models.Order;
import models.Product;
import models.enums.CourierStatus;
import models.enums.OrderStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EmployeeMenu {
    private class ProductObserver implements Observer<Product> {
        public void notifyUpdate(Product item) {
            ((DefaultTableModel) tableProducts.getModel()).addRow(item.toStringArray());
            tableProducts.revalidate();
        }

        public ProductObserver getThis() {
            return ProductObserver.this;
        }
    }

    private class CourierObserver implements Observer<Courier> {
        public void notifyUpdate(Courier item) {
            ((DefaultTableModel) tableCouriers.getModel()).addRow(item.toStringArray());
            tableCouriers.revalidate();
        }

        public CourierObserver getThis() {
            return CourierObserver.this;
        }
    }

    private ProductController productController;
    private OrderController orderController;
    private CourierController courierController;

    private JTable tableProducts;
    private JTable tableCouriers;

    /**
     * Create the application.
     */
    public EmployeeMenu() {

        try {
            this.productController = new ProductController();
            this.orderController = new OrderController();
            this.courierController = new CourierController();
        } catch (DataAccessException e) {
            UIUtil.displayDBErrorMsg(e.getMessage());
        }
        showWindow();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void showWindow() {
        JFrame frmEmployeeMenu = new JFrame();
        frmEmployeeMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(EmployeeMenu.class.getResource("/ui/img/icon.png")));
        frmEmployeeMenu.setTitle("Employee Menu");
        frmEmployeeMenu.setBounds(100, 100, 577, 309);
        frmEmployeeMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmEmployeeMenu.getContentPane().setLayout(null);

        BufferedImage addImage = null;
        BufferedImage dispatchImage = null;
        try {
            addImage = ImageIO.read(Objects.requireNonNull(EmployeeMenu.class.getResource("/ui/img/add.png")));
            dispatchImage = ImageIO.read(Objects.requireNonNull(EmployeeMenu.class.getResource("/ui/img/dispatch.png")));
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

        JScrollPane scrollPaneProducts = new JScrollPane(tableProducts);
        scrollPaneProducts.setLocation(10, 11);
        scrollPaneProducts.setSize(496, 200);
        manageProductsPanel.add(scrollPaneProducts);

        tableProducts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int horizontalScrollValue = scrollPaneProducts.getHorizontalScrollBar().getValue();
                    int verticalScrollValue = scrollPaneProducts.getVerticalScrollBar().getValue();
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
                    popup.show(manageProductsPanel, e.getX() - horizontalScrollValue, e.getY() - verticalScrollValue);
                }
            }
        });

        JButton btnNewProduct = new JButton(addButtonIcon);
        btnNewProduct.setToolTipText("Add new product");
        btnNewProduct.setBorder(BorderFactory.createEmptyBorder());
        btnNewProduct.setContentAreaFilled(false);
        btnNewProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CreateProductEmployeeMenu cpem = new CreateProductEmployeeMenu();
                cpem.addObserver(new ProductObserver().getThis());
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

        JTable tableOrders = new JTable(new DefaultTableModel(orderRows, Arrays.copyOf(orderColumns, orderColumns.length - 2)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tableOrders.setBounds(10, 15, 400, 200);
        tableOrders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        UIUtil.resizeColumnWidth(tableOrders);

        JScrollPane scrollPaneOrders = new JScrollPane(tableOrders);
        scrollPaneOrders.setLocation(10, 11);
        scrollPaneOrders.setSize(496, 200);
        manageOrdersPanel.add(scrollPaneOrders);

        JButton btnDispatchOrder = new JButton(dispatchButtonIcon);
        btnDispatchOrder.setToolTipText("Dispatch selected order");
        btnDispatchOrder.setBorder(BorderFactory.createEmptyBorder());
        btnDispatchOrder.setContentAreaFilled(false);

        String[][] orderRowsCopy = orderRows;

        btnDispatchOrder.setBounds(516, 11, 30, 30);
        manageOrdersPanel.add(btnDispatchOrder);
        
        JPanel manageCouriersPanel = new JPanel();
        tabbedPane.addTab("Manage Couriers", null, manageCouriersPanel, null);
        manageCouriersPanel.setLayout(null);

        String[] couriersColumns = UIUtil.getClassFields(Courier.class);
        String[][] couriersRows = null;
        try {
            couriersRows = this.courierController.getCouriers().stream().map(Courier::toStringArray).toArray(String[][]::new);
        } catch (DataAccessException e) {
            UIUtil.displayDBErrorMsg(e.getMessage());
        }

        tableCouriers = new JTable(new DefaultTableModel(couriersRows, couriersColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        });

        TableColumn statusColumn = tableCouriers.getColumnModel().getColumn(4);

        JComboBox<String> editCourierStatusComboBox = new JComboBox<>();
        for(CourierStatus status : CourierStatus.values()) {
            editCourierStatusComboBox.addItem(status.toString());
        }

        statusColumn.setCellEditor(new DefaultCellEditor(editCourierStatusComboBox));
        tableCouriers.setLocation(10, 15);
        tableCouriers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        UIUtil.resizeColumnWidth(tableCouriers);

        tableCouriers.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                String[] updatedRow = UIUtil.getTableRowValues(tableCouriers.getModel(), couriersColumns.length, row);

                int id = Integer.parseInt(updatedRow[0]);
                String firstName = updatedRow[1];
                String lastName = updatedRow[2];
                String phoneNumber = updatedRow[3];
                CourierStatus status = CourierStatus.valueOf(updatedRow[4]);
                Courier c = new Courier(id, firstName, lastName, phoneNumber, status);

                try {
                    courierController.updateCourier(c);
                    UIUtil.displayMessage("Successfully updated courier with id " + id, "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (DataAccessException dataAccessException) {
                    UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
                }
            }
        });
        
        JScrollPane scrollPaneCouriers = new JScrollPane(tableCouriers);
        scrollPaneCouriers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneCouriers.setBounds(10, 11, 400, 200);
        manageCouriersPanel.add(scrollPaneCouriers);

        tableCouriers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int verticalScrollValue = scrollPaneCouriers.getVerticalScrollBar().getValue() - 10;
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());

                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem("Delete courier...");
                    menuItem.addActionListener(e1 -> {
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this courier?", "Delete courier?", dialogButton);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            int courierId = Integer.parseInt(UIUtil.getTableRowValues(tableCouriers.getModel(), couriersColumns.length, row)[0]);
                            try {
                                courierController.deleteCourier(courierId);
                                ((DefaultTableModel) tableCouriers.getModel()).removeRow(row);
                                UIUtil.displayMessage("Successfully deleted courier with id " + courierId, "Success", JOptionPane.INFORMATION_MESSAGE);
                                tableCouriers.revalidate();
                            } catch (DataAccessException dataAccessException) {
                                UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
                            }
                        }
                    });
                    popup.add(menuItem);
                    popup.show(manageCouriersPanel, e.getX(), e.getY() - verticalScrollValue);
                }
            }
        });

        btnDispatchOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableOrders.getSelectedRow();

                if(row < 0) {
                    UIUtil.displayMessage("Make sure a row is selected.", "No row selected", JOptionPane.ERROR_MESSAGE);
                    return;
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

        JButton btnNewCourier = new JButton(addButtonIcon);
        btnNewCourier.setToolTipText("Add new courier");
        btnNewCourier.setBorder(BorderFactory.createEmptyBorder());
        btnNewCourier.setContentAreaFilled(false);
        btnNewCourier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CreateCourierEmployeeMenu ccem = new CreateCourierEmployeeMenu();
                ccem.addObserver(new CourierObserver().getThis());
                ccem.showWindow();
            }
        });
        btnNewCourier.setBounds(516, 11, 30, 30);
        manageCouriersPanel.add(btnNewCourier);

        frmEmployeeMenu.setVisible(true);
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
