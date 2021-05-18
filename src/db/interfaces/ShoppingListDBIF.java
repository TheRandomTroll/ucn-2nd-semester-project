package db.interfaces;

import exceptions.DataAccessException;
import models.Order;
import models.ShoppingList;
import models.Voucher;

public interface ShoppingListDBIF {
    int createShoppingList(ShoppingList sl) throws DataAccessException;
}
