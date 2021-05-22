package test;

import controllers.VoucherController;
import exceptions.DataAccessException;
import models.Voucher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VoucherControllerTests {
    private VoucherController voucherController;

    @Before
    public void setUp() throws DataAccessException {
        this.voucherController = new VoucherController();
    }

    @Test
    public void testFindVoucherByCode() throws DataAccessException {
        Voucher v = this.voucherController.findByCode("96P38357F");
        assertEquals(0.53, v.getDiscount(), 0.001);
        assertEquals(22, v.getId());
    }

    @Test
    public void testFindVoucherByInvalidCode() throws DataAccessException {
        Voucher v = this.voucherController.findByCode("96P38357O");
        assertNull(v);
    }
}
