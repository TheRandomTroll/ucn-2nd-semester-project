package db.interfaces;

import exceptions.DataAccessException;
import models.Courier;
import models.enums.CourierStatus;

import java.util.List;

public interface CourierDBIF {
    List<Courier> findAvailableCouriers() throws DataAccessException;
    int updateCourierStatus(int courierId, CourierStatus status) throws DataAccessException;
}
