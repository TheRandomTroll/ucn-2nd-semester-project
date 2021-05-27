package db;

import db.interfaces.CourierBatchDBIF;
import exceptions.DataAccessException;
import models.Courier;
import models.enums.CourierStatus;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class CourierBatchDB implements CourierBatchDBIF {
    private final CourierDB courierDB;

    private static final String DISPATCH_ORDER_Q = "INSERT INTO CourierBatches (CreationDate, OrderId, CourierId) VALUES (?, ?, ?)";

    private final PreparedStatement dispatchOrderPS;
    public CourierBatchDB() throws DataAccessException {
        this.courierDB = new CourierDB();

        try {
            this.dispatchOrderPS = DBConnection.getInstance().getConnection().prepareStatement(DISPATCH_ORDER_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public Courier dispatchOrder(int orderId) throws DataAccessException {
        List<Courier> couriers = this.courierDB.findAvailableCouriers();

        try {
            int randomIndex = new Random().nextInt(couriers.size());
            Courier courier = couriers.get(randomIndex);

            DBConnection.getInstance().startTransaction();
            this.dispatchOrderPS.setDate(1, Date.valueOf(LocalDate.now()));
            this.dispatchOrderPS.setInt(2, orderId);
            this.dispatchOrderPS.setInt(3, courier.getId());

            this.courierDB.updateCourierStatus(courier.getId(), CourierStatus.DELIVERING);

            this.dispatchOrderPS.executeUpdate();
            DBConnection.getInstance().commitTransaction();
            return courier;
        } catch (SQLException e) {
            try {
                DBConnection.getInstance().rollbackTransaction();
            } catch (SQLException e1) {
                throw new DataAccessException("Could not rollback transaction", e1);
            }
            throw new DataAccessException("Could not insert data", e);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new DataAccessException("No available couriers", e);
        }
    }
}
