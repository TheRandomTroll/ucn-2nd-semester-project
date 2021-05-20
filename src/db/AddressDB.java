package db;

import db.interfaces.AddressDBIF;
import exceptions.DataAccessException;
import models.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A data access class for the <i>Addresses</i> table from the database.
 */
public class AddressDB implements AddressDBIF {
    private static final String CREATE_ADDRESS_Q = "INSERT INTO Addresses (Street, StreetNumber, Floor, City, PostalCode) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_Q = "SELECT Id, Street, StreetNumber, Floor, City, PostalCode FROM Addresses WHERE Id = ?";

    private PreparedStatement createAddressPS;
    private PreparedStatement findByIdPS;

    public AddressDB() throws DataAccessException {
        try {
            this.createAddressPS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ADDRESS_Q, Statement.RETURN_GENERATED_KEYS);
            this.findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public int createAddress(Address a) throws DataAccessException {
        try {
            this.createAddressPS.setString(1, a.getStreetName());
            this.createAddressPS.setString(2, a.getStreetNumber());
            this.createAddressPS.setString(3, a.getFloor());
            this.createAddressPS.setString(4, a.getCity());
            this.createAddressPS.setString(5, a.getPostalCode());

            int addressId;

            ResultSet generatedKeys = createAddressPS.getGeneratedKeys();
            if (generatedKeys.next()) {
                addressId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Could not insert address, no ID obtained.");
            }

            a.setId(addressId);

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
