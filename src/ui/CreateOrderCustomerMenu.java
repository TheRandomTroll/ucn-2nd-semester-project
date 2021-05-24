package ui;

import controllers.OrderController;
import exceptions.DataAccessException;
import exceptions.InsufficientDataException;
import models.*;
import models.enums.PaymentType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class CreateOrderCustomerMenu {
    private static class MyListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            String labelText = (String) value;
            setText(labelText);

            return this;
        }

    }

    private JFrame frame;

    private OrderController orderController;
    private final Customer customer;
    private Order order;
    private JTextField textFieldBarcode;
    private JTextField textFieldQuantity;
    private JTextField textFieldNewAddressStreet;
    private JTextField textFieldNewAddressStreetNumber;
    private JTextField textFieldNewAddressFloor;
    private JTextField textFieldNewAddressCity;
    private JTextField textFieldNewAddressPostalCode;
    private JTextField textFieldVoucherCode;

    /**
     * Create the application.
     */
    public CreateOrderCustomerMenu(Customer c) {
        this.order = new Order(c);
        try {
            System.out.println("Order Created");
            this.orderController = new OrderController();
            orderController.createOrder(order);
        } catch (DataAccessException e) {
            UIUtil.displayDBErrorMsg(e.getMessage());
        }
        this.customer = c;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 658, 484);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Create New Order");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(10, 18, 197, 14);
        frame.getContentPane().add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(217, 35, 200, 330);
        frame.getContentPane().add(scrollPane);

        JLabel lblTotalPrice = new JLabel("<html>Total price:<br> 0,00 kn");
        lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTotalPrice.setBounds(427, 256, 205, 34);
        frame.getContentPane().add(lblTotalPrice);

        JList<String> list = new JList<>();
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int index = list.locationToIndex(e.getPoint());
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem("Delete from shopping cart");
                    menuItem.addActionListener(e1 -> {
                        order.removeOrderLine(index);
                        UIUtil.displayMessage("Product removed from cart.", "Product removed", JOptionPane.INFORMATION_MESSAGE);
                        updateList();
                        updateTotalPrice();
                    });
                    popup.add(menuItem);
                    popup.show(list, e.getX(), e.getY());
                }
            }

            private void updateTotalPrice() {
                if (order.getAppliedVoucher() == null) {
                    lblTotalPrice.setText("<html>Total price:<br>" + String.format("%.2f kn", order.getTotalPrice()));
                } else {
                    lblTotalPrice.setText("<html>Total price:<br><strike>" + String.format("%.2f kn</strike> %.2f kn", order.getTotalPrice(), order.getDiscountedPrice()));
                }
            }

            private void updateList() {
                DefaultListModel<String> listModel = new DefaultListModel<>();
                List<OrderLine> orderLineList = order.getOrderLines();
                for (int i = 0; i < orderLineList.size(); i++) {
                    listModel.add(i, orderLineList.get(i).toString());
                }

                list.setModel(listModel);
            }
        });
        list.setCellRenderer(new MyListCellRenderer());
        scrollPane.setViewportView(list);

        JLabel lblNewLabel_1 = new JLabel("Your products:");
        lblNewLabel_1.setBounds(217, 18, 200, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_3 = new JLabel("Barcode");
        lblNewLabel_3.setBounds(217, 371, 82, 14);
        frame.getContentPane().add(lblNewLabel_3);

        textFieldBarcode = new JTextField();
        textFieldBarcode.setBounds(217, 385, 86, 20);
        frame.getContentPane().add(textFieldBarcode);
        textFieldBarcode.setColumns(10);

        textFieldQuantity = new JTextField();
        textFieldQuantity.setColumns(10);
        textFieldQuantity.setBounds(331, 385, 86, 20);
        frame.getContentPane().add(textFieldQuantity);

        JLabel lblOrderNumber = new JLabel();
        lblOrderNumber.setText("<html>Order No.<br>" + order.getOrderNumber());
        lblOrderNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOrderNumber.setBounds(427, 17, 133, 41);
        frame.getContentPane().add(lblOrderNumber);

        JLabel lblNewLabel_3_1 = new JLabel("Quantity");
        lblNewLabel_3_1.setBounds(331, 371, 86, 14);
        frame.getContentPane().add(lblNewLabel_3_1);

        JButton btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int barcode = Integer.parseInt(textFieldBarcode.getText());
                int quantity = Integer.parseInt(textFieldQuantity.getText());

                try {
                    order = orderController.addProduct(barcode, quantity);
                    updateList();
                    updateTotalPrice();
                } catch (DataAccessException dataAccessException) {
                    UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
                } catch (IllegalArgumentException illegalArgumentException) {
                    UIUtil.displayMessage("Product with barcode " + barcode + " does not exist!", "Invalid barcode!", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void updateTotalPrice() {
                if (order.getAppliedVoucher() == null) {
                    lblTotalPrice.setText("<html>Total price:<br>" + String.format("%.2f kn", order.getTotalPrice()));
                } else {
                    lblTotalPrice.setText("<html>Total price:<br><strike>" + String.format("%.2f kn</strike> %.2f kn", order.getTotalPrice(), order.getDiscountedPrice()));
                }
            }

            private void updateList() {
                DefaultListModel<String> listModel = new DefaultListModel<>();
                List<OrderLine> orderLineList = order.getOrderLines();
                for (int i = 0; i < orderLineList.size(); i++) {
                    listModel.add(i, orderLineList.get(i).toString());
                }

                list.setModel(listModel);
            }
        });
        btnAddToCart.setBounds(217, 411, 200, 23);
        frame.getContentPane().add(btnAddToCart);

        JComboBox comboBoxPaymentMethod = new JComboBox(Arrays.stream(PaymentType.values()).map(Enum::toString).toArray());
        comboBoxPaymentMethod.setBounds(427, 385, 205, 20);
        frame.getContentPane().add(comboBoxPaymentMethod);

        JLabel lblNewLabel_5 = new JLabel("Select payment method");
        lblNewLabel_5.setBounds(427, 371, 205, 14);
        frame.getContentPane().add(lblNewLabel_5);
        JButton btnConfirmOrder = new JButton("Confirm Order");
        btnConfirmOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PaymentType paymentType = PaymentType.valueOf((String) comboBoxPaymentMethod.getSelectedItem());
                try {
                    orderController.finishSale(paymentType);
                    UIUtil.displayMessage("Order " + order.getOrderNumber() + " has been processed successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                } catch (DataAccessException dataAccessException) {
                    dataAccessException.printStackTrace();
                    UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
                } catch (InsufficientDataException insufficientDataException) {
                    UIUtil.displayMessage("There has to be at least one item in the order!", "No items in order", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnConfirmOrder.setBounds(427, 411, 205, 23);
        frame.getContentPane().add(btnConfirmOrder);

        JLabel lblAddressInfo = new JLabel("<html>The package will be delivered<br>to the billing address you provided.<br><b>Address info:</b><br>" + order.getDeliveryAddress().toHTMLString());
        lblAddressInfo.setBounds(10, 43, 197, 105);
        frame.getContentPane().add(lblAddressInfo);

        JPanel newAddressPanel = new JPanel();
        newAddressPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        newAddressPanel.setBounds(10, 185, 200, 200);
        frame.getContentPane().add(newAddressPanel);
        newAddressPanel.setLayout(null);

        JLabel lblAddressInfo_1 = new JLabel("Address Info");
        lblAddressInfo_1.setBounds(10, 11, 92, 20);
        newAddressPanel.add(lblAddressInfo_1);

        JLabel lblNewLabel_2_2 = new JLabel("Street");
        lblNewLabel_2_2.setBounds(10, 35, 46, 14);
        newAddressPanel.add(lblNewLabel_2_2);

        JLabel lblNewLabel_2_2_1 = new JLabel("Street Number");
        lblNewLabel_2_2_1.setBounds(106, 35, 86, 14);
        newAddressPanel.add(lblNewLabel_2_2_1);

        textFieldNewAddressStreet = new JTextField();
        textFieldNewAddressStreet.setColumns(10);
        textFieldNewAddressStreet.setBounds(10, 49, 86, 20);
        newAddressPanel.add(textFieldNewAddressStreet);

        textFieldNewAddressStreetNumber = new JTextField();
        textFieldNewAddressStreetNumber.setColumns(10);
        textFieldNewAddressStreetNumber.setBounds(106, 49, 86, 20);
        newAddressPanel.add(textFieldNewAddressStreetNumber);

        textFieldNewAddressFloor = new JTextField();
        textFieldNewAddressFloor.setColumns(10);
        textFieldNewAddressFloor.setBounds(10, 94, 86, 20);
        newAddressPanel.add(textFieldNewAddressFloor);

        JLabel lblNewLabel_2_2_1_1 = new JLabel("Floor");
        lblNewLabel_2_2_1_1.setBounds(10, 80, 46, 14);
        newAddressPanel.add(lblNewLabel_2_2_1_1);

        textFieldNewAddressCity = new JTextField();
        textFieldNewAddressCity.setColumns(10);
        textFieldNewAddressCity.setBounds(106, 94, 86, 20);
        newAddressPanel.add(textFieldNewAddressCity);

        JLabel lblNewLabel_2_2_1_2 = new JLabel("City");
        lblNewLabel_2_2_1_2.setBounds(106, 80, 46, 14);
        newAddressPanel.add(lblNewLabel_2_2_1_2);

        JLabel lblNewLabel_2_2_1_3 = new JLabel("Postal Code");
        lblNewLabel_2_2_1_3.setBounds(10, 125, 86, 14);
        newAddressPanel.add(lblNewLabel_2_2_1_3);

        textFieldNewAddressPostalCode = new JTextField();
        textFieldNewAddressPostalCode.setColumns(10);
        textFieldNewAddressPostalCode.setBounds(10, 139, 86, 20);
        newAddressPanel.add(textFieldNewAddressPostalCode);

        JButton btnNewAddress = new JButton("Set Delivery Address");
        btnNewAddress.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Address a = UIUtil.parseAddress(textFieldNewAddressStreet, textFieldNewAddressStreetNumber, textFieldNewAddressFloor, textFieldNewAddressCity, textFieldNewAddressPostalCode);
                try {
                    Address lookup = orderController.findAddressByData(a);
                    if (lookup == null) {
                        orderController.createAddress(a);
                    } else {
                        a = lookup;
                    }
                    order.setDeliveryAddress(a);

                    UIUtil.displayMessage("Delivery address successfully updated.", "Successfully updated address", JOptionPane.INFORMATION_MESSAGE);
                    lblAddressInfo.setText("<html><b>Invoice will be sent to:</b><br>" + order.getInvoiceAddress().toHTMLString() +
                            "<br><b>The package will be delivered to:</b><br>" + order.getDeliveryAddress().toHTMLString());
                } catch (DataAccessException dataAccessException) {
                    dataAccessException.printStackTrace();
                    UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
                }
            }
        });
        btnNewAddress.setBounds(10, 170, 180, 23);
        newAddressPanel.add(btnNewAddress);

        newAddressPanel.setVisible(false);

        JCheckBox chckbxNewDeliveryAddress = new JCheckBox("Use a different delivery address");
        chckbxNewDeliveryAddress.addItemListener(e -> {
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            newAddressPanel.setVisible(checked);
            if (!checked) {
                order.setDeliveryAddress(order.getInvoiceAddress());
                lblAddressInfo.setText("<html>The package will be delivered<br>to the billing address you provided.<br><b>Address info:</b><br>" + order.getDeliveryAddress().toString());
            }
        });
        chckbxNewDeliveryAddress.setBounds(6, 155, 201, 23);
        frame.getContentPane().add(chckbxNewDeliveryAddress);

        JLabel lblNewLabel_2 = new JLabel("Got a voucher?");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(427, 185, 115, 14);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_4 = new JLabel("Code");
        lblNewLabel_4.setBounds(427, 210, 46, 14);
        frame.getContentPane().add(lblNewLabel_4);

        textFieldVoucherCode = new JTextField();
        textFieldVoucherCode.setBounds(427, 235, 115, 20);
        frame.getContentPane().add(textFieldVoucherCode);
        textFieldVoucherCode.setColumns(10);

        JButton btnApplyVoucher = new JButton("Apply");
        btnApplyVoucher.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String voucherCode = textFieldVoucherCode.getText();
                try {
                    Voucher v = orderController.findVoucherByCode(voucherCode);
                    int hasBeenApplied = order.applyVoucher(v);
                    if (hasBeenApplied == -1) {
                        UIUtil.displayMessage("Voucher does not exist!", "Invalid code!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (hasBeenApplied == -2) {
                            UIUtil.displayMessage("Voucher has expired!", "Invalid code!", JOptionPane.ERROR_MESSAGE);
                        } else {
                            UIUtil.displayMessage("Voucher applied successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            updateTotalPrice();
                        }
                    }
                } catch (DataAccessException dataAccessException) {
                    UIUtil.displayDBErrorMsg(dataAccessException.getMessage());
                }
            }

            private void updateTotalPrice() {
                lblTotalPrice.setText("<html>Total price:<br><strike>" + String.format("%.2f kn</strike> %.2f kn", order.getTotalPrice(), order.getDiscountedPrice()));
            }
        });
        btnApplyVoucher.setBounds(543, 234, 89, 21);
        frame.getContentPane().add(btnApplyVoucher);
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
