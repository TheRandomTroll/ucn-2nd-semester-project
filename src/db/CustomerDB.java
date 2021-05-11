package db;

import db.interfaces.CustomerDBIF;
import exceptions.DataAccessException;
import models.Address;
import models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDB implements CustomerDBIF {
    private static final String FIND_BY_PHONE_NO_Q = "SELECT Id, Name, PhoneNumber, AddressId FROM Customers WHERE PhoneNumber = ?";
    private static final String CREATE_CUSTOMER_Q = "INSERT INTO Customers (Name, PhoneNumber, AddressId) VALUES (?, ?, ?)";

    private PreparedStatement findByPhoneNoPS;
    private PreparedStatement createCustomerPS;

    public CustomerDB() throws DataAccessException {
        try {
            findByPhoneNoPS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_PHONE_NO_Q);
            createCustomerPS = DBConnection.getInstance().getConnection().prepareStatement(CREATE_CUSTOMER_Q);
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
    public int createCustomer(String name, String phoneNo, int addressId) throws DataAccessException {
        try {
            this.createCustomerPS.setString(1, name);
            this.createCustomerPS.setString(2, phoneNo);
            this.createCustomerPS.setInt(3, addressId);

            return this.createCustomerPS.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Could not retrieve data", e);
        }
    }

    private Customer buildObject(ResultSet rs) throws DataAccessException {
        try {
            Customer c = new Customer(rs.getInt("Id"), rs.getString("Name"), rs.getString("PhoneNumber"),
                    rs.getInt("email"));
            return c;
        } catch (SQLException e) {
            throw new DataAccessException("Could not read result set", e);
        }

    }
}
