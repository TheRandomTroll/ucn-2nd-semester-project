package controllers;

import db.VoucherDB;
import db.interfaces.VoucherDBIF;
import exceptions.DataAccessException;
import models.Voucher;

public class VoucherController {
    private VoucherDBIF voucherDB;

    public VoucherController() throws DataAccessException {
        this.voucherDB = new VoucherDB();
    }

    public Voucher findByCode(String code) throws DataAccessException {
        return this.voucherDB.findByCode(code);
    }
}
