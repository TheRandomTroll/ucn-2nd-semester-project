package db.interfaces;

import exceptions.DataAccessException;
import models.Courier;
import models.enums.CourierStatus;

import java.util.List;

public interface CourierDBIF {
    int createCourier(Courier c) throws DataAccessException;

    List<Courier> getCouriers() throws DataAccessException;

    List<Courier> findAvailableCouriers() throws DataAccessException;

    int updateCourierStatus(int courierId, CourierStatus status) throws DataAccessException;

    int updateCourier(Courier c) throws DataAccessException;

    int deleteCourier(int id) throws DataAccessException;
}
