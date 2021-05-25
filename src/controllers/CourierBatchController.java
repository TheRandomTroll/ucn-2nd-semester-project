package controllers;

import db.CourierBatchDB;
import db.interfaces.CourierBatchDBIF;
import exceptions.DataAccessException;
import models.Courier;

public class CourierBatchController {
    private final CourierBatchDBIF courierBatchDB;

    public CourierBatchController() throws DataAccessException {
        this.courierBatchDB = new CourierBatchDB();
    }

    public Courier dispatchOrder(int orderId) throws DataAccessException {
        return this.courierBatchDB.dispatchOrder(orderId);
    }
}
