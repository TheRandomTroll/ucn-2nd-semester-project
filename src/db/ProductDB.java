package db;

import db.interfaces.ProductDBIF;
import exceptions.DataAccessException;
import models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A data access class for the <i>Products</i> table from the database.
 */
public class ProductDB implements ProductDBIF {
    private static final String FIND_BY_BARCODE_Q = "SELECT Id, Name, Barcode, Description, Price, MaxStock, MinStock FROM Products WHERE Barcode = ?";

    private PreparedStatement findByBarcodePS;

    public ProductDB() throws DataAccessException {
        try {
            this.findByBarcodePS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_BARCODE_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public Product findByBarcode(int barcode) throws DataAccessException {
        try {
            this.findByBarcodePS.setInt(1, barcode);
            ResultSet rs = this.findByBarcodePS.executeQuery();
            Product p = null;
            if(rs.next()) {
                p = buildObject(rs);
            }

            return p;
        } catch (SQLException e) {
            throw new DataAccessException("Could not fetch data", e);
        }
    }

    private Product buildObject(ResultSet rs) throws DataAccessException {
        try {
            Product p = new Product(rs.getInt("Id"), rs.getString("Name"), rs.getInt("Barcode"), rs.getString("Description"),
                                    rs.getDouble("Price"), rs.getInt("MaxStock"), rs.getInt("MinStock"));
            return p;
        } catch (SQLException e) {
            throw new DataAccessException("Could not parse data", e);
        }
    }
}
