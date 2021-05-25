package db;

import db.interfaces.AddressDBIF;
import db.interfaces.CourierBatchDBIF;
import db.interfaces.CustomerDBIF;
import db.interfaces.OrderDBIF;
import exceptions.DataAccessException;
import models.*;
import models.enums.OrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access class for the <i>Orders</i> table from the database.
 */
public class OrderDB implements OrderDBIF {
    private static final String GET_FINISHED_ORDERS_Q = "SELECT Id, OrderNumber, TotalPrice, CustomerId, OrderStatusId, InvoiceAddressId, DeliveryAddressId FROM Orders WHERE OrderStatusId <> 1";
    private static final String CREATE_ORDER_Q = "INSERT INTO Orders (OrderNumber, CustomerId, OrderStatusId, InvoiceAddressId, DeliveryAddressId) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_Q = "UPDATE Orders SET OrderNumber = ?, TotalPrice = ?, AppliedVoucherId = ?, OrderStatusId = ?, InvoiceAddressId = ?, DeliveryAddressId = ? WHERE Id = ?";
    private static final String UPDATE_ORDER_STATUS_Q = "UPDATE Orders SET OrderStatusId = ? WHERE Id = ?";

    private final PreparedStatement getFinishedOrdersPS;
    private final PreparedStatement createOrderPS;
    private final PreparedStatement updateOrderPS;
    private final PreparedStatement updateOrderStatusPS;

    private final CustomerDBIF customerDB;
    private final AddressDBIF addressDB;
    private final CourierBatchDBIF courierBatchDB;

    public OrderDB() throws DataAccessException {
        try {
            this.customerDB = new CustomerDB();
            this.addressDB = new AddressDB();
            this.courierBatchDB = new CourierBatchDB();
            this.getFinishedOrdersPS = DBConnection.getInstance().getConnection().prepareStatement(GET_FINISHED_ORDERS_Q);
            this.createOrderPS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER_Q, Statement.RETURN_GENERATED_KEYS);
            this.updateOrderPS = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_ORDER_Q);
            this.updateOrderStatusPS = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_ORDER_STATUS_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public List<Order> getFinishedOrders() throws DataAccessException {
        try {
            ResultSet rs = getFinishedOrdersPS.executeQuery();
            return buildObjects(rs);
        } catch (SQLException e) {
            throw new DataAccessException("Could not fetch data", e);
        }

    }
    @Override
    public int createOrder(Order o) throws DataAccessException {
        try {
            this.createOrderPS.setString(1, o.getOrderNumber());
            this.createOrderPS.setInt(2, o.getCustomer().getId());
            this.createOrderPS.setInt(3, o.getStatus().getValue());
            if (o.getInvoiceAddress() == null) {

                this.createOrderPS.setNull(4, Types.NULL);
            } else {
                this.createOrderPS.setInt(4, o.getInvoiceAddress().getId());
            }
            if (o.getDeliveryAddress() == null) {
                this.createOrderPS.setNull(5, Types.NULL);
            } else {
                this.createOrderPS.setInt(5, o.getDeliveryAddress().getId());
            }

            int rows = this.createOrderPS.executeUpdate();

            int orderId;

            ResultSet generatedKeys = createOrderPS.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Could not insert order, no ID obtained.");
            }

            o.setId(orderId);

            return rows;
        } catch (SQLException e) {
            throw new DataAccessException("Could not insert data", e);
        }
    }

    private int updateOrder(Order o) throws DataAccessException {
        try {
            this.updateOrderPS.setString(1, o.getOrderNumber());
            this.updateOrderPS.setDouble(2, o.getTotalPrice());
            if(o.getAppliedVoucher() == null) {
                this.updateOrderPS.setNull(3, Types.NULL);
            } else {
                this.updateOrderPS.setInt(3, o.getAppliedVoucher().getId());
            }
            this.updateOrderPS.setInt(4, o.getStatus().getValue());
            this.updateOrderPS.setInt(5, o.getInvoiceAddress().getId());
            this.updateOrderPS.setInt(6, o.getDeliveryAddress().getId());
            this.updateOrderPS.setInt(7, o.getId());

            return this.updateOrderPS.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Could not update data", e);
        }
    }

    @Override
    public int saveOrder(Order o) throws DataAccessException {
        int rows = this.updateOrder(o);
        for (OrderLine ol : o.getOrderLines()) {
            rows += new OrderLineDB().createOrderLine(o, ol);
        }

        this.courierBatchDB.dispatchOrder(o.getId());
        return rows;
    }

    @Override
    public int updateOrderStatus(int id, OrderStatus status) throws DataAccessException {
        try {
            this.updateOrderStatusPS.setInt(1, status.getValue());
            this.updateOrderStatusPS.setInt(2, id);

            return this.updateOrderStatusPS.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Could not update data", e);
        }
    }

    private List<Order> buildObjects(ResultSet rs) throws DataAccessException {
        List<Order> orders = new ArrayList<>();
        while(true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new DataAccessException("Could not parse data", e);
            }
            orders.add(buildObject(rs));
        }
        return orders;
    }

    private Order buildObject(ResultSet rs) throws DataAccessException {
        try {
            Customer c = this.customerDB.findById(rs.getInt("CustomerId"));
            Address invoice = this.addressDB.findById(rs.getInt("InvoiceAddressId"));
            Address delivery = this.addressDB.findById(rs.getInt("DeliveryAddressId"));
            return new Order(rs.getInt("Id"), rs.getString("OrderNumber"), rs.getFloat("TotalPrice"), c, OrderStatus.values()[rs.getInt("OrderStatusId") - 1], invoice, delivery);
        } catch (SQLException e) {
            throw new DataAccessException("Could not parse data", e);
        }
    }
}
