package db.interfaces;

import exceptions.DataAccessException;
import models.Product;

public interface ProductDBIF {
    Product findByBarcode(int barcode) throws DataAccessException;
}
