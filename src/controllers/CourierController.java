package controllers;

import exceptions.DataAccessException;
import models.Courier;

public class CourierController {
    private final CourierBatchController courierBatchController;

    public CourierController() throws DataAccessException {
        this.courierBatchController = new CourierBatchController();
    }

    public Courier dispatchOrder(int orderId) throws DataAccessException {
        return this.courierBatchController.dispatchOrder(orderId);
    }
}
