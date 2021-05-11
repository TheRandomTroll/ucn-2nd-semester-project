package db.interfaces;

import exceptions.DataAccessException;
import models.Voucher;

public interface VoucherDBIF {
    Voucher findByCode(String code) throws DataAccessException;
}
