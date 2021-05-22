package controllers;

import db.ShoppingListDB;
import db.interfaces.ShoppingListDBIF;
import exceptions.DataAccessException;
import models.Order;
import models.ShoppingList;
import models.Voucher;
import models.enums.PaymentType;

import java.sql.Date;
import java.time.LocalDate;

public class ShoppingListController {
    private ShoppingList sl;
    private ShoppingListDBIF shoppingListDB;

    public ShoppingListController() throws DataAccessException {
        this.shoppingListDB = new ShoppingListDB();
    }

    public ShoppingList createShoppingList(Order o, PaymentType paymentType) {
        Date today = Date.valueOf(LocalDate.now());
        this.sl = new ShoppingList(today, o, paymentType);
        return sl;
    }

    public int saveShoppingList() throws DataAccessException {
        return this.shoppingListDB.createShoppingList(sl);
    }
}
