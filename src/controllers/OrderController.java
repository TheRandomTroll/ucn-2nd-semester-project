package controllers;

import db.OrderDB;
import db.interfaces.OrderDBIF;
import exceptions.DataAccessException;
import exceptions.InsufficientDataException;
import models.Address;
import models.Order;
import models.Product;
import models.Voucher;
import models.enums.OrderStatus;
import models.enums.PaymentType;

public class OrderController {
    private Order order;
    private final OrderDBIF orderDB;
    private final AddressController addressController;
    private final VoucherController voucherController;
    private final ShoppingListController shoppingListController;

    public OrderController() throws DataAccessException {
        this.orderDB = new OrderDB();
        this.addressController = new AddressController();
        this.voucherController = new VoucherController();
        this.shoppingListController = new ShoppingListController();
    }

    public void createOrder(Order o) throws DataAccessException {
        this.order = o;
        this.orderDB.createOrder(order);
    }

    public Order addProduct(int barcode, int qty) throws DataAccessException {
        Product p = new ProductController().findByBarcode(barcode);
        if(p == null) {
            throw new IllegalArgumentException("Invalid product barcode!");
        }
        this.order.addOrderLine(p, qty);
        return order;
    }

    public void finishSale(PaymentType paymentType) throws DataAccessException, InsufficientDataException {
        if(order.getOrderLines().size() == 0) {
            throw new InsufficientDataException("Not enough order lines to finish order.");
        }

        this.shoppingListController.createShoppingList(order, paymentType);
        this.shoppingListController.saveShoppingList();
        this.order.setStatus(OrderStatus.ALLOCATED);
        orderDB.saveOrder(order);
    }

    public Address findAddressByData(Address a) throws DataAccessException {
        return this.addressController.findByData(a);
    }

    public int createAddress(Address a) throws DataAccessException {
        return this.addressController.createAddress(a);
    }

    public Voucher findVoucherByCode(String voucherCode) throws DataAccessException {
        return this.voucherController.findByCode(voucherCode);
    }
}
