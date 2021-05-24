package controllers;

import db.ProductDB;
import db.interfaces.ProductDBIF;
import exceptions.DataAccessException;
import models.Product;

import java.util.List;

public class ProductController {
    private final ProductDBIF productDB;

    public ProductController() throws DataAccessException {
        this.productDB = new ProductDB();
    }

    public Product findByBarcode(int barcode) throws DataAccessException {
        return this.productDB.findByBarcode(barcode);
    }

    public List<Product> getProducts() throws DataAccessException {
        return this.productDB.getProducts();
    }

    public int updateProduct(Product p) throws DataAccessException {
        return this.productDB.updateProduct(p);
    }

    public int deleteProduct(int productId) throws DataAccessException {
        return this.productDB.deleteProduct(productId);
    }
}