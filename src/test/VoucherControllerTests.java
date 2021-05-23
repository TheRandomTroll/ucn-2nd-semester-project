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
    public void setUp() {
        try {
            this.voucherController = new VoucherController();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindVoucherByCode() {
        Voucher v = null;
        try {
            v = this.voucherController.findByCode("96P38357F");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        assertEquals(0.53, v.getDiscount(), 0.001);
        assertEquals(22, v.getId());
    }

    @Test
    public void testFindVoucherByInvalidCode() {
        Voucher v = null;
        try {
            v = this.voucherController.findByCode("96P38357O");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        assertNull(v);
    }
}
