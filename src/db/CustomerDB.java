package db;

import db.interfaces.CustomerDBIF;
import exceptions.DataAccessException;
import models.Address;
import models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A data access class for the <i>Customers</i> table from the database.
 */
public class CustomerDB implements CustomerDBIF {
    private static final String FIND_BY_PHONE_NO_Q = "BEGIN TRAN SELECT Id, Name, PhoneNumber, Email, AddressId FROM Customers WHERE PhoneNumber = ? COMMIT TRAN";
    private static final String FIND_BY_ID_Q = "BEGIN TRAN SELECT Id, Name, PhoneNumber, Email, AddressId FROM Customers WHERE Id = ? COMMIT TRAN";
    private static final String CREATE_CUSTOMER_Q = "BEGIN TRAN INSERT INTO Customers (Name, PhoneNumber, Email, AddressId) VALUES (?, ?, ?, ?) COMMIT TRAN";
    private static final String UPDATE_CUSTOMER_Q = "BEGIN TRAN UPDATE Customers SET Name = ?, PhoneNumber = ?, Email = ?, AddressId = ? WHERE PhoneNumber = ? COMMIT TRAN";

    private final PreparedStatement findByPhoneNoPS;
    private final PreparedStatement findByIdPS;
    private final PreparedStatement createCustomerPS;
    private final PreparedStatement updateCustomerPS;
    private final AddressDB addressDB;

    public CustomerDB() throws DataAccessException {
        try {
            this.addressDB = new AddressDB();
            findByPhoneNoPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_PHONE_NO_Q);
            findByIdPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_Q);
            createCustomerPS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_CUSTOMER_Q);
            updateCustomerPS = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_CUSTOMER_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public Customer findByPhoneNo(String phoneNo) throws DataAccessException {
        try {
            this.findByPhoneNoPS.setString(1, phoneNo);
            ResultSet rs = this.findByPhoneNoPS.executeQuery();
            Customer c = null;
            if(rs.next()) {
                c = buildObject(rs);
            }
            return c;
        } catch (SQLException e) {
            throw new DataAccessException("Could not retrieve data", e);
        }
    }

    @Override
    public Customer findById(int id) throws DataAccessException {
        try {
            this.findByIdPS.setInt(1, id);
            ResultSet rs = this.findByIdPS.executeQuery();
            Customer c = null;
            if(rs.next()) {
                c = buildObject(rs);
            }
            return c;
        } catch (SQLException e) {
            throw new DataAccessException("Could not retrieve data", e);
        }
    }

    @Override
    public int createCustomer(String name, String phoneNo, String email, int addressId) throws DataAccessException {
        try {
            this.createCustomerPS.setString(1, name);
            this.createCustomerPS.setString(2, phoneNo);
            this.createCustomerPS.setString(3, email);
            this.createCustomerPS.setInt(4, addressId);

            return this.createCustomerPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not create data", e);
        }
    }

    @Override
    public int updateCustomer(String name, String phoneNo, String email, int addressId) throws DataAccessException {
        try {
            this.updateCustomerPS.setString(1, name);
            this.updateCustomerPS.setString(2, phoneNo);
            this.updateCustomerPS.setString(3, email);
            this.updateCustomerPS.setInt(4, addressId);
            this.updateCustomerPS.setString(5, phoneNo);

            return this.updateCustomerPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not update data", e);
        }
    }

    private Customer buildObject(ResultSet rs) throws DataAccessException {
        try {
            Address a = this.addressDB.findById(rs.getInt("AddressId"));
            return new Customer(rs.getInt("Id"), rs.getString("Name"), rs.getString("PhoneNumber"),
                    rs.getString("Email"), a);
        } catch (SQLException e) {
            throw new DataAccessException("Could not read result set", e);
        }

    }
}
