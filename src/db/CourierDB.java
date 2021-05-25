package db;

import db.interfaces.CourierDBIF;
import exceptions.DataAccessException;
import models.Courier;
import models.enums.CourierStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourierDB implements CourierDBIF {
    private static final String CREATE_COURIER_Q = "INSERT INTO Couriers (FirstName, LastName, PhoneNumber, CourierStatusId) VALUES (?, ?, ?, ?)";
    private static final String GET_COURIERS_Q = "SELECT Id, FirstName, LastName, PhoneNumber, CourierStatusId FROM Couriers";
    private static final String FIND_AVAILABLE_COURIERS_Q = "SELECT Id, FirstName, LastName, PhoneNumber, CourierStatusId FROM Couriers WHERE CourierStatusId = 1";
    private static final String UPDATE_COURIER_Q = "UPDATE Couriers SET FirstName = ?, LastName = ?, PhoneNumber = ?, CourierStatusId = ? WHERE Id = ?";
    private static final String UPDATE_COURIER_STATUS_Q = "UPDATE Couriers SET CourierStatusId = ? WHERE Id = ?";
    private static final String DELETE_COURIER_Q = "DELETE FROM Couriers WHERE Id = ?";

    private final PreparedStatement createCourierPS;
    private final PreparedStatement getCouriersPS;
    private final PreparedStatement findAvailableCouriersPS;
    private final PreparedStatement updateCourierPS;
    private final PreparedStatement updateCourierStatusPS;
    private final PreparedStatement deleteCourierPS;

    public CourierDB() throws DataAccessException {
        try {
            this.createCourierPS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_COURIER_Q, Statement.RETURN_GENERATED_KEYS);
            this.getCouriersPS = DBConnection.getInstance().getConnection().prepareStatement(GET_COURIERS_Q);
            this.findAvailableCouriersPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_AVAILABLE_COURIERS_Q);
            this.updateCourierPS = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_COURIER_Q);
            this.updateCourierStatusPS = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_COURIER_STATUS_Q);
            this.deleteCourierPS = DBConnection.getInstance().getConnection().prepareStatement(DELETE_COURIER_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public int createCourier(Courier c) throws DataAccessException {
        try {
            this.createCourierPS.setString(1, c.getFirstName());
            this.createCourierPS.setString(2, c.getLastName());
            this.createCourierPS.setString(3, c.getPhoneNo());
            this.createCourierPS.setInt(4, c.getStatus().getValue());
            int rows = this.createCourierPS.executeUpdate();

            int courierId;

            ResultSet generatedKeys = this.createCourierPS.getGeneratedKeys();
            if (generatedKeys.next()) {
                courierId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Could not insert product, no ID obtained.");
            }

            c.setId(courierId);

            return rows;
        } catch (SQLException e) {
            throw new DataAccessException("Could not insert data", e);
        }
    }

    @Override
    public List<Courier> getCouriers() throws DataAccessException {
        try {
            ResultSet rs = this.getCouriersPS.executeQuery();
            return buildObjects(rs);
        } catch (SQLException e) {
            throw new DataAccessException("Could not fetch data", e);
        }
    }

    @Override
    public List<Courier> findAvailableCouriers() throws DataAccessException {
        try {
            ResultSet rs = this.findAvailableCouriersPS.executeQuery();
            return buildObjects(rs);
        } catch (SQLException e) {
            throw new DataAccessException("Could not fetch data", e);
        }
    }

    @Override
    public int updateCourier(Courier c) throws DataAccessException {
        try {
            this.updateCourierPS.setString(1, c.getFirstName());
            this.updateCourierPS.setString(2, c.getLastName());
            this.updateCourierPS.setString(3, c.getPhoneNo());
            this.updateCourierPS.setInt(4, c.getStatus().getValue());
            this.updateCourierPS.setInt(5, c.getId());

            return this.updateCourierPS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Could not update data", e);
        }
    }

    @Override
    public int updateCourierStatus(int courierId, CourierStatus status) throws DataAccessException {
        try {
            this.updateCourierStatusPS.setInt(1, status.getValue());
            this.updateCourierStatusPS.setInt(2, courierId);

            return this.updateCourierStatusPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not fetch data", e);
        }
    }

    @Override
    public int deleteCourier(int id) throws DataAccessException {
        try {
            this.deleteCourierPS.setInt(1, id);
            return this.deleteCourierPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not delete data", e);
        }
    }

    private List<Courier> buildObjects(ResultSet rs) throws DataAccessException {
        List<Courier> couriers = new ArrayList<>();
        while(true) {
            try {
                if (!rs.next()) break;
                couriers.add(buildObject(rs));
            } catch (SQLException e) {
                throw new DataAccessException("Could not parse data", e);
            }

        }

        return couriers;
    }

    private Courier buildObject(ResultSet rs) throws DataAccessException {
        try {
            return new Courier(rs.getInt("Id"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("PhoneNumber"), CourierStatus.values()[rs.getInt("CourierStatusId") - 1]);
        } catch (SQLException e) {
            throw new DataAccessException("Could not parse data", e);
        }
    }
}
