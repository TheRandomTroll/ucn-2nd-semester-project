package db;

import db.interfaces.OrderLineDBIF;
import exceptions.DataAccessException;
import models.Order;
import models.OrderLine;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A data access class for the <i>OrderLines</i> table from the database.
 */
public class OrderLineDB implements OrderLineDBIF {
    private static final String CREATE_ORDER_LINE_Q = "INSERT INTO OrderLines (Quantity, ProductId, OrderId) VALUES (?, ?, ?)";

    private final PreparedStatement createOrderLinePS;

    public OrderLineDB() throws DataAccessException {
        try {
            this.createOrderLinePS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER_LINE_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public int createOrderLine(Order o, OrderLine ol) throws DataAccessException {
        try {
            DBConnection.getInstance().startTransaction();
            this.createOrderLinePS.setInt(1, ol.getQuantity());
            this.createOrderLinePS.setInt(2, ol.getProduct().getId());
            this.createOrderLinePS.setInt(3, o.getId());

            int rows = this.createOrderLinePS.executeUpdate();
            DBConnection.getInstance().commitTransaction();
            return rows;

        } catch (SQLException e) {
            try {
                DBConnection.getInstance().rollbackTransaction();
            } catch (SQLException e1) {
                throw new DataAccessException("Could not rollback transaction", e1);
            }
            throw new DataAccessException("Could not insert data", e);

        }
    }
}
