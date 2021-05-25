package db;

import db.interfaces.CourierDBIF;
import exceptions.DataAccessException;
import models.Courier;
import models.enums.CourierStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierDB implements CourierDBIF {
    private static final String FIND_AVAILABLE_COURIERS_Q = "SELECT Id, FirstName, LastName, PhoneNumber, CourierStatusId FROM Couriers WHERE CourierStatusId = 1";
    private static final String UPDATE_COURIER_STATUS_Q = "UPDATE Couriers SET CourierStatusId = ? WHERE Id = ?";

    private final PreparedStatement findAvailableCouriersPS;
    private final PreparedStatement updateCourierStatusPS;

    public CourierDB() throws DataAccessException {
        try {
            this.findAvailableCouriersPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_AVAILABLE_COURIERS_Q);
            this.updateCourierStatusPS = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_COURIER_STATUS_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
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
    public int updateCourierStatus(int courierId, CourierStatus status) throws DataAccessException {
        try {
            this.updateCourierStatusPS.setInt(1, status.getValue());
            this.updateCourierStatusPS.setInt(2, courierId);

            return this.updateCourierStatusPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not fetch data", e);
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
