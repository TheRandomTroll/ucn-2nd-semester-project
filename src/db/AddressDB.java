package db;

import db.interfaces.AddressDBIF;
import exceptions.DataAccessException;
import models.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDB implements AddressDBIF {
    private static final String CREATE_ADDRESS_Q = "INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_Q = "SELECT Id, Street, StreetNumber, Floor, City, PostalCode FROM Addresses WHERE Id = ?";

    private PreparedStatement createAddressPS;
    private PreparedStatement findByIdPS;

    public AddressDB() throws DataAccessException {
        try {
            this.createAddressPS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ADDRESS_Q);
            this.findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public int createAddress(String street, String streetNumber, String floor, String city, String postalCode) throws DataAccessException {
        try {
            this.createAddressPS.setString(1, street);
            this.createAddressPS.setString(2, streetNumber);
            this.createAddressPS.setString(3, floor);
            this.createAddressPS.setString(4, city);
            this.createAddressPS.setString(5, postalCode);

            return this.createAddressPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not insert data", e);

        }
    }

    @Override
    public Address findById(int id) throws DataAccessException {
        try {
            this.findByIdPS.setInt(1, id);
            ResultSet rs = this.findByIdPS.executeQuery();
            Address a = null;

            if (rs.next()) {
                a = buildObject(rs);
            }

            return a;
        } catch (SQLException e) {
            throw new DataAccessException("Could not fetch data", e);
        }
    }

    private Address buildObject(ResultSet rs) throws DataAccessException {
        try {
            Address a = new Address(rs.getInt("Id"), rs.getString("Street"), rs.getString("StreetNumber"),
                                    rs.getString("Floor"), rs.getString("City"), rs.getString("PostalCode"));
            return a;
        } catch (SQLException e) {
            throw new DataAccessException("Could not parse data", e);

        }
    }
}
