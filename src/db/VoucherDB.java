package db;

import db.interfaces.VoucherDBIF;
import exceptions.DataAccessException;
import models.Voucher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A data access class for the <i>Vouchers</i> table from the database.
 */
public class VoucherDB implements VoucherDBIF {
    private static final String FIND_BY_CODE_Q = "SELECT Id, Code, ExpirationDate, Discount FROM Vouchers WHERE Code = ?";

    private final PreparedStatement findByCodePS;

    public VoucherDB() throws DataAccessException {
        try {
            this.findByCodePS = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_CODE_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statement", e);
        }
    }

    @Override
    public Voucher findByCode(String code) throws DataAccessException {
        try {
            this.findByCodePS.setString(1, code);
            ResultSet rs = this.findByCodePS.executeQuery();
            Voucher v = null;

            if(rs.next()) {
                v = buildObject(rs);
            }

            return v;
        } catch (SQLException e) {
            throw new DataAccessException("Could not fetch data", e);
        }

    }

    private Voucher buildObject(ResultSet rs) throws DataAccessException {
        try {
            return new Voucher(rs.getInt("Id"), rs.getString("Code"), rs.getDate("ExpirationDate"), rs.getDouble("Discount"));
        } catch (SQLException e) {
            throw new DataAccessException("Could not parse data", e);
        }
    }
}
