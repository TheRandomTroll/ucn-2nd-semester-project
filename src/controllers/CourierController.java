package controllers;

import db.CourierDB;
import db.interfaces.CourierDBIF;
import exceptions.DataAccessException;
import models.Courier;

import java.util.List;

public class CourierController {
    private final CourierDBIF courierDB;

    private final CourierBatchController courierBatchController;

    public CourierController() throws DataAccessException {
        this.courierDB = new CourierDB();
        this.courierBatchController = new CourierBatchController();
    }

    public List<Courier> getCouriers() throws DataAccessException {
        return this.courierDB.getCouriers();
    }

    public int updateCourier(Courier c) throws DataAccessException {
        return this.courierDB.updateCourier(c);
    }

    public int deleteCourier(int id) throws DataAccessException {
        return this.courierDB.deleteCourier(id);
    }

    public Courier dispatchOrder(int orderId) throws DataAccessException {
        return this.courierBatchController.dispatchOrder(orderId);
    }

    public int addCourier(Courier c) throws DataAccessException {
        return this.courierDB.createCourier(c);
    }
}
