package db.interfaces;

import exceptions.DataAccessException;
import models.ShoppingList;

public interface ShoppingListDBIF {
    int createShoppingList(ShoppingList sl) throws DataAccessException;
}
