package controllers;

import db.ProductDB;
import db.interfaces.ProductDBIF;
import exceptions.DataAccessException;
import models.Product;

public class ProductController {
    private final ProductDBIF productDB;

    public ProductController() throws DataAccessException {
        this.productDB = new ProductDB();
    }

    public Product findByBarcode(int barcode) throws DataAccessException {
        return this.productDB.findByBarcode(barcode);
    }
}