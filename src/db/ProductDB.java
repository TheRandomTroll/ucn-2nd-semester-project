package db;

import db.interfaces.ProductDBIF;
import exceptions.DataAccessException;
import models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access class for the <i>Products</i> table from the database.
 */
public class ProductDB implements ProductDBIF {
    private static final String CREATE_PRODUCT_Q = "INSERT INTO Products (Name, Barcode, Description, Price, MaxStock, MinStock, Quantity) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_BARCODE_Q = "SELECT Id, Name, Barcode, Description, Price, MaxStock, MinStock, Quantity FROM Products WHERE Barcode = ?";
    private static final String GET_PRODUCTS_Q = "SELECT Id, Name, Barcode, Description, Price, MaxStock, MinStock, Quantity FROM Products";
    private static final String UPDATE_PRODUCT_Q = "UPDATE Products SET Name = ?, Barcode = ?, Description = ?, Price = ?, MaxStock = ?, MinStock = ?, Quantity = ? WHERE Id = ?";
    private static final String DELETE_PRODUCT_Q = "DELETE FROM Products WHERE Id = ?";

    private final PreparedStatement createProductPS;
    private final PreparedStatement findByBarcodePS;
    private final PreparedStatement getProductsPS;
    private final PreparedStatement updateProductPS;
    private final PreparedStatement deleteProductPS;

    public ProductDB() throws DataAccessException {
        try {
            this.createProductPS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_PRODUCT_Q, Statement.RETURN_GENERATED_KEYS);
            this.findByBarcodePS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_BARCODE_Q);
            this.getProductsPS = DBConnection.getInstance().getConnection().prepareStatement(GET_PRODUCTS_Q);
            this.updateProductPS = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_PRODUCT_Q);
            this.deleteProductPS = DBConnection.getInstance().getConnection().prepareStatement(DELETE_PRODUCT_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public int createProduct(Product p) throws DataAccessException {
        try {
            this.createProductPS.setString(1, p.getName());
            this.createProductPS.setInt(2, p.getBarcode());
            this.createProductPS.setString(3, p.getDescription());
            this.createProductPS.setDouble(4, p.getPrice());
            this.createProductPS.setInt(5, p.getMaxStock());
            this.createProductPS.setInt(6, p.getMinStock());
            this.createProductPS.setInt(7, p.getQuantity());

            int rows = this.createProductPS.executeUpdate();

            int productId;

            ResultSet generatedKeys = this.createProductPS.getGeneratedKeys();
            if (generatedKeys.next()) {
                productId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Could not insert product, no ID obtained.");
            }

            p.setId(productId);

            return rows;
        } catch (SQLException e) {
            throw new DataAccessException("Could not insert data", e);
        }
    }

    @Override
    public List<Product> getProducts() throws DataAccessException {
        try {
            ResultSet rs = this.getProductsPS.executeQuery();

            return buildObjects(rs);
        } catch (SQLException e) {
            throw new DataAccessException("Could not fetch data", e);
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

    @Override
    public int updateProduct(Product p) throws DataAccessException {
        try {
            this.updateProductPS.setString(1, p.getName());
            this.updateProductPS.setInt(2, p.getBarcode());
            this.updateProductPS.setString(3, p.getDescription());
            this.updateProductPS.setDouble(4, p.getPrice());
            this.updateProductPS.setInt(5, p.getMaxStock());
            this.updateProductPS.setInt(6, p.getMinStock());
            this.updateProductPS.setInt(7, p.getQuantity());
            this.updateProductPS.setInt(8, p.getId());

            return this.updateProductPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not update data", e);
        }
    }

    @Override
    public int deleteProduct(int productId) throws DataAccessException {
        try {
            this.deleteProductPS.setInt(1, productId);;

            return this.deleteProductPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not update data", e);
        }
    }

    private List<Product> buildObjects(ResultSet rs) throws DataAccessException {
        List<Product> list = new ArrayList<>();
        while(true) {
            try {
                if (!rs.next()) break;
                list.add(buildObject(rs));
            } catch (SQLException e) {
                throw new DataAccessException("Could not parse data", e);
            }
        }

        return list;
    }

    private Product buildObject(ResultSet rs) throws DataAccessException {
        try {
            return new Product(rs.getInt("Id"), rs.getString("Name"), rs.getInt("Barcode"), rs.getString("Description"),
                                    rs.getDouble("Price"), rs.getInt("MaxStock"), rs.getInt("MinStock"), rs.getInt("Quantity"));
        } catch (SQLException e) {
            throw new DataAccessException("Could not parse data", e);
        }
    }
}
