package db;

import db.interfaces.ShoppingListDBIF;
import exceptions.DataAccessException;
import models.ShoppingList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A data access class for the <i>ShoppingLists</i> table from the database.
 */
public class ShoppingListDB implements ShoppingListDBIF {
    private static final String CREATE_LIST_Q = "INSERT INTO ShoppingLists (CreationDate, OrderId, PaymentTypeId) VALUES (?, ?, ?)";

    private PreparedStatement createListPS;

    public ShoppingListDB() throws DataAccessException {
        try {
            this.createListPS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_LIST_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public int createShoppingList(ShoppingList sl) throws DataAccessException {
        try {
            this.createListPS.setDate(1, sl.getCreationDate());
            this.createListPS.setInt(2, sl.getOrder().getId());
            this.createListPS.setInt(3, sl.getPaymentType().getValue());

            return this.createListPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not update data", e);
        }
    }
}
