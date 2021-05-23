package db;

import db.interfaces.OrderDBIF;
import exceptions.DataAccessException;
import models.Order;
import models.OrderLine;

import java.sql.*;
/**
 * A data access class for the <i>Orders</i> table from the database.
 */
public class OrderDB implements OrderDBIF {
    private static final String CREATE_ORDER_Q = "INSERT INTO Orders (OrderNumber, CustomerId, OrderStatusId, InvoiceAddressId, DeliveryAddressId) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_Q = "UPDATE Orders SET OrderNumber = ?, TotalPrice = ?, AppliedVoucherId = ?, OrderStatusId = ?, InvoiceAddressId = ?, DeliveryAddressId = ? WHERE Id = ?";

    private final PreparedStatement createOrderPS;
    private final PreparedStatement updateOrderPS;

    public OrderDB() throws DataAccessException {
        try {
            this.createOrderPS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER_Q, Statement.RETURN_GENERATED_KEYS);
            this.updateOrderPS = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_ORDER_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
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

        return rows;
    }
}
