package db.interfaces;

import exceptions.DataAccessException;
import models.Product;

import java.util.List;

public interface ProductDBIF {
    List<Product> getProducts() throws DataAccessException;

    Product findByBarcode(int barcode) throws DataAccessException;

    int updateProduct(Product p) throws DataAccessException;
}
